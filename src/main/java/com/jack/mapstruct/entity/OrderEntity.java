package com.jack.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @description: order entity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-22 15:38
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class OrderEntity extends BaseEntity{
    private String number;
    private LocalDate date;
    private String status;
    private Long userId;
}
