package com.yd.scala.hello;

import java.util.HashMap;

/**
 * @author created by Zeb-D on 2019-08-15 12:54
 */
public class Test {
    public static void main(String[] args) {
        HelloService helloService = new HelloService() {
        };
        System.out.println(System.identityHashCode(helloService));
        User user = new User() {
            {
                setId("11234");
            }
        };
        HashMap map = new HashMap() {
            protected void sleep() {

            }
        };
        HashMap map1 = new HashMap() {{
            put(1, 2);
        }};


    }

    static abstract class User {
        private String id;

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
