package com.jack.stream;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
//        String[] words = new String[]{"Hello b","World c"};
//        List<String[]> a = Arrays.stream(words)
//                .map(word -> word.split(" "))
//                .distinct()
//                .collect(toList());
//        a.forEach(System.out::println);

        /**
         * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
         */
//        Stream<String> stream = Arrays.stream(words);
//        stream.map(s -> s.split(" ")).flatMap(Arrays::stream);
//        stream.collect(toList()).forEach(System.out::println);

        //flatMap理解为二维遍历，以前二维遍历需要双循环，而现在不再需要双循环

        testStream();
    }

    /**
     * 嵌套
     */
    private static void testStream(){
        List<String> list = Lists.newArrayList("43","44","43232","3fds23","321dsa");

        list.stream().filter(s -> s.startsWith("4")).forEach(s -> {
            System.out.println(s);
            System.out.println("----");
            list.stream().filter(s1 -> (s1.length()>3 && s1.equals(s))).forEach(s1 -> {
                System.out.println(s1);
            });

        });

    }


    private Optional<String> op1(){
        System.err.println("op1");
        return Optional.of("op1");
    }
    private Optional<String> op2(){
        System.err.println("op2");
        return Optional.empty();
    }
    private Optional<String> op3(){
        System.err.println("op3");
        return Optional.of("op3");
    }


    @Test
    public void testFilter(){
        List<String> list = Lists.newArrayList("43","1","44","43232","3fds23","1","321dsa");

        final Optional<String> any = list.stream().filter(s -> filter(s)).findAny();
        System.err.println(any.get());


        final Optional<String> stringOptional = Lists.newArrayList(op1(), op2(), op3()).stream().filter(Optional::isPresent).findAny().orElse(Optional.empty());
        if(stringOptional.isPresent()) {
            System.err.println(stringOptional.get());
        }
        final Optional<String> stringOptional1 = Stream.of(op1(), op2(), op3()).filter(Optional::isPresent).findAny().orElse(Optional.empty());
        if(stringOptional1.isPresent()) {
            System.err.println(stringOptional1.get());
        }
    }

    private boolean filter(String str){
        System.err.println(str);
        return str.equals("1");
    }
}
