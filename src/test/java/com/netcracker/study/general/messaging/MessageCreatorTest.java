package com.netcracker.study.general.messaging;

import com.netcracker.study.general.Direction;
import com.netcracker.study.objects.entities.Player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;


public class MessageCreatorTest {

    @Test
    public void shouldReturnAppropriateMessageOnDoubleKill() {
        Player shooter = new Player(1, 2, "Shooter");
        Player victimOne = new Player(1, 3, "Victim One");
        Player victimTwo = new Player(2, 3, "Victim Two");
        ShootResult shootingResult = new ShootResult(shooter, Direction.RIGHT);
        shootingResult.addKilledPlayer(victimOne);
        shootingResult.addKilledPlayer(victimTwo);
        MessageCreator messageCreator = new MessageCreator(shootingResult);
        Message expectedMessage = new Message("Shooter shoots to the RIGHT. Victim One dies. Victim Two dies. ");

        Message actualMessage = messageCreator.generateMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void shouldMessageAboutShootingTheWall() {
        Player shooter = Mockito.mock(Player.class);
        Mockito.when(shooter.getName()).thenReturn("Shooter");
        ShootResult shootingResult = new ShootResult(shooter, Direction.LEFT);
        shootingResult.setStoppedByWall(true);
        MessageCreator messageCreator = new MessageCreator(shootingResult);
        Message expectedMessage = new Message("Shooter shoots to the LEFT. Bullet hits the wall. ");

        Message actualMessage = messageCreator.generateMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void shouldMessageAboutLeavingLabyrinthWithTreasure() {
        Player walker = Mockito.mock(Player.class);
        Mockito.when(walker.getName()).thenReturn("Walker");
        Mockito.when(walker.hasTreasure()).thenReturn(true);
        MoveResult moveResult = new MoveResult(walker, Direction.TOP);
        moveResult.movedOutsideLabyrinth();
        MessageCreator messageCreator = new MessageCreator(moveResult);
        Message expectedMessage = new Message("Walker walks to the TOP. He manages to exit the labyrinth with treasure, and wins! ");

        Message actualMessage = messageCreator.generateMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void shouldMessageAboutTurnChange() {
        Player oldPlayer = Mockito.mock(Player.class);
        Mockito.when(oldPlayer.getName()).thenReturn("Player1");
        Player newPlayer = Mockito.mock(Player.class);
        Mockito.when(newPlayer.getName()).thenReturn("Player2");
        EndTurnResult endTurnResult = new EndTurnResult(oldPlayer);
        endTurnResult.setNewPlayer(newPlayer);
        MessageCreator messageCreator = new MessageCreator(endTurnResult);
        Message expectedMessage = new Message("Player Player1 ends his turn. Now is the turn of player Player2.");

        Message actualMessage = messageCreator.generateMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());

    }
}
