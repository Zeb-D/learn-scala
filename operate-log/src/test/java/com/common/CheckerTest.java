package com.common;

import com.yd.common.Checker;
import com.yd.common.EnumSummry;
import com.yd.entity.User;
import com.yd.entity.User2;
import org.junit.Test;

/**
 * @author Yd on  2018-01-16
 * @Description：
 **/
public class CheckerTest {

    @Test
    public void testNotNull() {
        EnumSummry.LogOperType operType = Checker.checkNotNull(EnumSummry.LogOperType.ALL);
    }

    @Test
    public void test() {
        User user = Checker.receiveMessage();
        System.out.println(user);
    }
}
