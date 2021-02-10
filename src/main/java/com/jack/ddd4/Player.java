package com.jack.ddd4;

import lombok.Data;

/**
 * @description: player
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-01-04 17:15
 */
@Data
public abstract class Player {

    String name;

    private Weapon weapon;


}
