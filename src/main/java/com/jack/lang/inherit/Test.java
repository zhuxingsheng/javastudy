package com.jack.lang.inherit;

import com.alibaba.fastjson.JSON;

/**
 * @description: tset
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-02-03 10:25
 */
public class Test {

    public static void main(String[] args) {
        Father father = new Father();

        father.setName("father");

        Father son = new Son();
        son.setName("son");

        System.err.println(JSON.toJSONString(father));
        System.err.println(JSON.toJSONString(son));
    }

}
