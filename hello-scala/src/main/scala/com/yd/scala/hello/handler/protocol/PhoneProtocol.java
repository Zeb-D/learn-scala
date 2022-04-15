package com.yd.scala.hello.handler.protocol;

public class PhoneProtocol implements Protocol {
    private final Hider hider = new PhoneHider();

    @Override
    public Hider getHider() {
        return hider;
    }
}
