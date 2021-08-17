package com.yd.scala.hello;

import sun.misc.Unsafe;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * AtomicLongFieldUpdater 一定要volatile 修饰
 *
 * @author created by Zeb灬D on 2021-08-11 15:57
 */
public class AtomicLongFieldUpdaterTest {
    private volatile int a = 0;
    protected volatile long cachedPreparedStatementCount = 0L;
    private volatile long cachedPreparedStatementDeleteCount = 0L;
    volatile long cachedPreparedStatementMissCount = 0L;
    final static AtomicLongFieldUpdater<AtomicLongFieldUpdaterTest> cachedPreparedStatementCountUpdater = AtomicLongFieldUpdater.newUpdater(AtomicLongFieldUpdaterTest.class, "cachedPreparedStatementCount");
    final static AtomicLongFieldUpdater<AtomicLongFieldUpdaterTest> cachedPreparedStatementDeleteCountUpdater = AtomicLongFieldUpdater.newUpdater(AtomicLongFieldUpdaterTest.class, "cachedPreparedStatementDeleteCount");
    final static AtomicLongFieldUpdater<AtomicLongFieldUpdaterTest> cachedPreparedStatementMissCountUpdater = AtomicLongFieldUpdater.newUpdater(AtomicLongFieldUpdaterTest.class, "cachedPreparedStatementMissCount");

    private static final sun.misc.Unsafe UNSAFE = getUnsafe();
    private static final long cachedPreparedStatementDeleteCountOffset;

    static {
        try {
            Class<?> tk = AtomicLongFieldUpdaterTest.class;
            cachedPreparedStatementDeleteCountOffset = UNSAFE.objectFieldOffset(tk.getDeclaredField("cachedPreparedStatementCount"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception {
        //输出filed偏移量
        System.out.println(UNSAFE.objectFieldOffset(AtomicLongFieldUpdaterTest.class.getDeclaredField("a")));
        System.out.println(UNSAFE.objectFieldOffset(AtomicLongFieldUpdaterTest.class.getDeclaredField("cachedPreparedStatementCount")));
        System.out.println(UNSAFE.objectFieldOffset(AtomicLongFieldUpdaterTest.class.getDeclaredField("cachedPreparedStatementDeleteCount")));
        System.out.println(UNSAFE.objectFieldOffset(AtomicLongFieldUpdaterTest.class.getDeclaredField("cachedPreparedStatementMissCount")));
        System.out.println(UNSAFE.addressSize());


        AtomicLongFieldUpdaterTest t = new AtomicLongFieldUpdaterTest();
        System.out.println(cachedPreparedStatementCountUpdater.get(t));
        System.out.println(cachedPreparedStatementDeleteCountOffset);
        System.out.println(UNSAFE.getAndSetLong(t, cachedPreparedStatementDeleteCountOffset, 2));
        System.out.println(UNSAFE.getAndSetLong(t, cachedPreparedStatementDeleteCountOffset, 2));
        System.out.println(Reflection.getCallerClass(1));
        System.out.println(Reflection.getCallerClass(2));
    }

    //方法调用在java中是通过栈帧的出栈入栈实现的， 栈帧是方法执行时的数据结构，
    // 包括局部变量表， 操作数栈等。getCallerClass(int n) 就是返回 跳过n 个栈帧后，
    // 定义这个捏的方法的类。  getCallerClass(2) 返回的永远是我们的代码中调用getUnsafe 的那个类（n = 0,返回的是 sun.reflect.Reflection. n=1 , 返回的是 Unsafe）。
    // 而我们所写的类的加载器是 AppClassLoader 。java加载器使用的双亲委派模型，保证了只有rt.jar 等class 是使用的启动类加载器，即getClassLoader返回null。
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            // 反射 的调用栈是没有ClassLoader，是因为rt.jar 等class 是使用的启动类加载器？？？
            return (Unsafe) field.get(null);
        } catch (Exception e) {
        }
        return null;
    }
}
