package com.yd.scala.hello.handler.protocol;

public class DefaultProtocol implements Protocol {

    private final Hider hider = new DefaultHider();

    @Override
    public Hider getHider() {
        return hider;
    }
}
