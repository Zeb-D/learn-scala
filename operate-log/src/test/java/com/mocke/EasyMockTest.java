package com.mocke;

import com.yd.mock.Class1Mocked;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Yd on  2018-01-17
 * @Description：
 **/
public class EasyMockTest {

    @Test
    public void testMockMethod() {
        Class1Mocked obj = createMock(Class1Mocked.class);

        expect(obj.hello("z3")).andReturn("hello l4");
        replay(obj);

        String actual = obj.hello("z3");
        assertEquals("hello l4", actual);

        verify(obj);
    }
}
