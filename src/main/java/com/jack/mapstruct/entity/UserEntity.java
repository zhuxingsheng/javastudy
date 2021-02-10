package com.jack.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @description: user entity
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-23 17:13
 */
@Data
@AllArgsConstructor
@SuperBuilder
@RequiredArgsConstructor
public class UserEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private Integer age;
    private String language;
}
