package com.jack.classloader;

/**
 * Created by Jack on 2018/9/29.
 */
public class LoaderClass1 {

    public void loader() {
        System.out.println(this.getClass().getName() + " loader:"+this.getClass().getClassLoader());

    }
}
