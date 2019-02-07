package com.netcracker.study.general;

import com.netcracker.study.objects.borders.BorderObject;
import com.netcracker.study.objects.borders.Wall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MapReader {
    private String mapFilePath;

    public MapReader(String mapFilePath) {
        this.mapFilePath = mapFilePath;
    }

    public GameField initMapFromFile() throws FileNotFoundException {
        File mapFile = new File(mapFilePath);
        FileReader input = new FileReader(mapFile);
        Scanner scan = new Scanner(input);
        ArrayList<String> mapLines = new ArrayList<String>();
        while (scan.hasNextLine()){
            mapLines.add(scan.nextLine());
        }
        int tw = mapLines.get(0).length();
        int th = mapLines.size();
        int width = (tw-1)/2;
        int height = (th-1)/2;
        GameField gameField = new GameField(width,height);

        String curline;
        for (int i = 0; i < height; i = i+1) {
            curline = mapLines.get(th-1 - 2*i);
            for (int j = 0; j < width; j = j + 1){
                gameField.placeBorder(j ,i, Direction.BOTTOM, interpBorderSymbol(curline.charAt(1+j*2)));
            }
        }
        curline = mapLines.get(0);
        for (int j = 0; j < width; j = j + 1){
            gameField.placeBorder(j, height-1, Direction.TOP, interpBorderSymbol(curline.charAt(1+j*2)));
        }
        for (int i = 0; i < height; i = i+1) {
            curline = mapLines.get(th - 2 - 2*i);
            for (int j = 0; j < width; j = j + 1){
                gameField.placeBorder(j, i, Direction.LEFT, interpBorderSymbol(curline.charAt(j*2)));
            }
            gameField.placeBorder(width-1, i, Direction.RIGHT, interpBorderSymbol(curline.charAt(width*2)));
        }

        return gameField;
    }

    private BorderObject interpBorderSymbol(char c) {
        switch (c) {
            case ' ':
                return new Wall(0);
            case '|':
                return new Wall(1);
            case  '-':
                return new Wall(1);
        }
        throw new IllegalArgumentException("Found illegal symbol while reading for borders: " + c);
    }
}
