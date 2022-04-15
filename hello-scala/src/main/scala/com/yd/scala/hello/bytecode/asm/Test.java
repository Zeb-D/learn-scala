package com.yd.scala.hello.bytecode.asm;

import com.yd.scala.hello.bytecode.Base;

/**
 * @author created by Zeb灬D on 2021-11-16 15:33
 */
public class Test {
    public static void main(String[] args) {
        //可以先执行下，这段代码，然后在执行Generator，再到这里来
        Base base = new Base();
        base.process();
    }
}
