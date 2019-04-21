package com.jack.classloader;


/**
 * Created by Jack on 2018/9/29.
 */
public class LoaderClass {

    public void loader(){
        System.out.println("LoaderClass:"+this.getClass().getClassLoader());
//        LoaderClass1 class1 = new LoaderClass1();
//        class1.loader();
    }
}
