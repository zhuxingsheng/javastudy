package com.jack.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @description: jdk8 time
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-01 09:39
 */
public class TimeTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020,8,1);
        LocalDate localDate1 = localDate.plusMonths(2);
        LocalDate localDate2 = localDate.minusMonths(2);
        System.err.println(localDate1 + "" + localDate2);

        Duration duration = Duration.between(localDate, LocalDate.of(2020, 7, 20));
        long l = duration.get(ChronoUnit.MONTHS);
        System.err.println(l);


//        LocalDate.now().with(TemporalAdjuster)
    }


}
