package com.util;

import com.BaseTest;
import com.yd.common.util.format.DictionaryFormatter;
import com.yd.common.util.format.FormatterUtil;
import com.yd.entity.GoodsInfo;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Yd on  2018-01-27
 * @Description：
 **/
public class FormatterTest extends BaseTest {
    @Resource(name = "format.type")
    private DictionaryFormatter typeFormatter;


    @Test
    public void test() {
        String key = "1";
        String va1 = typeFormatter.format(key);
        System.out.println(va1);
    }

    @Test
    public void testFormat() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setType("draft");
        String formatDesc = "{format.goods.status:type},{format.type:goodStatus}";
        FormatterUtil.format(formatDesc, goodsInfo);
        System.out.println(goodsInfo);
    }

}
