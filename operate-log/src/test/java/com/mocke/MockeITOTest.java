package com.mocke;

import com.yd.mock.Class1Mocked;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Yd on  2018-01-17
 * @Description：
 **/
public class MockeITOTest {

    @Test
    public void testMockMethod() {
        Class1Mocked obj=mock(Class1Mocked.class);

        when(obj.hello("z3")).thenReturn("hello l4");

        String actual=obj.hello("z3");
        assertEquals("hello l4",actual);

        verify(obj).hello("z3");
        verify(obj,times(1)).hello("z3"); //可以加参数验证次数
//        在验证阶段可以通过增加参数(time(int)、atLeastOnce()、atLeast(int)、never()等)来精确验证调用次数。
    }

    @Test
    public void testMockMethodInOrder() {
        Class1Mocked objOther = mock(Class1Mocked.class);
        Class1Mocked objCn = mock(Class1Mocked.class);

        when(objOther.hello("z3")).thenReturn("hello l4");
        when(objCn.hello("z3")).thenReturn("hello 张三");

        String other = objOther.hello("z3");
        assertEquals("hello l4", other);
        String cn = objCn.hello("z3");
        assertEquals("hello 张三", cn);

        InOrder inOrder = inOrder(objOther, objCn); //此行并不决定顺序，下面的两行才开始验证顺序
        inOrder.verify(objOther).hello("z3");
        inOrder.verify(objCn).hello("z3");
    }

    @Test
    public void testSkipExpect() {
        Class1Mocked obj = mock(Class1Mocked.class);

        assertEquals(null, obj.hello("z3"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).show();
    }

    @Test
    public void testCallRealMethod () {
//        hello("z3")时会执行原有的代码，而执行hello("l4")时则是返回默认值null且没有输出打印，执行show()同样没有输出打印
        Class1Mocked obj = mock(Class1Mocked.class);
        doCallRealMethod().when(obj).hello("z3");

        assertEquals("hello z3",obj.hello("z3"));
        assertEquals(null,obj.hello("l4"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).hello("l4");
        verify(obj).show();
    }

    @Test
    public void testSpy() {
        Class1Mocked obj = spy(new Class1Mocked());

        doNothing().when(obj).show();

        assertEquals("hello z3",obj.hello("z3"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).show();
    }

    @Test
    public void testSpy2() {
        Class1Mocked obj = spy(new Class1Mocked());

        when(obj.hello("z3")).thenReturn("hello l4");

        assertEquals("hello l4",obj.hello("z3"));

        verify(obj).hello("z3");
    }

    @Test
    public void testSpy3() {
        Class1Mocked obj = spy(new Class1Mocked());

        doReturn("hello l4").when(obj).hello("z3");

        assertEquals("hello l4",obj.hello("z3"));

        verify(obj).hello("z3");
    }
}
