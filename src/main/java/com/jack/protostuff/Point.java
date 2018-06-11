package com.jack.protostuff;

import java.io.Serializable;

/**
 * 我是注释
 *
 * @author zl
 * @create 2018/5/22 15:57
 **/
public class Point implements Serializable{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
