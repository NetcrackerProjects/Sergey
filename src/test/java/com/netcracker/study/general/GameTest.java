package com.netcracker.study.general;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;

public class GameTest {

    @Test
    public void stoppedByWallTest() throws FileNotFoundException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.addPlayer(1, 1, "Mover");

        game.playerMoves(game.getGameField().getPlayerByName("Mover"),Direction.LEFT);

        Assert.assertEquals(game.getGameField().getPlayerByName("Mover").getPositionX(), 1);
    }

    @Test
    public void droppingDeadTest() throws FileNotFoundException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.addPlayer(0, 0, "Shooter");
        game.addPlayer(2, 0, "Player Two");

        game.playerShoots(game.getGameField().getPlayerByName("Shooter"),Direction.RIGHT);

        Assert.assertFalse(game.getGameField().getPlayerByName("Player Two").isAlive());
    }

    @Test
    public void twoKillsTest() throws FileNotFoundException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.addPlayer(0, 0, "Shooter");
        game.addPlayer(2, 0, "Player Two");
        game.addPlayer(3, 0, "Player Three");
        String expectedMessage = "Player Shooter shoots to the RIGHT. Player Two is killed. Player Three is killed. ";

        Assert.assertEquals(game.playerShoots(game.getGameField().getPlayerByName("Shooter"),Direction.RIGHT), expectedMessage);
    }

}
