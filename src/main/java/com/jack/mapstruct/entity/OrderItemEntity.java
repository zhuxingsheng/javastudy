package com.jack.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @description: order item entity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-23 17:00
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@SuperBuilder
public class OrderItemEntity extends BaseEntity{

    private Long orderId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;




}
