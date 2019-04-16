package com.netcracker.study.general.messaging;

import com.netcracker.study.general.Direction;
import com.netcracker.study.objects.entities.Player;

public class MoveResult implements EventResult {

    private Player playerWhoMoved;
    private Direction direction;
    private boolean stoppedByWall;
    private boolean movedOutsideLabyrinth;

    @Override
    public EventTypes getEventType() {
        return EventTypes.MOVE;
    }

    public MoveResult(Player playerWhoMoved, Direction direction) {
        this.playerWhoMoved = playerWhoMoved;
        this.direction = direction;
        this.stoppedByWall = false;
        this.movedOutsideLabyrinth = false;
    }

    public void stoppedByWall() {
        this.stoppedByWall = true;
    }

    public void movedOutsideLabyrinth() {
        this.movedOutsideLabyrinth = true;
    }

    public Direction getDirection() {
        return direction;
    }

    Player getPlayerWhoMoved() {
        return playerWhoMoved;
    }

    boolean isStoppedByWall() {
        return stoppedByWall;
    }

    boolean isMovedOutsideLabyrinth() {
        return movedOutsideLabyrinth;
    }
}
