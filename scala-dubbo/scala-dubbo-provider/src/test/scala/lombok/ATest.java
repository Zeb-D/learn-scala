package lombok;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author created by Zeb灬D on 2021-08-26 15:58
 */
public class ATest {
    @Test
    public void test() {
        AVO v = new AVO();
        v.setName("aaa");
        v.setAmount(new BigDecimal("2.01"));
        v.setAmount1(new BigDecimal("1.01"));

        ListAVO l = new ListAVO();
        l.setAge(111);
        List<AVO> aa = new ArrayList<>();
        l.setRes(aa);
        aa.add(v);
        AVO v1 = new AVO();
        aa.add(v1);
        v1.setName("bbb");
        v1.setAmount(new BigDecimal(3.01D));
        v1.setAmount1(new BigDecimal("5.01"));
        System.out.println(AConverter.INSTANCE.toRes(l));
//        System.out.println(AConverter.INSTANCE.toRes(v));
        System.out.println(new DecimalFormat("0.00").format(new BigDecimal(3.01D)));
    }
}
