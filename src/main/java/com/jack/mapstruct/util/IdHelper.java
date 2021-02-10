package com.jack.mapstruct.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomUtils;

/**
 * @description: id helper
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 10:35
 */
@UtilityClass
public class IdHelper {


    public long next() {
        return RandomUtils.nextLong();
    }
}
