package com.model;

import com.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vlad on 23.02.17.
 */
public class MyServiceTest {
    MyService myService;
    @Before
    public void init() {
        myService = new MyService();
        myService.findFunctionValues();
    }

    @Test
    public void findSum() throws Exception {
        MyService myService;
        myService = new MyService(1,2,1,1);
        myService.findFunctionValues();
        assertEquals(-1.919,myService.findSum(),0.001);
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
        Method method=MyService.class.getDeclaredMethod("getIterationCount");
        method.setAccessible(true);
        assertEquals(180,myService.findMaxFuncElemIndex());
    }

    @Test
    public void findMinFuncElemIndex() throws Exception {
        MyService myService=new MyService();
        assertEquals(-1,myService.findMinFuncElemIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getY() throws Exception {
        myService.getY(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getX() throws Exception {
        myService.getY(-1);
    }

    @Test(expected = NullPointerException.class)
    public void getYNull() throws Exception {
        MyService myService=new MyService();
        myService.getY(1);
    }

    @Test(expected = NullPointerException.class)
    public void getXNull() throws Exception {
        MyService myService=new MyService();
        myService.getY(1);
    }

    /*Mockito test*/
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockTestController() throws ServletException, IOException {
        double xMin =1.;
        double xMax =10;
        double step =3;
        double a =0.1;

        when(request.getParameter("xMin")).thenReturn(Double.toString(xMin));
        when(request.getParameter("xMax")).thenReturn(Double.toString(xMax));
        when(request.getParameter("step")).thenReturn(Double.toString(step));
        when(request.getParameter("a")).thenReturn(Double.toString(a));
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("WEB-INF/view/result.jsp")).thenReturn(rd);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        Controller controller=new Controller();
        controller.doGet(request,response);

        /*checking result values*/
        MyService myService =new MyService(xMin,xMax,step,a);
        myService.findFunctionValues();
        /*getting average and sum of function*/
        double sum= myService.findSum();
        double aver=myService.findAver();

        Map<Double,Double> result = myService.getFunctionMapRepresent();


        verify(request).setAttribute("sum", sum);
        verify(request).setAttribute("aver", aver);

        verify(session).setAttribute("result",result );

        verify(rd).forward(request, response);

        String resultString = sw.getBuffer().toString().trim();
        //System.out.println("Result: "+resultString);
    }
}