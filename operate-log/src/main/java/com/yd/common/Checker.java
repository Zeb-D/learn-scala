package com.yd.common;

import com.yd.entity.User;
import com.yd.entity.User2;

/**
 * @author Yd on  2018-01-16
 * @Description：
 **/
public class Checker {

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T receiveMessage() {
        Object o = new User2(12, "11", "1234","12345");
        return (T) o;
    }
}
