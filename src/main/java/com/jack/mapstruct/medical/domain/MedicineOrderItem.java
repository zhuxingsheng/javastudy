package com.jack.mapstruct.medical.domain;

import com.jack.mapstruct.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @description: MedicineOrderItem
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 21:34
 */
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class MedicineOrderItem extends OrderItem {

    //是否被治疗
    private boolean prescribed;


}
