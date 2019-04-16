package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;
import com.netcracker.study.objects.borders.Shootable;

public class Player extends Entity implements Shootable {

    private String name;
    private boolean alive;
    private int bullets;
    private boolean hasTreasure;

    public Player(int positionX, int positionY, String name) {
        super(positionX, positionY);
        this.alive = true;
        this.name = name;
        this.bullets = 20;
    }

    @Override
    public void onShoot() {
        if (isAlive()) {
            this.alive = false;
        }
    }

    @Override
    public boolean doesStopBullet() {
        return false;
    }

    public void relocate(int newPositionX, int newPositionY) {
        this.positionX = newPositionX;
        this.positionY = newPositionY;
    }

    public void move(Direction direction) {
        switch (direction) {
            case TOP:
                positionY++;
                break;
            case BOTTOM:
                positionY--;
                break;
            case LEFT:
                positionX--;
                break;
            case RIGHT:
                positionX++;
                break;
        }
    }

    public Bullet shoot(Direction direction) {
        this.bullets--;
        return new Bullet(positionX, positionY, direction);
    }

    public int getBullets() {
        return bullets;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean hasTreasure() {
        return hasTreasure;
    }

}
