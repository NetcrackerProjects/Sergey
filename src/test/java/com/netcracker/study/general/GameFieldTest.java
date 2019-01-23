package com.netcracker.study.general;

import com.netcracker.study.objects.borders.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class GameFieldTest {

    @Test
    public void ConstructFieldTest() throws FileNotFoundException {
        String testMapPath = "src/test/resources/TestMap.txt";
        Wall testWall = new Wall(1);
        Wall testPassage = new Wall(0);

        GameField gameField = new GameField(testMapPath);

        Assertions.assertEquals(testWall, gameField.getBorderReference(0, 1, Direction.BOTTOM));
        Assertions.assertEquals(testPassage, gameField.getBorderReference(1, 1, Direction.BOTTOM));
        Assertions.assertEquals(testWall, gameField.getBorderReference(3, 3, Direction.TOP));
        Assertions.assertEquals(testPassage, gameField.getBorderReference(3, 3, Direction.RIGHT));
    }

    @Test void OutOfBordersTest() {
        GameField gameField = new GameField(2,4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {gameField.getBorderReference(10, 20, Direction.RIGHT);});
    }
}
