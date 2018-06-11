package com.jack.protostuff;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jack01.zhu on 2018/5/22.
 */
public class Test {

    public static void main(String[]arg){
        Persion persion = new Persion();
        persion.setAge(12);
        persion.setName("zhangsan");
        List<String> strList = new ArrayList<>();
        strList.add("clothes1");
        strList.add("clothes2");
        persion.setClothesList(strList);

        Point point = new Point();
        point.setX(1);
        point.setY(2);
        persion.setPoint(point);
        ProtostuffUtil ProtostuffUtil = new ProtostuffUtil();
        byte[] bytes = ProtostuffUtil.serializer(persion,Persion.class);
        Persion persion1 = ProtostuffUtil.deserializer(bytes, Persion.class);
        System.out.println( Arrays.toString( bytes ) );
        System.out.println(JSON.toJSONString(persion1));
        System.out.println(JSON.toJSONString(persion));
    }
}
