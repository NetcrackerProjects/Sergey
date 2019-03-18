package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.PhaseViolationException;
import com.netcracker.study.objects.entities.Bullet;
import com.netcracker.study.objects.entities.Player;

import java.io.FileNotFoundException;
import java.util.List;

public class Game {
    private GameField gameField;
    private PlayerManager playerManager;
    private int currentPlayer;
    private Phase currentPhase;

    public Game(String mapFilePath) throws FileNotFoundException {
        MapReader mapReader = new MapReader(mapFilePath);
        this.gameField = mapReader.createGameFieldFromTextFile();
        playerManager = new PlayerManager(gameField);
    }


    public void start(){
        this.newRound();
    }

    private void endTurn(){
        if (currentPlayer ==  playerManager.numberOfPlayers() - 1) {
            newRound();
        } else {
            currentPlayer++;
        }
    }

    Player currentPlayer(){
        return playerManager.getPlayer(currentPlayer);
    }

    private void newRound(){
        this.currentPlayer = 0;
        this.currentPhase = Phase.PRE_MOVE;
    }

    String playerShoots(Player player, Direction direction) throws  PhaseViolationException{
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new PhaseViolationException(currentPhase);
        }

        if (player.getBullets() <= 0) {
            return player.getName() + " tries to shoot, but there is no ammo.";
        }

        StringBuilder returnString = new StringBuilder("Player " + player.getName() + " shoots to the " + direction + ". ");
        if (gameField.getBorderAt(player, direction).doesStopBullet()){
            returnString.append("There is a wall.");
        } else {
            Bullet bullet = player.shoot(direction);
            while (!bullet.isStopped()){
                bullet.fly();
                List<Player> killedPlayers = playerManager.getAllPlayersInTile(bullet.getPositionX(),bullet.getPositionY());
                killedPlayers.forEach(playerKilled -> returnString.append(playerKilled.onShoot()));
                if (gameField.getBorderAt(bullet, direction).doesStopBullet()){
                    bullet.stop();
                }
            }
        }

        return returnString.toString();
    }

    String playerMoves(Player player, Direction moveDirection) throws PhaseViolationException {
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new PhaseViolationException(currentPhase);
        }
        this.currentPhase = Phase.MOVE;
        StringBuilder returnString = new StringBuilder("Player " + player.getName() + " moves to the " + moveDirection +". ");
        if (gameField.getBorderAt(player, moveDirection).isPassable()) {
            returnString.append("But the wall stops the movement, so he stays in place.");
        } else {
            player.move(moveDirection);
        }
        this.currentPhase = Phase.POST_MOVE;
        return returnString.toString();
    }

    GameField getGameField() {
        return this.gameField;
    }

    PlayerManager getPlayerManager() {
        return this.playerManager;
    }

}
