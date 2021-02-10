package com.jack.mapstruct.medical.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

/**
 * @description: 处方、药方，医生开的药
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 21:29
 */
@FieldNameConstants
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prescription {
    private String doctor;
    private String treatment;
    private LocalDate date;
}
