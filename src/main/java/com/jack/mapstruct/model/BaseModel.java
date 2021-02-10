package com.jack.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @description: base model
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 16:52
 */
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseModel {
    private String id;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
