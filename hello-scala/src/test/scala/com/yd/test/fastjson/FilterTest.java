package com.yd.test.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.common.hash.Hashing;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author created by Zeb灬D on 2021-09-18 11:14
 */
public class FilterTest {
    @Test
    public void TestFilter() {
        User u = new User();
        u.userName = "啊啊啊";
        u.email = "112121@qq.com";
        u.mobile = "1123123121312123";
        String uStr = JSONObject.toJSONString(u);
        System.out.println(uStr);
        System.out.println(JSONObject.toJSONString(u, new SimpleValueFilter()));

        System.out.println(containsAllChinese("1121asdasdzczxcxc"));
        System.out.println(containsAllChinese("啊啊啊啊啊啊啊"));
//        System.out.println(JSONObject.parseObject(uStr, User.class, new SimpleValueFilter()));
        System.out.println(Hashing.md5().hashString("7Ktm435F", StandardCharsets.UTF_8).toString());
    }

    //中文
    static final Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    static final boolean containsAllChinese(String str) {
        Matcher m = p.matcher(str);
        if (!m.find()) {
            return false;
        }
        return true;
    }

    class SimpleValueFilter implements ValueFilter {

        public Object process(Object object, String name, Object value) {
            if (object == null || value == null) {
                return value;
            }
            try {
                Field field = object.getClass().getDeclaredField(name);
                Mask mask = field.getAnnotation(Mask.class);
                if (mask == null) {
                    return value;
                }
                if (!(value instanceof String)) {
                    return value;
                }
                int startMaskIndex = mask.startMaskIndex();
                int endMaskIndex = mask.endMaskIndex();
                if (startMaskIndex == -1 && endMaskIndex == -1) {
                    return value;
                }
                String v = (String) value;
                if (containsAllChinese(v) && mask.maskChinese()) {
                    if (v.length() <= 3) {
                        return StringUtils.repeat("*", Math.max(v.length() - 1, 1)) + v.substring(v.length() - 1);
                    } else {
                        return StringUtils.repeat("*", Math.max(v.length() - endMaskIndex, endMaskIndex)) + v.substring(v.length() - endMaskIndex);
                    }
                }
                String startStr = "";
                if (v.length() > startMaskIndex && startMaskIndex > 0) {
                    startStr = v.substring(0, startMaskIndex);
                }
                String endStr = "";
                if (v.length() > endMaskIndex && endMaskIndex > 0) {
                    endStr = v.substring(v.length() - endMaskIndex);
                }
                return startStr + "***" + endStr;
            } catch (NoSuchFieldException e) {
                return value;
            }
        }
    }

    @Data
    class User {
        @Mask(startMaskIndex = 1, endMaskIndex = 2, maskChinese = true)
        private String userName;
        @Mask(startMaskIndex = 3, endMaskIndex = 2)
        private String mobile;
        @Mask(startMaskIndex = 3, endMaskIndex = 2)
        private String email;
    }


    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @Inherited
    @interface Mask {
        int startMaskIndex() default -1;

        int endMaskIndex() default -1;

        boolean maskChinese() default false;
    }
}
