package com.yd.scala.hello.extension.adapter.local;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;
import com.yd.scala.hello.extension.compiler.CtClassCompiler;
import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;
import com.yd.scala.hello.extension.spring.KitSpringContext;
import com.yd.scala.hello.extension.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;


public class SpringLocalAdapterFactory implements LocalAdapterFactory {
    private static final Logger logger = LoggerFactory.getLogger(SpringLocalAdapterFactory.class);

    private final static AtomicLong classIndex = new AtomicLong(1L);

    private final InvalidLocalExtensionAdapter invalidAdapter = new InvalidLocalExtensionAdapter();

    @Override
    public ExtensionAdapter getAdapter(String interfaceName, String methodName, Type returnType) {
        Class<?> clazz;
        try {
            clazz = ClassUtils.fromName(interfaceName);
        } catch (Throwable ignored) {
            return null;
        }
        ApplicationContext context = KitSpringContext.getApplicationContext();
        Object bean;
        try {
            bean = context.getBean(clazz);
        } catch (Throwable ignored) {
            return null;
        }

        try {
            Method method = clazz.getMethod(methodName, ExtensionParameter.class);
            Type methodReturnType = method.getGenericReturnType();
            if (!(methodReturnType instanceof ParameterizedType)) {
                logger.error("返回值类型不匹配");
                return invalidAdapter;
            }
            ParameterizedType parameterizedType = (ParameterizedType) methodReturnType;
            if (parameterizedType.getRawType() != ExtensionResult.class) {
                logger.error("返回值类型不匹配");
                return invalidAdapter;
            }
            Type resultType = parameterizedType.getActualTypeArguments()[0];
            if (!ClassUtils.typeEquals(resultType, returnType)) {
                logger.error("返回值类型不匹配");
                return invalidAdapter;
            }
            String code = buildAdapterCode(interfaceName, methodName);
            Class<?> proxyServiceClass = CtClassCompiler.compile(code, ClassUtils.getClassLoader());
            Method newInstanceMethod = proxyServiceClass.getMethod("newInstance", Object.class);
            return (ExtensionAdapter) newInstanceMethod.invoke(null, bean);
        } catch (Throwable throwable) {
            logger.error("构建代理类失败", throwable);
            return invalidAdapter;
        }
    }

    private String buildAdapterCode(String extensionClassName, String methodName) {
        String adapterClassName = "LocalExtensionAdapter" + classIndex.addAndGet(1);
        return "public class " + SpringLocalAdapterFactory.class.getPackage().getName() + "." + adapterClassName +
                " extends " + ExtensionAdapter.class.getName() + "{\n " +
                "\n" +
                "    private " + extensionClassName + " extension;\n" +
                "    \n" +
                "    public " + adapterClassName + "(Object service){\n" +
                "        extension = (" + extensionClassName + ")service;\n" +
                "    }\n" +
                "\n" +
                "    public static " + SpringLocalAdapterFactory.class.getPackage().getName() + "." + adapterClassName + " newInstance(Object service){\n" +
                "        return new " + SpringLocalAdapterFactory.class.getPackage().getName() + "." + adapterClassName + "(service);\n" +
                "    }\n" +
                "\n" +
                "    public " + ExtensionResult.class.getName() + " doExtend(" + ExtensionParameter.class.getName() + " param) {\n" +
                "        return (" + ExtensionResult.class.getName() + ")extension." + methodName + "(param);\n" +
                "    }\n" +
                "    \n" +
                "}";
    }

}
