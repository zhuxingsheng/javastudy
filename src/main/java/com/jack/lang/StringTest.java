package com.jack.lang;

/**
 * javap -c
 */
public class StringTest {

    public static void main(String[] args) {
//        StringBuilder str = new StringBuilder("test");
//        for (int i =0;i<10;i++) {
//            str.append("test");
//        }

        String str = 2 + str();


        String str1 = 3 +"";

        String str2 = String.valueOf(4);

        System.err.println(str);
    }

    private static String str(){
        return "ff";
    }
}
