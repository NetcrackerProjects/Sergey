package com.netcracker.study.general;

public enum Direction {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT;

    private Direction opposite;

    static {
        TOP.opposite = BOTTOM;
        BOTTOM.opposite = TOP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    public Direction getOpposite() {
        return opposite;
    }
}