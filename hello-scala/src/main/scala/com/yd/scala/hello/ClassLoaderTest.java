package com.yd.scala.hello;

import sun.misc.Launcher;
import sun.reflect.Reflection;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author created by Zeb灬D on 2021-06-28 11:00
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName(ClassA.class.getName());
        //以下代码不执行静态代码块
        Class<ClassB> clazz = (Class<ClassB>) ClassLoader.getSystemClassLoader().loadClass(ClassB.class.getName());
        Class<ClassB> clazz1 = (Class<ClassB>) ClassLoader.getSystemClassLoader().loadClass(ClassB.class.getName());
        //下面这个会报错，ExtClassLoader 加载不了，只加载自己负责路径的jar，双亲委派机制就是垃圾
        //Class<ClassB> clazz2 = (Class<ClassB>) Thread.currentThread().getContextClassLoader().getParent().loadClass(ClassB.class.getName());
        System.out.println("多次load是否一致1-->"+(clazz.equals(clazz1)));
        //初始化
        ClassB b = clazz.newInstance();
        System.out.println(b);

        System.out.println(Thread.currentThread().getContextClassLoader());//AppClassLoader
        System.out.println(Thread.currentThread().getContextClassLoader().getParent());//ExtClassLoader
        System.out.println(Thread.currentThread().getContextClassLoader().getParent().getParent());//null
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println("Object -> " + Object.class.getClassLoader());
        System.out.println(BOOTSTRAP_CLASSLOADER);
        System.out.println(Reflection.getCallerClass(0));
        System.out.println(Reflection.getCallerClass(1));
        System.out.println(Reflection.getCallerClass(1).getClassLoader());
        System.out.println(Launcher.getLauncher().getClass().getClassLoader());
    }

    public static class ClassA {
        private String name;

        static {
            System.out.println("aaaaa");
        }

        static {
            print("cccc");
        }

        public ClassA() {
            System.out.println("bbbb");
        }

        public static final void print(String str) {
            System.out.println(str);
        }
    }


    public static class ClassB {
        private String name;

        static {
            System.out.println("baaaaa");
        }

        static {
            print("bcccc");
        }

        public ClassB() {
            System.out.println("b-bbbb");
        }

        public static final void print(String str) {
            System.out.println(str);
        }
    }

    private static class BootstrapClassLoader extends ClassLoader {
        BootstrapClassLoader() {
            super(Object.class.getClassLoader());
        }
    }

    private static ClassLoader getBootstrapClassLoader() {
        if (BOOTSTRAP_CLASSLOADER == null) {
            Class var0 = ClassLoaderTest.class;
            synchronized(ClassLoaderTest.class) {
                if (BOOTSTRAP_CLASSLOADER == null) {
                    ClassLoader cl = null;
                    if (System.getSecurityManager() != null) {
                        cl = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
                            public ClassLoaderTest.BootstrapClassLoader run() {
                                return new ClassLoaderTest.BootstrapClassLoader();
                            }
                        });
                    } else {
                        cl = new ClassLoaderTest.BootstrapClassLoader();
                    }

                    BOOTSTRAP_CLASSLOADER = (ClassLoader)cl;
                }
            }
        }

        return BOOTSTRAP_CLASSLOADER;
    }
    private static volatile ClassLoader BOOTSTRAP_CLASSLOADER;
}
