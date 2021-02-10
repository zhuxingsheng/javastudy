package com.jack.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import org.junit.jupiter.api.Test;

/**
 * @description: case
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-09-16 14:46
 */
public class CaseTest {

    @Test
    public void test(){
int i = 1;
    String s = Match(i).of(
            Case($(1), "one"),
            Case($(2), "two")
    );
        System.err.println(s);
}
}
