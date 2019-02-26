package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;
import com.netcracker.study.general.GameField;
import java.util.Set;

public class Bullet {
    private int positionX, positionY;
    private Direction flightDirection;
    private boolean stopped;
    private GameField parentGameField;
    private StringBuilder shootingResult;

    Bullet(int positionX, int positionY, Direction flightDirection, GameField parentGameField){
        this.flightDirection = flightDirection;
        this.positionX = positionX;
        this.positionY = positionY;
        this.parentGameField = parentGameField;
    }

    public String launch(){
        this.shootingResult = new StringBuilder();
        this.stopped = false;
        this.flyFurther();
        return shootingResult.toString();
    }

    private void flyFurther(){
        if (parentGameField.getBorderReference(this.positionX, this.positionY, this.flightDirection).doesStopBullet()) {
            this.stopped = true;
        }
        if (!this.stopped) {
            switch (flightDirection) {
                case RIGHT:
                    this.positionX++;
                case LEFT:
                    this.positionX--;
                case BOTTOM:
                    this.positionY--;
                case TOP:
                    this.positionY++;
            }
            Set<Player> affectedPlayers = parentGameField.getAllPlayersInTile(positionX, positionY);
            for (Player player : affectedPlayers) {
                this.shootingResult.append(player.onShoot());
            }
            this.flyFurther();
        }
    }

}
