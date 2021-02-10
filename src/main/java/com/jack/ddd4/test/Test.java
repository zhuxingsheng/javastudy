package com.jack.ddd4.test;

import com.jack.ddd4.Fighter;
import com.jack.ddd4.Staff;
import com.jack.ddd4.Sword;

/**
 * @description: test
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-01-04 17:18
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void test() {

        Fighter fighter = new Fighter("Hero");
        Sword sword = new Sword("Sword", 10);
        fighter.setWeapon(sword);

        Staff staff = new Staff("Staff", 10);
        fighter.setWeapon(staff);

        //编译期静态绑定
        //Assertions.assertTrue(fighter.getWeapon() == null);

        System.out.println(fighter.getWeapon());

//        Player player = fighter;
//        Staff staff = new Staff("Staff", 10);
//        player.setWeapon(staff);

    }

}
