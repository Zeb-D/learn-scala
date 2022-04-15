package com.yd.scala.hello;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为什么会线程池 会导致主线程不能退出？
 * 分析？？DefaultThreadFactory， 设置了 t.setDaemon(false); 非守护线程
 *
 * @author created by Zeb灬D on 2021-08-26 10:40
 */
public class ScheduleTest {
    //    private final static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private final static ExecutorService executorService = Executors.newFixedThreadPool(3);

    static {
//        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread()), 10, TimeUnit.SECONDS);
        executorService.submit(() -> {
            System.out.println(Thread.currentThread());
        });
    }

    public static void main(String[] args) {
        System.out.println("aaa");
    }
}
