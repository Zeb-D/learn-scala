package com.yd.scala.hello.bytecode.instrument;

import java.lang.management.ManagementFactory;

/**
 * 期望的效果是:在一个持续运行并已经加载了所有类的 JVM 中，还 能利用字节码增强技术对其中的类行为做替换并重新加载。
 * 为了模拟这种情况，我 们将 Base 类做改写，在其中编写 main 方法，每五秒调用一次 process() 方法，
 * 在 process() 方法中输出一行“process”。
 */
public class Base {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        // 打印当前 Pid
        System.out.println("pid:" + s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }

    public static void process() {
        System.out.println("process");
    }
}