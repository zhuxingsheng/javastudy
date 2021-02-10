package com.jack.perf4j;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;

/**
 * @description: Slf4JStopWatch
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-11-26 17:54
 */
@Slf4j
public class Slf4JStopWatchTest {

    @SneakyThrows
    @Test
    public void test(){
        StopWatch sw = new Slf4JStopWatch();

        Thread.sleep(1000);

        sw.stop("tag","message");
        log.info("-{}",sw.getElapsedTime());

    }

}
