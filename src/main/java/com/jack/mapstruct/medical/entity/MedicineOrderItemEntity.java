package com.jack.mapstruct.medical.entity;

import com.jack.mapstruct.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @description: MedicineOrderItemEntity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 17:31
 */
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Data
public class MedicineOrderItemEntity extends OrderItemEntity {

    private boolean prescribed;
}
