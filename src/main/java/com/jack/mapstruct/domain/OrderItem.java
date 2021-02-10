package com.jack.mapstruct.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @description: order item
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-25 18:12
 */
@Data
@ToString(exclude = {"order"})
@EqualsAndHashCode(exclude = {"order"})
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class OrderItem extends BaseObject{
    private Order order;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;

    public BigDecimal getAmount() {
        return unitPrice == null || quantity == null ? BigDecimal.ZERO : unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
