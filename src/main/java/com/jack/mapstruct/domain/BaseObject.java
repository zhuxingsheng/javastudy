package com.jack.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @description: base object
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-23 17:23
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class BaseObject {
    private long id;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long modifiedBy;
    private LocalDateTime modifiedAt;
}
