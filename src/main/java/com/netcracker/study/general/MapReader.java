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

    public GameField createGameFieldFromTextFile() throws FileNotFoundException {
        File mapFile = new File(mapFilePath);
        FileReader input = new FileReader(mapFile);
        Scanner scan = new Scanner(input);
        ArrayList<String> mapLines = new ArrayList<String>();
        while (scan.hasNextLine()){
            mapLines.add(scan.nextLine());
        }
        int totalWidth = mapLines.get(0).length();
        int totalHeight = mapLines.size();
        int width = (totalWidth-1)/2;
        int height = (totalHeight-1)/2;
        GameField gameField = new GameField(width,height);

        String currentLine;
        for (int i = 0; i < height; i++) {
            currentLine = mapLines.get(totalHeight-1 - 2*i);
            for (int j = 0; j < width; j++){
                gameField.placeBorder(j ,i, Direction.BOTTOM, convertSymbolToObject(currentLine.charAt(1+j*2)));
            }
        }
        currentLine = mapLines.get(0);
        for (int j = 0; j < width; j++){
            gameField.placeBorder(j, height-1, Direction.TOP, convertSymbolToObject(currentLine.charAt(1+j*2)));
        }
        for (int i = 0; i < height; i++) {
            currentLine = mapLines.get(totalHeight - 2 - 2*i);
            for (int j = 0; j < width; j++){
                gameField.placeBorder(j, i, Direction.LEFT, convertSymbolToObject(currentLine.charAt(j*2)));
            }
            gameField.placeBorder(width-1, i, Direction.RIGHT, convertSymbolToObject(currentLine.charAt(width*2)));
        }

        return gameField;
    }

    private BorderObject convertSymbolToObject(char symbol) {
        switch (symbol) {
            case ' ':
                return new Wall(0);
            case '|':
                return new Wall(1);
            case  '-':
                return new Wall(1);
        }
        throw new IllegalArgumentException("Found illegal symbol while reading for borders: " + symbol);
    }
}
