package com.netcracker.study.objects.borders;

public class Wall extends BorderObject{

    private int thickness;

    public Wall() {
        this.thickness = 1;
    }

    public Wall(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public void onShoot() {
        //Nothing should happen, at least, until messages implementation
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
    public void onGrenadeHit(){
        if (this.thickness > 0) {
            thickness = thickness-1;
        }
    }

    @Override
    public String toString() {
        return "This is a wall of thickness = " + thickness;
    }

    public int getThickness() {
        return  thickness;
    }

}
