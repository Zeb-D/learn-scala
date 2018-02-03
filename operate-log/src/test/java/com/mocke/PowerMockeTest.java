package com.mocke;

import com.yd.mock.Class2Mocked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.*;
/**
 * @author Yd on  2018-01-17
 * @Description：
 **/
@RunWith(PowerMockRunner.class)
@PrepareForTest( { Class2Mocked.class })
public class PowerMockeTest {


    @Test
    public void testMockStaticMethod() {
        mockStatic(Class2Mocked.class);
        when(Class2Mocked.getDouble(1)).thenReturn(3);

        int actual = Class2Mocked.getDouble(1);
        assertEquals(3, actual);

        verifyStatic();
        Class2Mocked.getDouble(1);
    }

    @Test
    public void testMockPrivateMethod() throws Exception {
        Class2Mocked obj = mock(Class2Mocked.class);

        when(obj, "multiply3", 1).thenReturn(4);
        doCallRealMethod().when(obj).getTripleString(1);

        String actual = obj.getTripleString(1);
        assertEquals("4", actual);

        verifyPrivate(obj).invoke("multiply3", 1);
    }

    @Test
    public void testMockPrivateMethod2() throws Exception {
        Class2Mocked obj = spy(new Class2Mocked());
        when(obj, "multiply3", 1).thenReturn(4);

        String actual = obj.getTripleString(1);
        assertEquals("4", actual);

        verifyPrivate(obj).invoke("multiply3", 1);
    }

    @Test
    public void testStructureWhenPathDoesntExist() throws Exception {
        final String directoryPath = "mocked path";

        File directoryMock = mock(File.class);

        whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);
        when(directoryMock.exists()).thenReturn(true);

        File file=new File(directoryPath);
        assertTrue(file.exists());

        verifyNew(File.class).withArguments(directoryPath);
        verifyPrivate(directoryMock).invoke("exists");
    }
}
