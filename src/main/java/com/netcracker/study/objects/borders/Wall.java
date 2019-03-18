package com.netcracker.study.objects.borders;

import java.util.Objects;

public class Wall extends BorderObject{

    private int thickness;

    public Wall() {
        this.thickness = 1;
    }

    public Wall(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public String onShoot() {
        return "";
    }

    @Override
    public boolean doesStopBullet(){
        if (this.thickness < 1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isPassable(){
        if (this.thickness < 1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onGrenadeHit(){
        if (this.thickness > 0) {
            thickness--;
        }
    }

    @Override
    public String toString() {
        return "This is a wall of thickness = " + thickness;
    }

    public int getThickness() {
        return  thickness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return thickness == wall.thickness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(thickness);
    }
}
