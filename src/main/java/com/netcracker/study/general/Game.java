package com.netcracker.study.general;

import com.netcracker.study.objects.entities.Bullet;
import com.netcracker.study.objects.entities.Player;

import java.io.FileNotFoundException;
import java.util.Set;

public class Game {
    private GameField gameField;
    private int numberOfPlayers;
    private int currentPlayer;
    private Phase currentPhase;

    public Game(String mapFilePath) throws FileNotFoundException {
        MapReader mapReader = new MapReader(mapFilePath);
        this.gameField = mapReader.createGameFieldFromTextFile();
        this.numberOfPlayers = 0;
    }

    public void addPlayer(int positionX, int positionY, String name) {
        gameField.addPlayer(positionX, positionY, name);
        this.numberOfPlayers++;
    }

    public void start(){
        this.newRound();
    }

    private void endTurn(){
        if (currentPlayer ==  numberOfPlayers) {
            newRound();
        } else {
            currentPlayer++;
        }
    }

    private void newRound(){
        currentPlayer = 1;
        currentPhase = Phase.PRE_MOVE;
    }

    String playerShoots(Player player, Direction direction) throws IllegalStateException {
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new IllegalStateException("Illegal call to shoot after or during movement");
        }
        if (player.getBullets() <= 0) {
            return player.getName() + " tries to shoot, but there is no ammo.";
        }

        StringBuilder returnString = new StringBuilder("Player " + player.getName() + " shoots to the " + direction + ". ");
        if (gameField.getBorderReference(player.getPositionX(),player.getPositionY(),direction).doesStopBullet()){
            returnString.append("There is a wall.");
        } else {
            Bullet bullet = new Bullet(player.getPositionX(), player.getPositionY(), direction);
            while (!bullet.isStopped()){
                bullet.flyFurther();
                Set<Player> killedPlayers = gameField.getAllPlayersInTile(bullet.getPositionX(),bullet.getPositionY());
                killedPlayers.forEach(playerKilled -> returnString.append(playerKilled.onShoot()));
                if (gameField.getBorderReference(bullet.getPositionX(),bullet.getPositionY(),direction).doesStopBullet()){
                    bullet.stop();
                }
            }
        }
        return returnString.toString();
    }

    String playerMoves(Player player, Direction direction) throws IllegalStateException {
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new IllegalStateException("Illegal call to move after movement or in the state of already moving");
        }
        this.currentPhase = Phase.MOVE;
        StringBuilder returnString = new StringBuilder("Player " + player.getName() + " moves to the " + direction +". ");
        if (gameField.getBorderReference(player.getPositionX(),player.getPositionY(),direction).doesStopMovement()) {
            returnString.append("But the wall stops the movement, so he stays in place.");
        } else {
            player.move(direction);
        }
        this.currentPhase = Phase.POST_MOVE;
        return returnString.toString();
    }

    GameField getGameField() {
        return this.gameField;
    }

}
