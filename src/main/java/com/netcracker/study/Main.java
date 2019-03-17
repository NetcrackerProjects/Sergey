package com.netcracker.study;

import com.netcracker.study.general.Game;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try{
            Game game = new Game(args[0]);
            game.start();
        } catch (FileNotFoundException e) {
            System.out.println("No such map file found.");
        }
    }
}
