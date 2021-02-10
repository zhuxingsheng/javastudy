package com.jack.mapstruct.model;

import com.jack.mapstruct.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @description: order model
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 16:51
 */
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderModel extends BaseModel {

    private String number;
    private LocalDate date;
    private String dateText;
    private String userId;
    private String username;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private Integer totalQuantity;
    private List<? extends OrderItemModel> items;
}
