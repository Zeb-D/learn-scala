package com.yd.scala.hello.extension.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Zeb灬D
 * @date 2021/5/21 5:25 下午
 */

public class ScheduleManager {
    private final static ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(5);
    private final static Map<String,ScheduledFuture<?>> futureMap = new ConcurrentHashMap<>();
    private final static AtomicLong futureId = new AtomicLong(1L);

    static {
        scheduleService.scheduleAtFixedRate(ScheduleManager::clearFuture,1,1,TimeUnit.MINUTES);
    }

    public static String addDelayTask(Runnable command,long delay, TimeUnit unit){
        String id = String.valueOf(futureId.addAndGet(1));
        ScheduledFuture<?> future = scheduleService.schedule(command, delay, unit);
        futureMap.put(id,future);
        return id;
    }

    public static String addScheduledTask(Runnable command,long initialDelay, long period, TimeUnit unit){
        String id = String.valueOf(futureId.addAndGet(1));
        ScheduledFuture<?> future = scheduleService.scheduleAtFixedRate(command, initialDelay, period, unit);
        futureMap.put(id,future);
        return id;
    }

    public static void cancelTask(String taskId){
        ScheduledFuture<?> future = futureMap.get(taskId);
        if(future == null) {
            return;
        }
        if(future.isCancelled()){
            futureMap.remove(taskId);
            return;
        }
        future.cancel(false);
    }

    private static void clearFuture(){
        List<String> ids = new LinkedList<>(futureMap.keySet());
        for(String id : ids){
            ScheduledFuture<?> future = futureMap.get(id);
            if(future == null) {
                continue;
            }
            if(future.isCancelled()){
                futureMap.remove(id);
            }
        }
    }

    public static void shutdown(){
        scheduleService.shutdown();
    }
}
