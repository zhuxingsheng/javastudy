package com.jack.ddd4;

import lombok.Data;

/**
 * @description: weapon
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-01-04 17:16
 */
@Data
public abstract class Weapon {

    public Weapon(String name,int damage) {
        this.name= name;
        this.damage=damage;
    }

    String name;

    int damage;

    int damageType;

}
