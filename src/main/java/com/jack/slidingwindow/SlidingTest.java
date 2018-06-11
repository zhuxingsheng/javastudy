package com.jack.slidingwindow;

/**
 * Created by jack01.zhu on 2018/5/8.
 */
public class SlidingTest {

    public static void main(String[]arg) {

            int currentIndex = 6;

            long sum = 0;
            int ringWindow = 150;

            for (int i = 0; i < 120; i++) {
                int index = ((currentIndex + ringWindow) -i) % ringWindow;
                System.out.println(index);
        }
    }
}
