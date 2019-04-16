package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.NoSuchUserException;
import com.netcracker.study.general.Exceptions.PhaseViolationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;


public class GameTest {

    @Test
    public void shouldNotMoveWhenStoppedByWall() throws FileNotFoundException, NoSuchUserException, PhaseViolationException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(1, 1, "Mover");
        game.start();

        game.playerMoves(game.currentPlayer(), Direction.LEFT);

        assertEquals(game.getPlayerManager().getPlayer("Mover").getPositionX(), 1);
    }

    @Test
    public void shouldBeDeadAfterBeingShot() throws FileNotFoundException, NoSuchUserException, PhaseViolationException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(0, 0, "Shooter");
        game.getPlayerManager().addPlayer(2, 0, "Player Two");
        game.start();

        game.playerShoots(game.currentPlayer(), Direction.RIGHT);

        assertFalse(game.getPlayerManager().getPlayer("Player Two").isAlive());
    }

    @Test
    public void shouldReturnToFirstPlayerAfterLastOneFinishesTurn() throws FileNotFoundException {
        Game game = new Game("src/test/resources/TestMap.txt");
        game.getPlayerManager().addPlayer(0, 0, "Player One");
        game.getPlayerManager().addPlayer(2, 0, "Player Two");
        game.getPlayerManager().addPlayer(1, 0, "Player Three");
        game.start();
        game.playerEndsTurn();
        game.playerEndsTurn();

        game.playerEndsTurn();

        assertEquals(game.currentPlayer().getName(), "Player One");

    }
}
