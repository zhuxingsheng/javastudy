package com.jack.lang.exception.defined;

/**
 * @description: 自定义异常信息
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-14 17:41
 */
public class DefindExpTst {

    public static void main(String[] args) {
        DefindExpTst tst = new DefindExpTst();
        tst.test();
    }

    public void test(){
        test1();
    }

    public void test1(){
        final BizException a =
                new BizException("a");
        throw a;
    }
}
