package com.jack.wife;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.Joiner;
import java.util.concurrent.CountDownLatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: total data listener
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-10-31 19:04
 */
@Slf4j
@RequiredArgsConstructor
public class TotalListener extends AnalysisEventListener<Total> {

    private final CountDownLatch countDownLatch;

    /**
     * When analysis one row trigger invoke function.
     *
     * @param data one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     */
    @Override
    public void invoke(Total data, AnalysisContext context) {
        //log.info("invoke row:{}",context.readRowHolder().getRowIndex());
        if(StringUtils.isNotBlank(data.getKeyCode())
           // && data.consDate().before(new Date())
            && data.check()
        ) {
            //log.info("total:{}",data);
            DataContext.totals.put(Joiner.on("-").join(data.getPo(),data.getKeyCode()), data);
            data.setRow(context.readRowHolder().getRowIndex());
        }
    }

    /**
     * if have something to do after all analysis
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        countDownLatch.countDown();
        log.info("total read finish,size:{}",DataContext.totals.size());
    }

}
