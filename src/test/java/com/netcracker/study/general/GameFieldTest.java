package com.netcracker.study.general;

import com.netcracker.study.objects.entities.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GameFieldTest {

    @Test(expected = IllegalArgumentException.class)
    public void OutOfBordersTest() {
        GameField gameField = new GameField(2,4);

        gameField.getBorderReference(10, 20, Direction.RIGHT);
    }

    @Test
    public void getPlayerByNameTest() {
        GameField gameField = new GameField(2, 4);
        gameField.addPlayer(1,2,"Jack");

        Player playerToFind = gameField.getPlayerByName("Jack");

        Assert.assertEquals(playerToFind.getPositionX(), 1);
    }

    @Test
    public void tryToFindNonexistentPlayerTest() {
        GameField gameField = new GameField(2, 4);
        gameField.addPlayer(1,2,"Jack");

        Player playerToFind = gameField.getPlayerByName("Bob");

        Assert.assertEquals(playerToFind, null);
    }
}
