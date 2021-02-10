package com.jack.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: while test
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-05 19:53
 */
@Slf4j
public class WhileTest {

    public static void main(String[] args) {
        int i = 0;
        do {
            i++;
                log.warn("验真回调延迟任务:{} {},未写入完成"+i);
        } while (i <= 2);
    }
}
