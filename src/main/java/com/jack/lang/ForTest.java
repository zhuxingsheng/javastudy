package com.jack.lang;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Jack on 2019/4/12.
 */
public class ForTest {

    public static void main(String[] args) {
        for(Map.Entry<String,String> entry:getMap().entrySet()){
            System.err.println(""+entry.getKey());
        }
    }

    private static Map<String,String> getMap(){
        System.err.println("getMap");
        Map<String,String> MAP =  Maps.newHashMap();
        MAP.put("1","2");
        MAP.put("11","2");
        MAP.put("111","2");
        return MAP;
    }
}
