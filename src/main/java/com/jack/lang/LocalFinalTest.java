package com.jack.lang;

/**
 * 为什么google checkstyle强制让局部变量修饰为final
 *
 * @description: local final test
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-30 16:17
 */
public class LocalFinalTest {

    private Integer v1 = 1;


    public void test(){
        Integer a = v1;
        Integer b = v1;
        System.err.println(v1);
    }



    public static void main(String[] args) {
        new LocalFinalTest().test();
    }

//    public void testFinal(){
//        final String name = "Whoops bug";
//        int pluginType = 3;
//    }

//    public void testSwitch(){
//        final String name;
//        int pluginType = 3;
//        switch (pluginType) {
//            case 1:
//                name = "Candidate Staff";
//                //break;
//                //should have handled all the cases for pluginType
//            case 2:
//                name = "fff";
//        }
//        // code, code, code
//        // Below is not possible with final
//        //name = "Whoops bug";
//    }
/**
    public void testIf() {
        // Use of final to ensure a variable is always assigned a value,
// and is assigned a value once and only once.
        int a = 4;
        final int x;

        if (a > 0) {
            x = 14;
        } else if (a < 0) {
            x = 0;
        } else {
            x = 3;
        }
        System.err.println(x);
    }
 */
}
