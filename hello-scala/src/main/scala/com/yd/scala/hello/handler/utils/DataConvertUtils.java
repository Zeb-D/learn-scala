package com.yd.scala.hello.handler.utils;

import com.alibaba.fastjson.JSON;
import com.yd.scala.hello.extension.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataConvertUtils {

    public static void main(String[] args) {
        test();
    }

    /**
     * @version 1.0
     * @Description: 邮箱统一处理 邮箱替换 2-3 前边显示1，4-n 前边三位+4个*
     * @Date 2019-10-10 15:10
     */
    public static String emailReplace(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        String emailReplaced;
        String temp = email;
        String[] emails = email.split("@");
        int size = emails[0].length();
        log.info("temp :{}", temp);
        if (size <= 1) {
            emailReplaced = "*";
        } else if (size >= 2 && size <= 4) {
            log.info("size :{}", size);
            String t1 = temp.substring(0, 1);
            emailReplaced = t1;
            emailReplaced += "****@";
            emailReplaced += emails[1];
//            emailReplaced = temp.replaceAll(temp.substring(1, temp.lastIndexOf("@")), "****");
        } else {
            emailReplaced = temp.replaceAll(temp.substring(3, temp.lastIndexOf("@")), "****");
        }

        return emailReplaced;
    }

    /**
     * 账号替换脱敏
     */

    public static String accountReplace(String account) {
        String accountReplaced;
        if (StringUtils.isBlank(account)) {
            return "";
        }
        if (isEmail(account)) {
            accountReplaced = emailReplace(account);
        } else {
            accountReplaced = mobileReplace(account);

        }

        return accountReplaced;
    }

    /**
     * @version 1.0
     * @Description: 少于等于6 前后各一个，多6，前三后三
     * @Date 2019-10-11 14:59
     */
    public static String deviceIdReplace(String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            return "";
        }
        String deviceReplaced;
        int length = deviceId.length() - 1;
        log.info("device :{}", length);
        if (length <= 1) {
            deviceReplaced = "*";
        } else if (length >= 2 && length <= 6) {
            deviceReplaced = deviceId.replaceAll("(?<=\\w{1})\\w(?=\\w{1})", "*");
        } else {
            deviceReplaced = deviceId.replaceAll(deviceId.substring(5, (deviceId.length() - 4)), "****");
        }
        return deviceReplaced;

    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则表达式
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     * 超出12位 区号单独显示，手机号单独脱敏
     *
     * @param mobile 用户名
     * @return 替换后的用户名
     */
    public static String mobileReplace(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return "";
        }
        String mobileReplaced;
        String mobileNum;
        String[] mobiles;
        String code = null;
        int length;
        if (mobile.contains("-")) {
            mobiles = mobile.split("-");
            code = mobiles[0];
            mobileNum = mobiles[1];
            length = mobileNum.length();
        } else {
            length = mobile.length();
            mobileNum = mobile;
        }


        log.info("mobile :{}", mobileNum);
        if (length <= 1) {
            mobileReplaced = "*";
        } else if (length == 2) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{0})\\d(?=\\d{1})");
        } else if (length >= 3 && length <= 6) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{1})\\d(?=\\d{1})");
        } else if (length == 7) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{1})\\d(?=\\d{2})");
        } else if (length == 8) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{1})\\d(?=\\d{3})");
        } else if (length == 9) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{1})\\d(?=\\d{4})");
        } else if (length == 10) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{2})\\d(?=\\d{4})");
        } else if (length == 11) {
            mobileReplaced = replaceAction(mobileNum, "(?<=\\d{3})\\d(?=\\d{4})");
        } else {
            mobileReplaced = mobileNum.replaceAll(mobileNum.substring(3, (mobileNum.length() - 4)), "****");
        }
        log.info("code :{},mobileReplaced:{}", code, mobileReplaced);
        if (StringUtils.isNotBlank(code)) {
            mobileReplaced = code + "-" + mobileReplaced;
        }
        return mobileReplaced;

    }

    /**
     * @version 1.0
     * @Description: 判断是否是邮箱
     * @Date 2019-10-11 17:18
     */

    public static boolean isEmail(String email) {
        Boolean condition = (email == null || "".equals(email));
        if (condition) {
            return false;
        }
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    /**
     * 地址替换方式 统一替换为4个*
     *
     * @param address
     * @return
     */

    public static String addressReplace(String address) {

        if (StringUtils.isBlank(address)) {
            return "";
        }
        String replace = "****";
        return replace;

    }


    public static void test() {
        String text = "86-12333444";
        String text2 = "123456789833";
        String email = "aa@bccto.me";
        String deviceId = "ebc73e0eb0ed5a54e2y1tc";
        log.info(JSON.toJSONString("{\"isTamper\":true}"));
//        log.info("手机号处理：{}", mobileReplace(text2));
        log.info("手机号处理：{}", mobileReplace(text));
//        log.info("账号处理：{}", emailReplace(email));
//        log.info("设备处理：{}", deviceIdReplace(deviceId));
        log.info("是否是邮箱：{}", isEmail(email));
        log.info("账号替换：{}", accountReplace(email));
//        log.info("账号邮箱替换：{}", accountReplace(email));
//        log.info("地址替换：{}", addressReplace(email));

    }


}
