package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.NoSuchUserException;
import com.netcracker.study.general.Exceptions.PhaseViolationException;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;

public class GameTest {

    @Test
    public void stoppedByWallTest() throws FileNotFoundException, NoSuchUserException, PhaseViolationException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(1, 1, "Mover");
        game.start();

        game.playerMoves(game.currentPlayer(),Direction.LEFT);

        Assert.assertEquals(game.getPlayerManager().getPlayer("Mover").getPositionX(), 1);
    }

    @Test
    public void droppingDeadTest() throws FileNotFoundException, NoSuchUserException, PhaseViolationException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(0, 0, "Shooter");
        game.getPlayerManager().addPlayer(2, 0, "Player Two");
        game.start();

        System.out.println(game.currentPlayer().getName());

        game.playerShoots(game.currentPlayer(),Direction.RIGHT);

        Assert.assertFalse(game.getPlayerManager().getPlayer("Player Two").isAlive());
    }

    @Test
    public void twoKillsTest() throws FileNotFoundException, PhaseViolationException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(0, 0, "Shooter");
        game.getPlayerManager().addPlayer(2, 0, "Player Two");
        game.getPlayerManager().addPlayer(3, 0, "Player Three");
        game.start();
        String expectedMessage = "Player Shooter shoots to the RIGHT. Player Two is killed. Player Three is killed. ";

        Assert.assertEquals(game.playerShoots(game.currentPlayer(),Direction.RIGHT), expectedMessage);
    }

}
