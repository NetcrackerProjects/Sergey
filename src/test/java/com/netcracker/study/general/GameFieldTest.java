package com.netcracker.study.general;

import com.netcracker.study.objects.borders.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class GameFieldTest {

    @Test
    public void BottomWallTest() throws FileNotFoundException {
        String testMapPath = "src/test/resources/TestMap.txt";
        Wall expectedWall = new Wall(1);

        GameField gameField = new GameField(testMapPath);

        Assertions.assertEquals(expectedWall, gameField.getBorderReference(0, 1, Direction.BOTTOM));
    }

    @Test
    public void TopWallTest() throws FileNotFoundException {
        String testMapPath = "src/test/resources/TestMap.txt";
        Wall expectedWall = new Wall(1);

        GameField gameField = new GameField(testMapPath);

        Assertions.assertEquals(expectedWall, gameField.getBorderReference(3, 3, Direction.TOP));
    }

    @Test
    public void HorizontalPassageTest() throws FileNotFoundException {
        String testMapPath = "src/test/resources/TestMap.txt";
        Wall expectedPassage = new Wall(0);

        GameField gameField = new GameField(testMapPath);

        Assertions.assertEquals(expectedPassage, gameField.getBorderReference(1, 1, Direction.BOTTOM));
    }

    @Test
    public void OutOfLabyrinthPassageTest() throws FileNotFoundException {
        String testMapPath = "src/test/resources/TestMap.txt";
        Wall expectedPassage = new Wall(0);

        GameField gameField = new GameField(testMapPath);

        Assertions.assertEquals(expectedPassage, gameField.getBorderReference(3, 3, Direction.RIGHT));
    }

    @Test
    public void OutOfBordersTest() {
        GameField gameField = new GameField(2,4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {gameField.getBorderReference(10, 20, Direction.RIGHT);});
    }
}
