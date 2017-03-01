package com.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by vlad on 23.02.17.
 */
public class MyService {
    private double xMin=0.8;
    private double xMax=2;
    private double step=0.005;
    private double a=1.5;

    private double yVals[];
    private double xVals[];

    public MyService(){
        super();
    }

    public MyService(double xMin, double xMax, double step, double a) {
        super();
        this.xMin = xMin;
        this.xMax = xMax;
        this.step = step;
        this.a=a;
    }

    public double findSum(){
        double sum=0.0;
        for(double y :yVals){
            sum+=y;
        }
        return sum;
    }

    public double findAver(){
        return findSum()/yVals.length;
    }

    public void findFunctionValues(){
        int iter=getIterationCount();
        yVals=new double[iter];
        xVals=new double[iter];

        double x=xMin;
        for(int i=0;i<iter;i++,x+=step){
            xVals[i]=x;
            yVals[i]=func(x);
        }
    }

    public int findMaxFuncElemIndex(){
        if(yVals!=null){
            int maxElemIndex=0;
            for(int i=1;i<yVals.length;i++){
                if(yVals[i]>yVals[maxElemIndex]){
                    maxElemIndex=i;
                }
            }
            return  maxElemIndex;
        }
        else {
            return -1;
        }
    }

    public int findMinFuncElemIndex(){
        if(yVals!=null){
            int minElemIndex=0;
            for(int i=1;i<yVals.length;i++){
                if(yVals[i]<yVals[minElemIndex]){
                    minElemIndex=i;
                }
            }
            return  minElemIndex;
        }
        else {
            return -1;
        }
    }

    public Map<Double,Double> getFunctionMapRepresent() {
        TreeMap<Double,Double> result=new TreeMap<>();
        for(int i=0;i<this.xVals.length;i++){
            result.put(xVals[i],yVals[i]);
        }
        return result;
    }

    public double getY(int i) {
        if(i>-1&&i<yVals.length)
            return yVals[i];
        else throw new IllegalArgumentException();
    }

    public double getX(int i) {
        if(i>-1&&i<xVals.length)
            return xVals[i];
        else throw new IllegalArgumentException();
    }

    private int getIterationCount(){
        return (int)((xMax-xMin)/step)+1;
    }

    private double func(double x){
        final double EPS=0.0001;
        if(x<1.7){
            return Math.PI*x*x-7/(x*x);
        }
        //else

        if(Math.abs(x-1.7)<EPS){
            return a*x*x*x+7/Math.pow(x,0.5);
        }
        //else

        if(x>1.7){
            return Math.log(x+7/Math.pow(x,0.5));
        }
        //unreachable
        return 0;
    }
}
