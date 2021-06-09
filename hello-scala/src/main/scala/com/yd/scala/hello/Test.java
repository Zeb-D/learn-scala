package com.yd.scala.hello;

import com.yd.scala.classmexer.MemoryUtil;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.HashMap;

/**
 * -javaagent:/Users/zouyongdong/IdeaProjects/my/learn-scala/scala-classmexer/target/scala-classmexer.jar
 *
 * @author created by Zeb-D on 2019-08-15 12:54
 */
public class Test {
    public static void main(String[] args) throws Exception {
        User user = new User() {
            {
                setId("");//setId(""); 这种情况是不一样的
            }
        };
        //  这个对象在内存的总体大小
        System.out.println(ObjectSizeCalculator.getObjectSize(user));
        // 打印对象的shallow size
        System.out.println("Shallow Size: " + MemoryUtil.memoryUsageOf(user) + " bytes");
        // 打印对象的 retained size
        System.out.println("Retained Size: " + MemoryUtil.deepMemoryUsageOf(user) + " bytes");
        System.out.println(user);
        System.out.println(user.i);
        System.in.read();
    }


    {
        HelloService helloService = new HelloService() {
        };
        System.out.println(System.identityHashCode(helloService));

        HashMap map = new HashMap() {
            public void sleep() {

            }
        };
        HashMap map1 = new HashMap() {{
            put(1, 2);
        }};
    }

    // 对象A： 对象头12B + 内部对象s引用 4B + 内部对象i 基础类型int 4B + 对齐 4B = 24B
// 内部对象s 对象头12B + 2个内部的int类型8B + 内部的char[]引用 4B + 对齐0B = 24B
// 内部对象str的内部对象char数组 对象头12B + 数组长度4B + 对齐0B = 16B
// 总： 对象A 24+ 内部对象s 24B + 内部对象s的内部对象char数组 16B =64B
    static abstract class User {
        private String id;
        double i = 0;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static class Parent extends User {

    }


}
