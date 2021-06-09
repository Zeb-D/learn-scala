package com.yd.scala.classmexer;

import java.lang.instrument.Instrumentation;

/**
 * @author created by Zeb灬D on 2020-07-31 20:49
 */
public class Agent {
    private static volatile Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instr) {
        instrumentation = instr;
    }

    protected static Instrumentation getInstrumentation() {
        Instrumentation instr = instrumentation;
        if (instr == null) {
            throw new IllegalStateException("Agent not initted");
        } else {
            return instr;
        }
    }

    private Agent() {
    }
}
