package com.jack.mapstruct.medical.domain;

import com.jack.mapstruct.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @description: domain
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 21:24
 */
@FieldNameConstants
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineOrder extends Order {
    private Prescription prescription;
    //病人
    private String patient;
    private List<? extends MedicineOrderItem> medicineItems;
}
