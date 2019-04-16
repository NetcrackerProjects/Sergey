package com.netcracker.study.objects.borders;

import org.junit.Test;
import org.junit.Assert;

public class WallTest {

    @Test
    public void breakAndShootTest(){
        Wall wallWithOneLife = new Wall(1);

        wallWithOneLife.onGrenadeHit();

        Assert.assertEquals(false, wallWithOneLife.doesStopBullet());
    }
}
