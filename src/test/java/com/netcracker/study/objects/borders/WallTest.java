package com.netcracker.study.objects.borders;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class WallTest {

    @Test
    public void shouldNotStopBulletAfterBeingDestroyedByGrenade() {
        Wall wallWithOneLife = new Wall(1);

        wallWithOneLife.onGrenadeHit();

        assertFalse(wallWithOneLife.doesStopBullet());
    }
}
