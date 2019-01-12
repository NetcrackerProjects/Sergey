package com.netcracker.study.objects.borders;

public class Wall implements BorderObject{
    int thickness;

    public void onShoot() {
        //Nothing should happen, at least, until messages implementation
    }

    public Wall() {
        this.thickness = 1;
    }

    public int getThickness() {
        return  thickness;
    }

    @Override
    public String toString() {
        return "This is a wall of thickness = " + thickness;
    }
}
