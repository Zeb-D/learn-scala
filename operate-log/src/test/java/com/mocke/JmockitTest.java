package com.mocke;

import com.yd.mock.Class1Mocked;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Yd on  2018-01-17
 * @Description：
 **/
public class JmockitTest {
    @Mocked  //用@Mocked标注的对象，不需要赋值，jmockit自动mock
    Class1Mocked obj;

    @Test
    public void testMockNormalMethod1() {
        new Expectations() {
            {
                obj.hello("z3");
                returns("hello l4", "hello w5");
                obj.hello("张三");
                result="hello 李四";
            }
        };

        assertEquals("hello l4", obj.hello("z3"));
        assertEquals("hello w5", obj.hello("z3"));
        assertEquals("hello 李四", obj.hello("张三"));

        try {
            obj.hello("z3");
        } catch (Throwable e) {
            System.out.println("第三次调用hello(\"z3\")会抛出异常");
        }
        try {
            obj.show();
        } catch (Throwable e) {
            System.out.println("调用没有在Expectations块中定义的方法show()会抛出异常");
        }
    }

    @Test
    public void testMockNormalMethod() throws IOException {
        final Class1Mocked obj = new Class1Mocked();//也可以不用@Mocked标注，但需要final关键字
        new NonStrictExpectations(obj) {
            {
                obj.hello("z3");
                result = "hello l4";
            }
        };

        assertEquals("hello l4", obj.hello("z3"));
        assertEquals("hello 张三", obj.hello("张三"));

        new Verifications() {
            {
                obj.hello("z3");
                times = 1;
                obj.hello("张三");
                times = 1;
            }
        };
    }

}
