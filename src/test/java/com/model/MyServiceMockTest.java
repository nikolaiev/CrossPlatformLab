package com.model;

import com.controller.Controller;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vlad on 16.03.17.
 */

    /*Mockito test*/
public class MyServiceMockTest {
    //private MyService myService;
    private Controller controller;

    /*Mockito objects*/
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher rd;
    @Mock
    private MyService myServiceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void mockTestController() throws ServletException, IOException {

        final double xMin =1.;
        final double xMax =10;
        final double step =3;
        final double a =0.1;
        final double expectedSum=2.9255628372392755;
        final double expectedAver=0.7313907093098189;

        when(request.getParameter("xMin")).thenReturn(Double.toString(xMin));
        when(request.getParameter("xMax")).thenReturn(Double.toString(xMax));
        when(request.getParameter("step")).thenReturn(Double.toString(step));
        when(request.getParameter("a")).thenReturn(Double.toString(a));
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("WEB-INF/view/result.jsp")).thenReturn(rd);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        /*create controller object*/
        controller=new Controller();
        controller.doGet(request,response);

        /*checking result values*/
        when(myServiceMock.findSum()).thenReturn(expectedSum);
        when(myServiceMock.findAver()).thenReturn(expectedAver);

        /*getting average and sum of function*/
        double sum= myServiceMock.findSum();
        double aver=myServiceMock.findAver();

        verify(request).setAttribute("sum", sum);
        verify(request).setAttribute("aver", aver);
        //verify(session).setAttribute("result",result );

        verify(rd).forward(request, response);


    }
}
