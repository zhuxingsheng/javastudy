package com.jack.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @description: base entity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-23 15:53
 */
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseEntity {
    private Long id;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long modifiedBy;
    private LocalDateTime modifiedAt;


}
