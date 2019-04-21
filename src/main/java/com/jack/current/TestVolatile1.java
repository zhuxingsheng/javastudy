package com.jack.current;

/**
 * Created by jack01.zhu on 2018/6/27.
 */
public class TestVolatile1 {

    private int i;

    private volatile int j;

    private static volatile TestVolatile1 instance;

    public TestVolatile1 getInstance(){

        i = 9;
        instance = new TestVolatile1();

        j=9;
        return instance;
    }


    public void setI(int i){
        this.i = i;
    }

    public int getI(){
        return i;
    }

    public void setJ(int j){
        this.j = 6;
    }

    public int getJ(){
        return j;
    }

}
