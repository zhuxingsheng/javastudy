package com.jack.wife;

import static org.apache.commons.lang3.builder.ToStringStyle.NO_CLASS_NAME_STYLE;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @description: total data
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-10-31 18:53
 */
@Data
@Slf4j
@ExcelIgnoreUnannotated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Total {

    @ExcelProperty(value = "PO#")
    private String po;

    @ExcelProperty(value = "KEYCODE")
    private String keyCode;

    @ExcelProperty(value = "Cons date")
    private String consDateStr;

//    @ExcelProperty(value = "Cons date")
//    @DateTimeFormat(value = "yyyy/MM/dd")
//    private Date consDate;

    /**
     * 数量
     */
    @ExcelProperty(value = "Qty")
    private int qty;

    @ExcelProperty(value = "Unit price")
    private String  price;

    public BigDecimal getP() {
        try {
            return new BigDecimal(
                StringUtils.startsWith(price,"$")? StringUtils.substringAfter(price,"$") :price
                //price
            ).setScale(2, BigDecimal.ROUND_HALF_UP);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @SneakyThrows
    public Date consDate(){
        try {
            return DateUtils.parseDate(consDateStr,"yyyy/MM/dd","yyyy-MM-dd hh:mm:ss");
        } catch (Exception e) {
            log.error("dateStr:{}",consDateStr,e);
            throw e;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Total other = (Total)obj;

        return new EqualsBuilder()
            .append(po,other.getPo())
            .append(keyCode,other.getKeyCode())
            .append(consDate(),other.consDate())
            .append(qty,other.getQty())
            .append(getP(),other.getP()).isEquals();
    }

    public boolean check(){
        boolean result = true;
        try

    {
        Preconditions.checkNotNull(price);
    }catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,NO_CLASS_NAME_STYLE)
            .append("po",po)
            .append("keyCode",keyCode)
            .append("consDate", DateFormatUtils.format(consDate(),"dd/MM/yyyy"))
            .append("qtyStr",getQty())
            .append("price",price).build();
    }

    private int row;

}
