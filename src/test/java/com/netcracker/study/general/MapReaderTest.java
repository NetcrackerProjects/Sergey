package com.netcracker.study.general;

import com.netcracker.study.objects.borders.Wall;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

public class MapReaderTest {


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnEncounteringWrongSymbolInMapFile() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMapBadFormat.txt");

        mapReader.createGameFieldFromTextFile();
    }

    @Test
    public void shouldPlaceBottomWallWhereItWasInFile() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedWall = new Wall(1);

        GameField gameField = mapReader.createGameFieldFromTextFile();

        assertEquals(expectedWall, gameField.getBorderAt(0, 1, Direction.BOTTOM));
    }

    @Test
    public void shouldPlaceTopWallWhereItWasInFile() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedWall = new Wall(1);

        GameField gameField = mapReader.createGameFieldFromTextFile();

        assertEquals(expectedWall, gameField.getBorderAt(3, 3, Direction.TOP));
    }

    @Test
    public void shouldPlacePassageOutOfLabyrinthWhereItWasInFile() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedPassage = new Wall(0);

        GameField gameField = mapReader.createGameFieldFromTextFile();

        assertEquals(expectedPassage, gameField.getBorderAt(3, 3, Direction.RIGHT));
    }

    @Test
    public void shouldPlaceTPassageWhereItWasInFile() throws FileNotFoundException {
        MapReader mapReader = new MapReader("src/test/resources/TestMap.txt");
        Wall expectedPassage = new Wall(0);

        GameField gameField = mapReader.createGameFieldFromTextFile();

        assertEquals(expectedPassage, gameField.getBorderAt(1, 1, Direction.BOTTOM));
    }
}
