package com.yd.scala.hello.handler.aspect;

import com.alibaba.fastjson.JSON;
import com.yd.scala.hello.handler.cluster.Clusters;
import com.yd.scala.hello.handler.helper.ZebraHelper;
import com.yd.scala.hello.handler.path.RootPath;
import com.yd.scala.hello.handler.utils.DataPool;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class ZebraInterceptor implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ZebraInterceptor.class);
    private ConcurrentHashMap<Method, List<String>> methodParameterNamesCache = new ConcurrentHashMap();

    public ZebraInterceptor() {
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        Method method = mi.getThis().getClass().getDeclaredMethod(mi.getMethod().getName(), mi.getMethod().getParameterTypes());
        Object[] args = mi.getArguments();
        List<String> paramNames = this.getMethodParamNames(method);
        String className = mi.getThis().getClass().getSimpleName();
        String classMethodName = className + "." + method.getName();
        long timestamp = System.currentTimeMillis();
        log.info("before invoke method:{}, parameter names:{}, args:{}, timestamp:{}", new Object[]{classMethodName, paramNames, JSON.toJSON(args), timestamp});
        Boolean isZebra = (Boolean) DataPool.get("isZebra");

        try {
            log.info("是否开启脱敏：{}", isZebra);
            if (!isZebra) {
                return mi.proceed();
            }

            Zebra zebra = (Zebra) method.getAnnotation(Zebra.class);
            Boolean flag = method.isAnnotationPresent(Zebra.class);
            log.info("是否包含注解：{},zebra:{},flag:{}", JSON.toJSON(zebra), flag);
            if (Objects.isNull(zebra)) {
                return mi.proceed();
            }
            Object obj = mi.proceed();
            log.info("proceed ={}", JSON.toJSON(obj));
            String id = zebra.id();
            if (StringUtils.isBlank(id)) {
                id = zebra.value();
            }

            RootPath rootPath = Clusters.getRootPath(id);
            if (rootPath == null) {
                return obj;
            }

            Object object = ZebraHelper.invoke(rootPath, obj);
            log.info("after invoke method:{}, result:{}, invoke timestamp:{}, call duration:{}", new Object[]{classMethodName, JSON.toJSON(object), timestamp, System.currentTimeMillis() - timestamp});
            return object;
        } catch (Exception var20) {
            String errMessage = "exception!!! invoke method:" + classMethodName + ",  parameter names:" + paramNames + ", args:" + JSON.toJSONString(args) + ", invoke timestamp:" + timestamp;
            log.error(errMessage, var20);
            throw var20;
        }
    }

    private List<String> getMethodParamNames(Method method) {
        List<String> paramNames = (List) this.methodParameterNamesCache.get(method);
        if (paramNames != null) {
            return paramNames;
        } else {
            Parameter[] parameters = method.getParameters();
            paramNames = new ArrayList();
            Parameter[] var4 = parameters;
            int var5 = parameters.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Parameter parameter = var4[var6];
                paramNames.add(parameter.getName());
            }

            this.methodParameterNamesCache.put(method, paramNames);
            return paramNames;
        }
    }
}