package com.yd.scala.hello;

import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class MaskValueFilter implements ValueFilter {
    public static final ValueFilter MASK = new MaskValueFilter();
    //中文
    static final Pattern p = Pattern.compile("^[\u4e00-\u9fa5]+$");
    static final Pattern pp = Pattern.compile("^discount_price (asc|desc)$");

    public static final boolean containsAllChinese(String str) {
        Matcher m = p.matcher(str);
        if (!m.find()) {
            return false;
        }
        return true;
    }

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
            return mask(v, mask.maskChinese(), startMaskIndex, endMaskIndex);
        } catch (Exception e) {
            log.warn("e:{}", e);
            return value;
        }
    }

    public static final String mask(String v, boolean maskChinese, int startMaskIndex, int endMaskIndex) {
        if (containsAllChinese(v) && maskChinese) {
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
    }

    private static final boolean discountPriceOrder(String orderBy) {
        if (StringUtils.isEmpty(orderBy)) {
            return false;
        }
        return pp.matcher(orderBy).find();
    }

    public static void main(String[] args) {
        System.out.println(discountPriceOrder(""));
        System.out.println(discountPriceOrder("discount_price asc ,12234"));
        System.out.println(discountPriceOrder("discount_price asc"));
        System.out.println(discountPriceOrder("official_status desc,gmt_modified desc"));
        System.out.println(discountPriceOrder("discount_price 1asc"));
        System.out.println(discountPriceOrder("discount_price desc"));
        System.out.println(discountPriceOrder("12234 discount_price desc"));
        System.out.println(discountPriceOrder("discount_price desc asc"));
//        System.out.println(MaskValueFilter.containsAllChinese("啊啊11121"));
//        System.out.println(MaskValueFilter.containsAllChinese("马啊啊"));
//        System.out.println(MaskValueFilter.containsAllChinese("333333"));
//        System.out.println(MaskValueFilter.mask("马333333", true, 1, 1));
//        System.out.println(MaskValueFilter.mask("马啊啊", true, 1, 1));
//        System.out.println(MaskValueFilter.mask("333333", true, 1, 1));
    }
}