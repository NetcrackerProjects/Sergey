package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;

public class Bullet {
    private int positionX, positionY;
    private Direction flightDirection;
    private boolean stopped;
    private StringBuilder shootingResult;

    public Bullet(int positionX, int positionY, Direction flightDirection){
        this.flightDirection = flightDirection;
        this.positionX = positionX;
        this.positionY = positionY;
        this.stopped = false;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void stop(){
        this.stopped = true;
    }

    public boolean isStopped(){
        return this.stopped;
    }

    public String launch(){
        this.shootingResult = new StringBuilder();
        this.stopped = false;
        this.flyFurther();
        return shootingResult.toString();
    }

    public void flyFurther(){
        switch (flightDirection) {
            case RIGHT:
                this.positionX++; break;
            case LEFT:
                this.positionX--; break;
            case BOTTOM:
                this.positionY--; break;
            case TOP:
                this.positionY++; break;
        }
    }

}
