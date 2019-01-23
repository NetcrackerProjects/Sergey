package com.netcracker.study.objects.borders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {

    @Test
    public void breakAndShootTest(){
        Wall wallWithOneLife = new Wall(1);

        wallWithOneLife.onGrenadeHit();

        Assertions.assertEquals(false, wallWithOneLife.doesStopBullet());
    }
}
