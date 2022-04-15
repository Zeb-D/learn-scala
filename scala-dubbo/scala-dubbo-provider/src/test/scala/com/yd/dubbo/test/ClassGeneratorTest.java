package com.yd.dubbo.test;

import com.yd.scala.dubbo.client.IHelloService;
import org.apache.dubbo.common.bytecode.ClassGenerator;
import org.apache.dubbo.common.bytecode.Proxy;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author created by Zeb灬D on 2022-01-19 14:12
 */
public class ClassGeneratorTest {
    private static final String PACKAGE_NAME = ClassGeneratorTest.class.getPackage().getName();

    @Test
    public void TestGeneratorCode() {
        Proxy proxy = getProxy(Thread.currentThread().getContextClassLoader(), IHelloService.class);
        System.out.println(proxy);
    }

    public Proxy getProxy(ClassLoader cl, Class<?> ics) {
        ClassGenerator ccp = null, ccm = null;
        String pkg = null;
        Proxy proxy = null;
        try {

            ccp = ClassGenerator.newInstance(cl);

            Set<String> worked = new HashSet<String>();
            List<Method> methods = new ArrayList<Method>();

            if (!Modifier.isPublic(ics.getModifiers())) {
                String npkg = ics.getPackage().getName();
                if (pkg == null) {
                    pkg = npkg;
                } else {
                    if (!pkg.equals(npkg))
                        throw new IllegalArgumentException("non-public interfaces from different packages");
                }
            }
            ccp.addInterface(ics);

            for (Method method : ics.getMethods()) {
                String desc = ReflectUtils.getDesc(method);
                if (worked.contains(desc))
                    continue;
                worked.add(desc);

                int ix = methods.size();
                Class<?> rt = method.getReturnType();
                Class<?>[] pts = method.getParameterTypes();

                StringBuilder code = new StringBuilder("Object[] args = new Object[").append(pts.length).append("];");
                for (int j = 0; j < pts.length; j++)
                    code.append(" args[").append(j).append("] = ($w)$").append(j + 1).append(";");
                code.append(" Object ret = handler.invoke(this, methods[" + ix + "], args);");
                if (!Void.TYPE.equals(rt))
                    code.append(" return ").append(asArgument(rt, "ret")).append(";");

                methods.add(method);
                ccp.addMethod(method.getName(), method.getModifiers(), rt, pts, method.getExceptionTypes(), code.toString());
            }

            if (pkg == null)
                pkg = PACKAGE_NAME;

            // create ProxyInstance class.
            String pcn = pkg + ".proxy" + 1;
            ccp.setClassName(pcn);
            ccp.addField("public static java.lang.reflect.Method[] methods;");
            ccp.addField("private " + InvocationHandler.class.getName() + " handler;");
            ccp.addConstructor(Modifier.PUBLIC, new Class<?>[]{InvocationHandler.class}, new Class<?>[0], "handler=$1;");
            ccp.addDefaultConstructor();
            Class<?> clazz = ccp.toClass();
            clazz.getField("methods").set(null, methods.toArray(new Method[0]));
            System.out.println(clazz.newInstance());

            // create Proxy class.
            String fcn = pkg + "." + 2;
            ccm = ClassGenerator.newInstance(cl);
            ccm.setClassName(fcn);
            ccm.addDefaultConstructor();
            ccm.setSuperClass(Proxy.class);
            ccm.addMethod("public Object newInstance(" + InvocationHandler.class.getName() + " h){ return new " + pcn + "($1); }");
            Class<?> pc = ccm.toClass();
            System.out.println(ccm);
            proxy = (Proxy) pc.newInstance();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            // release ClassGenerator
            if (ccp != null)
                ccp.release();
            if (ccm != null)
                ccm.release();
        }
        return proxy;
    }

    private static String asArgument(Class<?> cl, String name) {
        if (cl.isPrimitive()) {
            if (Boolean.TYPE == cl)
                return name + "==null?false:((Boolean)" + name + ").booleanValue()";
            if (Byte.TYPE == cl)
                return name + "==null?(byte)0:((Byte)" + name + ").byteValue()";
            if (Character.TYPE == cl)
                return name + "==null?(char)0:((Character)" + name + ").charValue()";
            if (Double.TYPE == cl)
                return name + "==null?(double)0:((Double)" + name + ").doubleValue()";
            if (Float.TYPE == cl)
                return name + "==null?(float)0:((Float)" + name + ").floatValue()";
            if (Integer.TYPE == cl)
                return name + "==null?(int)0:((Integer)" + name + ").intValue()";
            if (Long.TYPE == cl)
                return name + "==null?(long)0:((Long)" + name + ").longValue()";
            if (Short.TYPE == cl)
                return name + "==null?(short)0:((Short)" + name + ").shortValue()";
            throw new RuntimeException(name + " is unknown primitive type.");
        }
        return "(" + ReflectUtils.getName(cl) + ")" + name;
    }
}
