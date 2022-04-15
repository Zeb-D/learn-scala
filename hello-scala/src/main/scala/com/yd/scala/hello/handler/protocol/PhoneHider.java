package com.yd.scala.hello.handler.protocol;


import com.yd.scala.hello.handler.utils.DataConvertUtils;

public class PhoneHider implements Hider {
    @Override
    public String hide(String phone) {
        return DataConvertUtils.mobileReplace(phone);
    }
}
