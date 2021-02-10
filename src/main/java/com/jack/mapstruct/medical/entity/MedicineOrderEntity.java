package com.jack.mapstruct.medical.entity;

import com.jack.mapstruct.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @description: medical entity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-11 22:53
 */
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineOrderEntity extends OrderEntity {
    private String doctor;
    private String treatment;
    private LocalDate prescribedAt;
    private String patient;
}
