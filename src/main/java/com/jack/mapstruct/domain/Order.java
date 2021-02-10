package com.jack.mapstruct.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * @description: domain order
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-22 14:54
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@FieldNameConstants
public class Order extends BaseObject{

    private String number;
    private LocalDate date;
    private OrderStatus status;
    private User user;
    @Setter(onParam_ = @NonNull)
    private List<? extends OrderItem> items;
}
