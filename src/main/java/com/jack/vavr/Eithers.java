package com.jack.vavr;

import io.vavr.control.Either;
import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: vavr either
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-09-16 13:49
 */
public class Eithers {

    private static ThreadLocalRandom random =
            ThreadLocalRandom.current();

    @SneakyThrows
    public static void main(String[] args) {
        Either<String, String> either = compute()
                .map(str -> str + " World");
        System.out.println(either.isLeft());
        System.out.println(either.isRight());
        either.getOrElseThrow(s -> new RuntimeException(s));
        either.getOrElseThrow(() -> new RuntimeException());

        System.out.println(either.get());
    }

    private static Either<String, String> compute() {
        return random.nextBoolean()
                ? Either.left("Boom!")
                : Either.right("Hello");
    }
}
