package com.yd.scala.hello.handler.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class CommonUtil {

    public static String getSamePath(List<String> paths) {
        String sameStr = null;
        for (String path : paths) {
            if (StringUtils.isNotBlank(sameStr) && path.contains(sameStr)) {
                continue;
            }
            if (path.contains("[*]")) {
                int i = path.indexOf("[*]");
                sameStr = path.substring(0, i + 3);
            } else {
                if (path.contains("resultDO")) {
                    int i = path.indexOf("resultDO");
                    sameStr = path.substring(0, i + 8);
                }

            }
        }
        return sameStr;
    }


}
