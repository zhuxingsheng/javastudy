package com.jack.execl;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @description: dao
 * @author: Jack
 * @create: 2020-02-29 20:09
 */
public class DemoDAO {
    public void save(List<DemoData> list) {
        System.err.println(JSON.toJSON(list));

        for(DemoData data:list) {
            String srcImgPath = "/Users/zhuxingsheng/Downloads/1.png"; //源图片地址
            String tarImgPath = "/Users/zhuxingsheng/Downloads/pic/"+data.getId()+".png"; //待存储的地址
            new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, data.getUsername(), data.getMoney(),data.getMoney());
        }
    }
}
