package com.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Created by vlad on 23.02.17.
 */
public class MyServicePrivateTest {
    MyService myService;
    @Before
    public void init() {
        myService = new MyService();
    }

    @Test
    public void getIterationCount() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=MyService.class.getDeclaredMethod("getIterationCount");
        final int expected=241;
        method.setAccessible(true);
        assertEquals(expected,method.invoke(myService));
    }

    @Test
    public void func() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final double expectedOne=5.195;
        final double expectedTwo=5.482;

        Method method=MyService.class.getDeclaredMethod("func",double.class);
        method.setAccessible(true);
        assert (Double.isInfinite((Double) method.invoke(myService,0)));
        assertEquals(expectedOne,(Double) method.invoke(myService,180),0.001);
        assertEquals(expectedTwo,(Double)method.invoke(myService,240),0.001);
    }
}
