package com.controller;

import com.model.MyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vlad on 23.02.17.
 */
public class Controller extends HttpServlet {
    /**
     *@see  HttpServlet#HttpServlet()
     */
    public Controller(){}

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println(request.getParameterNames());

        double xMin = Double.parseDouble(request.getParameter("xMin"));
        double xMax = Double.parseDouble(request.getParameter("xMax"));
        double step = Double.parseDouble(request.getParameter("step"));
        double a = Double.parseDouble(request.getParameter("a"));

        MyService myService =new MyService(xMin,xMax,step,a);
        myService.findFunctionValues();
        /*getting average and sum of function*/
        double sum= myService.findSum();
        request.setAttribute("sum", sum);
        double aver=myService.findAver();
        request.setAttribute("aver", aver);

        /*max elem index and value*/
        int maxElemInd=myService.findMaxFuncElemIndex();
        double maxElemVal=myService.getY(maxElemInd);

        request.setAttribute("maxElemInd", maxElemInd);
        request.setAttribute("maxElemVal", maxElemVal);


        /*min elem index and value*/
        int minElemInd=myService.findMinFuncElemIndex();
        double minElemVal=myService.getY(minElemInd);

        request.setAttribute("minElemInd", minElemInd);
        request.setAttribute("minElemVal", minElemVal);


        Map<Double,Double> result = myService.getFunctionMapRepresent();
        request.getSession().setAttribute("result",result);

        request.getRequestDispatcher("WEB-INF/view/result.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
