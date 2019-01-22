package com.netcracker.study.objects.borders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {

    @Test
    public void breakAndShootTest(){
        Wall testWall = new Wall(1);

        testWall.onGrenadeHit();

        Assertions.assertEquals(false, testWall.doesStopBullet());
    }
}
