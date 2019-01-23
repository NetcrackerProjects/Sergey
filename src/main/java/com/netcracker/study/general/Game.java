package com.netcracker.study.general;

import java.io.FileNotFoundException;

public class Game {

    public void start(String mapFile){
        try {
            GameField gameField = new GameField(mapFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
