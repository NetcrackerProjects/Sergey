package com.netcracker.study.general;

import org.junit.Test;
import org.junit.Assert;

public class GameFieldTest {
    @Test(expected = IllegalArgumentException.class)
    public void OutOfBordersTest() {
        GameField gameField = new GameField(2,4);

        gameField.getBorderReference(10, 20, Direction.RIGHT);
    }
}
