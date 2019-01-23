package com.netcracker.study.general;

import java.io.FileNotFoundException;

public class Game {
    public void start(String mapFile) throws FileNotFoundException {
        GameField gameField = new GameField(mapFile);
    }
}
