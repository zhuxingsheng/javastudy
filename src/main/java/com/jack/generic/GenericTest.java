package com.jack.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: generic test
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-28 11:50
 */
public class GenericTest {

    public static void main(String[] args) {
        List<? extends Fruit> list = new ArrayList<Apple>();
        //list.add(new Apple("1"));

    }
}


class Fruit {
    private String name;
    public Fruit(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public String toString() { return name; }
}
class Apple extends Fruit {
    public Apple(String name) { super(name); }
}

class RedFushi extends Apple {
    public RedFushi(String name) { super(name); }
}

class Orange extends Fruit {
    public Orange(String name) { super(name); }
}
