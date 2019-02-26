package com.netcracker.study.general;

import java.io.FileNotFoundException;

public class Game {
    private GameField gameField;

    public Game(String mapFilePath) throws FileNotFoundException {
        MapReader mapReader = new MapReader(mapFilePath);
        this.gameField = mapReader.createGameFieldFromTextFile();
    }

}
