package com.yd.scala.hello;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 存放这个用户相关的信息
 *
 * @author created by Zeb灬D on 2021-08-18 16:45
 */
@Slf4j
public class MallSessionContext {

    public static final ThreadLocal<MallSession> TL_SESSION = ThreadLocal.withInitial(() -> new MallSession());

    private final static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private final static ConcurrentHashMap<Object, ThreadLocal<MallSession>> TO_CLEAR_CONTEXT_MAP = new ConcurrentHashMap<>(64);
    private final static AtomicLong COUNT_CLEAR = new AtomicLong(0);

    public static void main(String[] args) {
        new Thread(() -> {
            registerContext(null, "a", "c", "d");
        }).start();
        new Thread(() -> {
            registerContext("b", "b", "b", "b");
        }).start();
        System.out.println(getNameSpace());
    }

    public static final void registerContext(String nameSpace, String site, String userName, String uid) {
        TL_SESSION.get().setNameSpace(nameSpace);
        TL_SESSION.get().setSite(site);
        TL_SESSION.get().setUid(uid);
        TL_SESSION.get().setUserName(userName);

        synchronized (TO_CLEAR_CONTEXT_MAP) {
            Object threadLocalMap = getThreadLocalMap(Thread.currentThread());
            if (threadLocalMap != null) {
                TO_CLEAR_CONTEXT_MAP.put(threadLocalMap, TL_SESSION);
                TO_CLEAR_CONTEXT_MAP.putIfAbsent(threadLocalMap, TL_SESSION);
            }else {
                System.out.println("11111111");
            }
        }
    }

    private final static Object getThreadLocalMap(Thread t) {
        try {
            Field field = t.getClass().getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            return threadLocalMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String getNameSpace() {
        return TL_SESSION.get().getNameSpace();
    }

    public final static String getSite() {
        return TL_SESSION.get().getSite();
    }

    public final static String getUid() {
        return TL_SESSION.get().getUid();
    }

    public final static String getUserName() {
        return TL_SESSION.get().getUserName();
    }

    static {
        scheduledExecutorService.scheduleAtFixedRate(() -> CLEAR_TL(), 30, 30, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> printThreadLocalInfo(), 30, 30, TimeUnit.SECONDS);
    }

    private static final void printThreadLocalInfo() {
        log.info("当前累计清理 COUNT_CLEAR:{},Map.Size:{}", COUNT_CLEAR.get(), TO_CLEAR_CONTEXT_MAP.size());
    }

    /**
     * 清理对应 Thread 的副本，不需要调用 ThreadLocal#remove
     */
    private static final int CLEAR_TL() {
        int count;
        //这个清理 要保证这些都被清理了
        synchronized (TO_CLEAR_CONTEXT_MAP) {
            count = TO_CLEAR_CONTEXT_MAP.size();
            TO_CLEAR_CONTEXT_MAP.keySet().stream().filter(Objects::nonNull).forEach(
                    threadLocalMap -> {
                        //将副本清理
                        try {
                            Method m = threadLocalMap.getClass().getDeclaredMethod("remove", ThreadLocal.class);
                            //remove
                            m.setAccessible(true);
                            m.invoke(threadLocalMap, TO_CLEAR_CONTEXT_MAP.get(threadLocalMap));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
            TO_CLEAR_CONTEXT_MAP.clear();
            COUNT_CLEAR.addAndGet(count);
        }
        return count;
    }


    @Setter
    @Getter
    public static class MallSession {
        /**
         * 租户id，对应 一个大B的id
         */
        private String nameSpace;
        /**
         * 站点，可以用户自己
         */
        private String site;

        /**
         * 用户名称，不分用户角色
         */
        private String userName;

        /**
         * 用户标示，可以是SaaS 用户的id
         */
        private String uid;
    }
}
