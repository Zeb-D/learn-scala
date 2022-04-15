package com.yd.scala.hello.extension.adviser;

import com.alibaba.fastjson.JSON;
import com.yd.scala.hello.extension.annotation.ExtensionPoint;
import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;
import com.yd.scala.hello.extension.exception.ExtensionRuntimeException;
import com.yd.scala.hello.extension.invoker.ExtensionAdapterWrapper;
import com.yd.scala.hello.extension.invoker.ExtensionPointInvoker;
import com.yd.scala.hello.extension.invoker.ExtensionPointInvokerPool;
import com.yd.scala.hello.extension.invoker.ParameterCalculate;
import com.yd.scala.hello.extension.path.PathReader;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public class ExtensionMethodInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionMethodInterceptor.class);
    private final PathReader pathReader = new PathReader();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        ExtensionPoint extensionPoint = method.getAnnotation(ExtensionPoint.class);
        String name = extensionPoint.name();
        if (StringUtils.isBlank(name)) {
            return invocation.proceed();
        }

        ExtensionPointInvoker extensionPointInvoker = ExtensionPointInvokerPool.getExtensionPointInvoker(name);
        if (extensionPointInvoker == null) {
            logger.info("未加载到扩展点对应的扩展插件 extensionPointName:{}", extensionPoint.name());
            return invocation.proceed();
        }
        logger.info("当前扩展点成功加载的扩展插件 extensionPointName:{} invoke:{}", extensionPoint.name(),
                JSON.toJSONString(extensionPointInvoker));

        Object[] methodParameter = invocation.getArguments();
        Object result = null;

        ExtensionParameter parameter = new ExtensionParameter();
        parameter.setParameters(methodParameter);
        parameter.setParameterTypes(getParameterTypes(method.getParameterTypes()));

        ParameterCalculate calculate = new ParameterCalculate(methodParameter, pathReader);
        if (extensionPointInvoker.hasBeforeExtensions()) {
            doLinkedInvoker(extensionPointInvoker.getBeforeExtensions(), parameter, calculate);
        }

        boolean beReplace = false;
        if (extensionPointInvoker.hasReplaceExtensions()) {
            for (ExtensionAdapterWrapper invoker : extensionPointInvoker.getReplaceExtensions()) {
                if (!calculate.match(invoker.getScript())) {
                    continue;
                }
                beReplace = true;
                ExtensionResult<?> extensionResult = tryDoInvoker(invoker, parameter);
                if (extensionResult.isSuccess()) {
                    result = extensionResult.getResult();
                } else if (extensionResult.getException() != null) {
                    throw new ExtensionRuntimeException(extensionResult.getException());
                } else {
                    throw new ExtensionRuntimeException(extensionResult.getErrorMsg());
                }
                break;
            }
        }

        if (!beReplace) {
            result = invocation.proceed();
        }

        if (extensionPointInvoker.hasAfterExtensions()) {
            parameter.setReturnType(method.getReturnType().getName());
            parameter.setReturnObject(result);
            doLinkedInvoker(extensionPointInvoker.getAfterExtensions(), parameter, calculate);
        }

        return result;
    }

    private void doLinkedInvoker(List<ExtensionAdapterWrapper> linkedInvokers, ExtensionParameter parameter, ParameterCalculate calculate) {
        for (ExtensionAdapterWrapper invoker : linkedInvokers) {
            if (calculate.match(invoker.getScript())) {
                tryDoInvoker(invoker, parameter);
            }
        }
    }

    private ExtensionResult<?> tryDoInvoker(ExtensionAdapterWrapper invoker, ExtensionParameter parameter) {
        long startTime = System.currentTimeMillis();
        ExtensionResult<?> result = null;
        try {
            result = invoker.doExtend(parameter);
        } catch (Throwable e) {
            result = ExtensionResult.exception(e);
        } finally {
            if (result == null) {
                result = ExtensionResult.error("result is null");
            }
            if (result.isSuccess()) {
                logger.info("执行扩展逻辑完成 action:{} reference:{} parameter:{} result:{}, 耗时:{}ms", invoker.getDefinition().getAction(),
                        invoker.getName(),
                        JSON.toJSONString(parameter), JSON.toJSONString(result),
                        System.currentTimeMillis() - startTime);
            } else {
                logger.error("执行扩展逻辑错误 action:{} reference:{} parameter:{}  result:{}, 耗时:{}ms",
                        invoker.getDefinition().getAction(), invoker.getName(),
                        JSON.toJSONString(parameter), JSON.toJSONString(result),
                        System.currentTimeMillis() - startTime);
            }
        }
        return result;
    }

    private String[] getParameterTypes(Class<?>[] types) {
        if (types == null || types.length == 0) {
            return new String[0];
        }
        String[] typeNames = new String[types.length];
        for (int i = 0; i < types.length; ++i) {
            typeNames[i] = types[i].getName();
        }
        return typeNames;
    }
}
