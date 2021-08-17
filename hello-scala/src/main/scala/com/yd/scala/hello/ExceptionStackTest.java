package com.yd.scala.hello;

/**
 * 热点代码捕获不到异常栈
 *
 * <p>
 * 使用反射(不推荐)：使用反射调用代码 解释器只能在运行时获取方法无法优化成机器码
 * 本地调试(不适用线上)：本地通过添加debug断点更新字节码消除热点代码
 * -XX:-OmitStackTraceInFastThrow(不推荐)：打印异常需要对错误流上锁消耗性能 jdk1.6后对于频繁异常代码禁用栈信息输出
 * arthas(推荐)：使用arthas指令可以动态增强字节码刷新热点代码 支持表达式过滤可以复原字节码
 * </P>
 *
 * @author created by Zeb灬D
 */
public class ExceptionStackTest {

    /**
     * 服务调用次数
     */
    public static int time = 0;

    /**
     * 异常服务
     *
     * @param time
     * @throws Exception
     */
    public static final void service(int time) throws Exception {
        for (int i = 0; i < 1000; i++) {
            //回边操作 内部循环越多 越容易变成热点代码
        }
        ((ExceptionStackTest) null).toString();
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            time++;
            try {
                service(time);  //请求服务
            } catch (Exception e) {
                System.err.printf("服务器繁忙-%d\n", time);
                e.printStackTrace();
                if (e.getStackTrace().length == 0) {
                    Thread.sleep(1000);
                }
            }
            Thread.sleep(1);
        }
    }

    /**
     * final方法更加容易出现热点代码
     *
     * @return
     */
    @Override
    public final String toString() {
        return super.toString();
    }
}
