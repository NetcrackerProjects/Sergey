package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.NoSuchUserException;
import com.netcracker.study.objects.entities.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameFieldTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnAttemptToGetBorderOutsideLabyrinth() {
        GameField gameField = new GameField(2, 4);

        gameField.getBorderAt(10, 20, Direction.RIGHT);
    }

    @Test
    public void shouldFindPlayerAtAppropriatePosition() throws NoSuchUserException {
        GameField gameField = new GameField(2, 4);
        PlayerManager playerManager = new PlayerManager(gameField);
        playerManager.addPlayer(1, 2, "Jack");

        Player playerToFind = playerManager.getPlayer("Jack");

        assertEquals(playerToFind.getPositionX(), 1);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowExceptionOnAttemptToFindAbsentUser() throws NoSuchUserException {
        GameField gameField = new GameField(2, 4);
        PlayerManager playerManager = new PlayerManager(gameField);
        playerManager.addPlayer(1, 2, "Jack");

        playerManager.getPlayer("Bob");
    }
}
