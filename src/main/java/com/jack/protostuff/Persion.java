package com.jack.protostuff;

import java.io.Serializable;
import java.util.List;

/**
 * 我是注释
 *
 * @author zl
 * @create 2018/5/22 15:38
 **/
public class Persion implements Serializable {
    private String name;
    private int age;
    private List<String> clothesList;
    private Point point;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getClothesList() {
        return clothesList;
    }

    public void setClothesList(List<String> clothesList) {
        this.clothesList = clothesList;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
