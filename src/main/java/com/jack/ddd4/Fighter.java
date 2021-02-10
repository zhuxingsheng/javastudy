package com.jack.ddd4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @description: fighter
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-01-04 17:16
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Fighter extends Player {

    public Fighter(String name) {
        super();
    }

    String name;

    /**
     *
     */
    private Sword weapon;

}
