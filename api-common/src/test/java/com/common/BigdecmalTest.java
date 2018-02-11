package com.common;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Yd on  2018-01-19
 * @Description：
 **/
public class BigdecmalTest {
    @Test
    public void testBigDecimal() {
        BigDecimal decimal = new BigDecimal("123654.12365");
        DecimalFormat format = new DecimalFormat("#0");
        format.format(decimal);
        Integer a = Integer.parseInt(decimal.toString());
        System.out.println(a);
    }
}
