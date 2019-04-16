package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.PhaseViolationException;
import com.netcracker.study.general.messaging.EndTurnResult;
import com.netcracker.study.general.messaging.EventResult;
import com.netcracker.study.general.messaging.MoveResult;
import com.netcracker.study.general.messaging.ShootResult;
import com.netcracker.study.objects.entities.Bullet;
import com.netcracker.study.objects.entities.Player;

import java.io.FileNotFoundException;
import java.util.List;

public class Game {
    private GameField gameField;
    private PlayerManager playerManager;
    private int currentPlayerIndex;
    private Phase currentPhase;

    public Game(String mapFilePath) throws FileNotFoundException {
        MapReader mapReader = new MapReader(mapFilePath);
        this.gameField = mapReader.createGameFieldFromTextFile();
        this.playerManager = new PlayerManager(gameField);
    }

    public void start() {
        this.newRound();
    }

    private void endTurn() {
        if (currentPlayerIndex == playerManager.numberOfPlayers() - 1) {
            newRound();
        } else {
            currentPlayerIndex++;
            this.currentPhase = Phase.PRE_MOVE;
        }
    }

    Player currentPlayer() {
        return playerManager.getPlayer(currentPlayerIndex);
    }

    private void newRound() {
        this.currentPlayerIndex = 0;
        this.currentPhase = Phase.PRE_MOVE;
    }

    EventResult playerShoots(Player player, Direction direction) throws PhaseViolationException {
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new PhaseViolationException(currentPhase);
        }

        ShootResult shootResult = new ShootResult(player, direction);
        if (player.getBullets() <= 0) {
            shootResult.failedShotNoAmmo();
            return shootResult;
        }

        if (gameField.getBorderAt(player, direction).doesStopBullet()) {
            shootResult.setStoppedByWall(true);
            return shootResult;
        } else {
            Bullet bullet = player.shoot(direction);
            while (!bullet.isStopped()) {
                bullet.fly();
                List<Player> killedPlayers = playerManager.getAllPlayersInTile(bullet.getPositionX(), bullet.getPositionY());
                killedPlayers.forEach(shootResult::addKilledPlayer);
                killedPlayers.forEach(Player::onShoot);
                if (gameField.getBorderAt(bullet, direction).doesStopBullet()) {
                    bullet.stop();
                }
            }
        }

        return shootResult;
    }

    EventResult playerMoves(Player player, Direction moveDirection) throws PhaseViolationException {
        if (this.currentPhase == Phase.POST_MOVE || this.currentPhase == Phase.MOVE) {
            throw new PhaseViolationException(currentPhase);
        }
        this.currentPhase = Phase.MOVE;
        MoveResult moveResult = new MoveResult(player, moveDirection);
        if (gameField.getBorderAt(player, moveDirection).isPassable()) {
            moveResult.stoppedByWall();
        } else {
            player.move(moveDirection);
            if (!gameField.tileWithinGameBorders(player.getPositionX(), player.getPositionY())) {
                moveResult.movedOutsideLabyrinth();
                player.move(moveDirection.getOpposite());
            }
        }
        this.currentPhase = Phase.POST_MOVE;
        return moveResult;
    }

    EventResult playerEndsTurn() {
        EndTurnResult endTurnResult = new EndTurnResult(playerManager.getPlayer(currentPlayerIndex));
        endTurn();
        endTurnResult.setNewPlayer(playerManager.getPlayer(currentPlayerIndex));
        return endTurnResult;
    }

    PlayerManager getPlayerManager() {
        return this.playerManager;
    }
}
