package com.netcracker.study.general;

import com.netcracker.study.objects.borders.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class MapReaderTest {

    @Test
    public void WrongSymbolTest(){
        MapReader mapReader = new MapReader("src/test/resources/TestMapBadFormat.txt");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {mapReader.initMapFromFile();});
    }

    @Test
    public void BottomWallTest() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedWall = new Wall(1);

        GameField gameField = mapReader.initMapFromFile();

        Assertions.assertEquals(expectedWall, gameField.getBorderReference(0, 1, Direction.BOTTOM));
    }

    @Test
    public void TopWallTest() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedWall = new Wall(1);

        GameField gameField = mapReader.initMapFromFile();

        Assertions.assertEquals(expectedWall, gameField.getBorderReference(3, 3, Direction.TOP));
    }

    @Test
    public void OutOfLabyrinthPassageTest() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedPassage = new Wall(0);

        GameField gameField = mapReader.initMapFromFile();

        Assertions.assertEquals(expectedPassage, gameField.getBorderReference(3, 3, Direction.RIGHT));
    }

    @Test
    public void HorizontalPassageTest() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedPassage = new Wall(0);

        GameField gameField = mapReader.initMapFromFile();

        Assertions.assertEquals(expectedPassage, gameField.getBorderReference(1, 1, Direction.BOTTOM));
    }
}
