package com.jack.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

/**
 * @description: demo
 * @author: Jack
 * @create: 2020-02-29 20:06
 */
@Data
public class DemoData {
    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String username;

    @ExcelProperty(index = 4)
    @NumberFormat("#,###.##")
    private String money;

}
