package com.jack.mapstruct.mapper;


import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", source = "main.id")
@Mapping(target = "createdBy", source = "main.createdBy")
@Mapping(target = "createdAt", source = "main.createdAt")
@Mapping(target = "modifiedBy", source = "main.modifiedBy")
@Mapping(target = "modifiedAt", source = "main.modifiedAt")
public @interface BaseFieldsMapping {
}
