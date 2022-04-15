package com.yd.scala.hello.handler.protocol;

import org.apache.commons.lang3.StringUtils;

public class DefaultHider implements Hider {
    @Override
    public String hide(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        final int len = value.length();
        if (len < 4) {
            return value.substring(0, 1) + "****";
        }
        return value.substring(0, 1) + "****" + value.substring(len - 1);
    }
}
