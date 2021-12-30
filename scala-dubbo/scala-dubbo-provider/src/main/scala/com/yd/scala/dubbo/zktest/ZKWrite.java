package com.yd.scala.dubbo.zktest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试下往zk某个路径下高并发写入多子路径
 *
 * @author created by Zeb灬D on 2021-12-09 10:19
 */
public class ZKWrite {
    public static final String ZK_PATH = "/zktest";

    public static void main(String[] args) throws Exception {
        int threads = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads);
        for (int i = 0; i < threads; i++) {
            final int j = i;
            Thread t = new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    final String path = ZK_PATH + "/" + j;
                    ZkConfig.zkClient.createEphemeral(path);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            t.setName("t" + i);
            t.start();
        }
        System.out.println("a");
        cyclicBarrier.isBroken();
        System.out.println("b");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
