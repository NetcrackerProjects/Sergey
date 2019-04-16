package com.netcracker.study.general.messaging;

import com.netcracker.study.general.Direction;
import com.netcracker.study.objects.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class ShootResult implements EventResult {
    private Player playerWhoShot;
    private Direction direction;
    private boolean stoppedByWall;
    private boolean failedShotNoAmmo;
    private List<Player> killedPlayers;

    @Override
    public EventTypes getEventType() {
        return EventTypes.SHOOT;
    }


    public ShootResult(Player playerWhoShot, Direction direction) {
        this.playerWhoShot = playerWhoShot;
        this.direction = direction;
        this.stoppedByWall = false;
        this.failedShotNoAmmo = false;
        killedPlayers = new ArrayList<>();
    }

    public void failedShotNoAmmo() {
        this.failedShotNoAmmo = true;
    }

    public void setStoppedByWall(boolean stoppedByWall) {
        this.stoppedByWall = stoppedByWall;
    }

    public void addKilledPlayer(Player player) {
        this.killedPlayers.add(player);
    }

    List<Player> getKilledPlayers() {
        return killedPlayers;
    }

    Player getPlayerWhoShot() {
        return playerWhoShot;
    }

    public Direction getDirection() {
        return direction;
    }

    boolean isStoppedByWall() {
        return stoppedByWall;
    }


}
