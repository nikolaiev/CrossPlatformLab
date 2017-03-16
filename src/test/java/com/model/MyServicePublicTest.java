package com.model;

import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by vlad on 23.02.17.
 */
public class MyServicePublicTest {
    MyService myService;

    @Before
    public void init() {
        myService = new MyService();
        myService.findFunctionValues();
    }

    @Test
    public void findSum() throws Exception {
        final double expected=-1.919;
        MyService myService;
        myService = new MyService(1,2,1,1);
        myService.findFunctionValues();
        assertEquals(expected,myService.findSum(),0.001);
    }

    @Test
    public void findAver() throws Exception {
        MyService myService;
        myService = new MyService(1,2,1,1);
        myService.findFunctionValues();

        Method method=MyService.class.getDeclaredMethod("getIterationCount");
        method.setAccessible(true);

        assertEquals(myService.findSum()/(Integer) method.invoke(myService),myService.findAver(),0.001);
    }


    @Test
    public void findMaxFuncElemIndex() throws Exception {
        final int expected=180;
        Method method=MyService.class.getDeclaredMethod("getIterationCount");
        method.setAccessible(true);
        assertEquals(expected,myService.findMaxFuncElemIndex());
    }

    @Test
    public void findMinFuncElemIndex() throws Exception {
        final int expectedMinValue=-1;
        int actualMinValue = myService.findMinFuncElemIndex();
        assertEquals(expectedMinValue,actualMinValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getY() throws Exception {
        final int index=-1;
        myService.getY(index);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getX() throws Exception {
        final int index=-1;
        myService.getY(index);
    }

    @Test(expected = NullPointerException.class)
    public void getYNull() throws Exception {
        final int index=1;
        MyService myService=new MyService();
        myService.getY(index);
    }

    @Test(expected = NullPointerException.class)
    public void getXNull() throws Exception {
        final int index=1;
        MyService myService=new MyService();
        myService.getY(index);
    }


}