package com.jack.wife;

import static org.apache.commons.lang3.builder.ToStringStyle.NO_CLASS_NAME_STYLE;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @description: right
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-10-31 18:48
 */
@Data
@ExcelIgnoreUnannotated
public class Right extends Object {

    @ExcelProperty(value = "Shipment No")
    private String po;

    @ExcelProperty(value = "Keycode")
    private String keyCode;

    @ExcelProperty(value = "FF Due Date")
    @DateTimeFormat(value = "dd/MM/yyyy")
    private Date consDate;

    /**
     * 数量
     */
    @ExcelProperty(value = "Total Qty")
    private String qtyStr;

    @ExcelProperty(value = "Unit Cost")
    private String  price;

    public BigDecimal getP(){
        return new BigDecimal(price);
    }

    public int getQty(){
        return Integer.parseInt(StringUtils.substringBefore(qtyStr,"."));
    }


    private int row;

    @Override
    public boolean equals(Object obj) {
        Right other = (Right) obj;

        return new EqualsBuilder()
            .append(po,other.getPo())
            .append(keyCode,other.getKeyCode())
            .append(consDate,other.getConsDate())
            .append(getQty(),other.getQty())
            .append(getP(),other.getP()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,NO_CLASS_NAME_STYLE)
            .append("po",po)
            .append("keyCode",keyCode)
            .append("consDate", DateFormatUtils.format(consDate,"dd/MM/yyyy"))
            .append("qtyStr",getQty())
            .append("price",price).build();
    }

}
