package com.jack.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义加载器
 * Created by Jack on 2018/9/29.
 */
public class MyClassLoader extends ClassLoader {

    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String root = "d:/";

        byte[] bytes = null;
        try {
            //路径改到根目录下
            String file = root + name.substring(name.lastIndexOf(".")+1) + ".class";
            InputStream ins = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            bytes = baos.toByteArray();

            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
