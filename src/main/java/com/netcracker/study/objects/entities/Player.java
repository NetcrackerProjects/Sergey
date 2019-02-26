package com.netcracker.study.objects.entities;

import com.netcracker.study.general.Direction;
import com.netcracker.study.general.GameField;
import com.netcracker.study.objects.borders.Shootable;

public class Player implements Shootable {

    private String name;
    private int positionX, positionY;
    boolean alive;
    private GameField parentGameField;

    public Player(int positionX, int positionY, String name, GameField parentGameField){
        this.positionX = positionX;
        this.positionY = positionY;
        this.name = name;
        this.parentGameField = parentGameField;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isAlive() {
        return alive;
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

    public String shoot(Direction direction) {
        StringBuilder returnString = new StringBuilder("Player " + name + " shoots to the " + direction + ". ");
        if (parentGameField.getBorderReference(positionX, positionY, direction).doesStopBullet()){
            returnString.append("Bullet hits the wall. ");
        } else {
            returnString.append("There is no wall. ");
        }
        Bullet bullet = new Bullet(this.positionX, this.positionY, direction, parentGameField);
        returnString.append(bullet.launch());
        return returnString.toString();
    }
}
