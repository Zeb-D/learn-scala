package com.yd.scala.classmexer.instrument;

import java.lang.instrument.Instrumentation;

/**
 * 需要用maven插件打一个agent attachment jar，不是这种-javaagent=:
 * <plugin>
 *                 <groupId>org.apache.maven.plugins</groupId>
 *                 <artifactId>maven-jar-plugin</artifactId>
 *                 <version>2.5</version>
 *                 <configuration>
 *                     <archive>
 *                         <manifestEntries>
 *                             <premain-class>com.yd.scala.classmexer.instrument.TestAgent</premain-class>
 *                             <Agent-Class>com.yd.scala.classmexer.instrument.TestAgent</Agent-Class>
 *                             <Can-Redefine-Classes>true</Can-Redefine-Classes>
 *                             <Can-Retransform-Classes>true</Can-Retransform-Classes>
 *                         </manifestEntries>
 *                     </archive>
 *                 </configuration>
 *             </plugin>
 * @author created by Zeb灬D on 2021-11-16 17:11
 */
public class TestAgent {

    public static void premain(String args, Instrumentation instr) {
        agentmain(args, instr);
    }


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
