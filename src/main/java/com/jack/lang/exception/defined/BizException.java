package com.jack.lang.exception.defined;

import lombok.Getter;

/**
 * @description: biz exception
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-14 17:42
 */
public class BizException extends RuntimeException {

    @Getter
    private String code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public  Throwable fillInStackTrace() {
        return this;
    }


}
