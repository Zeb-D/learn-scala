package com.yd.scala.hello.bytecode.instrument;

import java.lang.instrument.Instrumentation;

/**
 * @author created by Zeb灬D on 2021-11-16 17:11
 */
public class TestAgent {
    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
