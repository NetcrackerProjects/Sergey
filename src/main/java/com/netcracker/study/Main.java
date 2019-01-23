package com.netcracker.study;

import com.netcracker.study.general.Game;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.start(args[0]);
    }
}
