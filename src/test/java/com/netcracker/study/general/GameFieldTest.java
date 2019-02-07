package com.netcracker.study.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameFieldTest {
    @Test
    public void OutOfBordersTest() {
        GameField gameField = new GameField(2,4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {gameField.getBorderReference(10, 20, Direction.RIGHT);});
    }
}
