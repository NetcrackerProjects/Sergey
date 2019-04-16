package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;

public class Bullet extends Entity {
    private Direction flightDirection;
    private boolean stopped;

    public Bullet(int positionX, int positionY, Direction flightDirection) {
        super(positionX, positionY);
        this.flightDirection = flightDirection;
        this.stopped = false;
    }

    public void stop() {
        this.stopped = true;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void fly() {
        switch (flightDirection) {
            case RIGHT:
                this.positionX++;
                break;
            case LEFT:
                this.positionX--;
                break;
            case BOTTOM:
                this.positionY--;
                break;
            case TOP:
                this.positionY++;
                break;
        }
    }

}
