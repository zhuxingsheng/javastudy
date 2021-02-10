package com.jack.optional;

import java.util.Optional;

/**
 * @description: OptionalTest
 * @author: Jack
 * @create: 2020-02-28 00:49
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = new User();
        Optional<Address> address = Optional.ofNullable(user).map(user1 -> user1.getAddress());
        Optional<String> x = address.map(address1 -> address1.getAddress());

        Optional<String> addressStr = Optional.ofNullable(user).map(User::getAddress).map(Address::getAddress);
        System.err.println(x.orElse("else"));
        System.err.println(addressStr.orElse("else"));

        Optional<String> o = Optional.of("b");

        System.err.println(o.orElse(s()));
        System.err.println(o.orElseGet(()->{
            System.err.println("ss");
            return "ss";}));
        System.err.println(o);


    }

    private static String s(){
        System.err.println("s");
        return "s";
    }



}
