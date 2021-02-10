package com.jack.lombok;

import lombok.val;

import java.util.HashMap;

/**
 * @description: var test
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-02 23:24
 */
public class VarTest {

    public void test(){
        val a = new HashMap<String,String>();
        a.put("a","a");
    }
}
