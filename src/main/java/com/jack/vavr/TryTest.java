package com.jack.vavr;

import io.vavr.control.Try;

/**
 * @description: try test
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-01-20 16:51
 */
public class TryTest {

    public static void main(String[] args) {

        String get = Try.of(() -> get()).getOrNull();
        System.err.println(get);

    }

    private static String get() {
        if (true) {
            throw new NullPointerException();
        }
        return "a";
    }

}
