package com.jack.mapstruct.mapper;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;

/**
 * @description: global config
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-22 15:01
 */
@MapperConfig(builder = @Builder(disableBuilder = true))
public interface GlobalConfig {
}
