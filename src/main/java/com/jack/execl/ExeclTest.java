package com.jack.execl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.File;

/**
 * @description: execltest
 * @author: Jack
 * @create: 2020-02-29 19:27
 */
public class ExeclTest {

    public static void main(String[] args) {
        simpleRead();
    }

    public static void simpleRead() {
        // 写法2：
        String fileName = "//Users/zhuxingsheng/Downloads" + File
                .separator + "11.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }
}
