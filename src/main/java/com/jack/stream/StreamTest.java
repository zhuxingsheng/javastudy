package com.jack.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * stream 学习
 *
 * map:Returns a stream consisting of the results of applying the given function to the elements of this stream.
 *
 * 返回一个流，包含给定函数应用在流中每一个元素后的结果
 *
 * flatmap:Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
 *
 * 返回一个流，包含将此流中的每个元素替换为通过给定函数映射应用于每个元素而生成的映射流的内容
 *
 *
 */
public class StreamTest {

    public static void main(String[] args) {
        String[] words = new String[]{"Hello b","World c"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(" "))
                .distinct()
                .collect(toList());
        a.forEach(System.out::println);

        /**
         * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
         */
//        Stream<String> stream = Arrays.stream(words);
//        stream.map(s -> s.split(" ")).flatMap(Arrays::stream);
//        stream.collect(toList()).forEach(System.out::println);

        //flatMap理解为二维遍历，以前二维遍历需要双循环，而现在不再需要双循环


    }
}
