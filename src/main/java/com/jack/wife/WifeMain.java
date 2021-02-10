package com.jack.wife;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @description: wife main
 *
 * 对比execl
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-10-31 18:39
 */
@Slf4j
public class WifeMain {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        readRgith();
        readTotal();
        countDownLatch.await();

        log.info("start compare");
        compare();

    }



    private static void compare(){

        //相同keycode，别的属性得一致
        AtomicInteger compare = new AtomicInteger(0);
        AtomicInteger wrong = new AtomicInteger(0);

        DataContext.rights.forEach((pk,right) -> {

            if(!DataContext.totals.containsKey(pk)) {
                log.info("少了po-keyCode:{}",pk);
                compare.incrementAndGet();
            } else {

                Total total = DataContext.totals.get(pk);
                if(!total.equals(new Total.TotalBuilder().po(right.getPo())
                        .keyCode(right.getKeyCode()).qty(right.getQty()).consDateStr(DateFormatUtils.format(right.getConsDate(), "yyyy/MM/dd"))
                        .price(right.getPrice()).build()))
                     {
                        log.info("pk:{}记录错了", pk);
                        log.info("r:{}",right);
                        log.info("w:{}",total);
                        log.info("");
                        wrong.incrementAndGet();


                    }
            }
//        DataContext.rights.asMap().forEach((pk,right) -> {
//
//            if(!DataContext.totals.containsKey(pk)) {
//                log.info("少了po-keyCode:{}",pk);
//            } else {
//                List<Total> collectionMap = Lists.newArrayList(DataContext.totals.asMap().get(pk));
//                right.forEach(r -> {
//                    if (!collectionMap.contains(new Total.TotalBuilder().po(r.getPo())
//                        .keyCode(r.getKeyCode()).qty(r.getQty()).consDateStr(DateFormatUtils.format(r.getConsDate(), "yyyy/MM/dd"))
//                        .price(r.getPrice()).build())
//                    ) {
//                        log.info("po:{},pk:{},少了条记录:{}", r.getPo(), pk, r);
//                        compare.incrementAndGet();
//                    }
//                });
//            }


//            if (right.size() != collectionMap.get(pk).size()) {
//                log.info("pk:{}，数量不一致,right:{},total:{}",pk,right.size() , collectionMap.get(pk).size());
//
//
//            }

        });
        log.warn("共少了 {} 条记录,错了 {}",compare.get(),wrong.get());

    }


    public static void readTotal() {
        log.info("start read total");
        // 写法2：
        String fileName = "/Users/zhuxingsheng/Downloads" + File
            .separator + "total.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, Total.class, new TotalListener(countDownLatch)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(1).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    public static void readRgith() {
        log.info("start read right");
        // 写法2：
        String fileName = "/Users/zhuxingsheng/Downloads" + File
            .separator + "right.xlsx";
        File file = new File(fileName);
        boolean isExsits = file.exists();
        ExcelReader excelReader = EasyExcel.read(fileName, Right.class, new RightListener(countDownLatch)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(1).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

}
