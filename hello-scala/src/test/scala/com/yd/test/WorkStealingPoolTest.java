package com.yd.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author created by Zeb灬D on 2021-10-11 11:50
 */
public class WorkStealingPoolTest {
    // 线程数
    private static final int threads = 10;
    // 用于计数线程是否执行完成
    CountDownLatch countDownLatch = new CountDownLatch(threads);


    @Test
    public void test1() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//            Callable 带返回值
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }


    @Test
    public void test3() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//          Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(new Callable<String>() {
                /**
                 * call
                 * @return currentThreadName
                 */
                @Override
                public String call() {
                    return Thread.currentThread().getName();
                }
            });
            executorService.submit(new Thread(futureTask));
            System.out.println(futureTask.get());
        }
        System.out.println("---- end ----");
    }


    @Test
    public void testForkJoinPool() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        PiEstimateTask task = new PiEstimateTask(0, 1_000_000_000, 10_000_000);
        Future<Double> future = forkJoinPool.submit(task); // 不阻塞

        double pi = future.get();
        System.out.println("π 的值：" + pi);
        System.out.println("future 指向的对象是 task 吗：" + (future == task));

        forkJoinPool.shutdown(); // 向线程池发送关闭的指令
    }

    // 求PI 的双精度，采用Fork/Join方式
    class PiEstimateTask extends RecursiveTask<Double> {

        private final long begin;
        private final long end;
        private final long threshold; // 分割任务的临界值

        public PiEstimateTask(long begin, long end, long threshold) {
            this.begin = begin;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Double compute() {  // 实现 compute 方法
            if (end - begin <= threshold) {  // 临界值之下，不再分割，直接计算

                int sign; // 符号，多项式中偶数位取 1，奇数位取 -1（位置从 0 开始）
                double result = 0.0;

                for (long i = begin; i < end; i++) {
                    sign = (i & 1) == 0 ? 1 : -1;
                    result += sign / (i * 2.0 + 1);
                }

                return result * 4;
            }

            // 分割任务
            long middle = (begin + end) / 2;
            PiEstimateTask leftTask = new PiEstimateTask(begin, middle, threshold);
            PiEstimateTask rightTask = new PiEstimateTask(middle, end, threshold);

            leftTask.fork();  // 异步执行 leftTask
            rightTask.fork(); // 异步执行 rightTask

            double leftResult = leftTask.join();   // 阻塞，直到 leftTask 执行完毕返回结果
            double rightResult = rightTask.join(); // 阻塞，直到 rightTask 执行完毕返回结果

            return leftResult + rightResult; // 合并结果
        }

    }
}
