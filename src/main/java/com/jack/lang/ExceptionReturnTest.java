package com.jack.lang;

/**
 * return finally 的返回值
 * Created by Jack on 2019/3/27.
 */
public class ExceptionReturnTest {

    public static void main(String[] args) {
        System.out.println(testExpReturn());
    }

    private static int testExpReturn(){
        int i = 9;
        try {
            String a = null;
            a.length();

            return i;
        }catch (Exception e) {
            System.err.println("exp");
            i = 2;
            return i;
        }finally {
            i = 1;
            System.err.println("finally");
            //没有return ，那就返回catche中的i；如果有return 那就返回此处i
            return i;
        }
    }
}
