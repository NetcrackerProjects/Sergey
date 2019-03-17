package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;
import com.netcracker.study.objects.borders.Shootable;

public class Player implements Shootable {

    private String name;
    private int positionX, positionY;
    boolean alive;
    private int bullets;

    public Player(int positionX, int positionY, String name){
        this.positionX = positionX;
        this.positionY = positionY;
        this.alive = true;
        this.name = name;
        this.bullets = 20;
    }

    @Override
    public String onShoot() {
        if (isAlive()) {
            this.alive = false;
            return (name + " is killed. ");
        } else {
            return "";
        }
    }

    @Override
    public boolean doesStopBullet() {
        return false;
    }

    public void relocate(int newPositionX, int newPositionY){
        this.positionX = newPositionX;
        this.positionY = newPositionY;
    }

    public void move(Direction direction) {
        switch (direction){
            case TOP:
                positionY++;
            case BOTTOM:
                positionY--;
            case LEFT:
                positionX--;
            case RIGHT:
                positionX++;
        }
    }

    public void spendBullet() {
        this.bullets--;
    }

    public int getBullets() {
        return bullets;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getName(){
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

}
