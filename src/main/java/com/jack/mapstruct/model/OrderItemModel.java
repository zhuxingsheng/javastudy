package com.jack.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @description: order item model
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 16:56
 */
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class OrderItemModel extends BaseModel {
    private String orderId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
}
