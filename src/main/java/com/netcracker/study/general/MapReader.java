package com.netcracker.study.general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class MapReader {
    private File mapFile;
    private ArrayList<String> mapLines;
    private int totalWidth, totalHeight, width, height;
    private MapObjectFactory objectFactory;
    private GameField gameField;

    MapReader(String mapFilePath) throws FileNotFoundException {
        this.mapFile = new File(mapFilePath);
        scanTextFile();
        this.objectFactory = new MapObjectFactory();
    }

    GameField createGameFieldFromTextFile() {
        this.gameField = new GameField(width, height);
        addAllHorizontalBorders();
        addAllVerticalBorders();
        return gameField;
    }

    private void scanTextFile() throws FileNotFoundException {
        FileReader input = new FileReader(mapFile);
        Scanner scan = new Scanner(input);
        this.mapLines = new ArrayList<>();
        while (scan.hasNextLine()) {
            mapLines.add(scan.nextLine());
        }
        this.totalWidth = mapLines.get(0).length();
        this.totalHeight = mapLines.size();
        this.width = (totalWidth - 1) / 2;
        this.height = (totalHeight - 1) / 2;
    }

    private void addAllHorizontalBorders() {
        for (int i = 0; i <= height; i++) {
            readHorizontalBorderRow(i);
        }
    }

    private void readHorizontalBorderRow(int rownum) {
        String currentLine = mapLines.get(tileRowIndex(rownum) + 1);
        for (int j = 0; j < width; j++) {
            gameField.placeBorder(j, rownum, Direction.BOTTOM, objectFactory.convertSymbolToBorderObject(currentLine.charAt(1 + j * 2)));
        }
    }

    private void addAllVerticalBorders() {
        for (int i = 0; i < height; i++) {
            readVerticalBorderRow(i);
        }
    }

    private void readVerticalBorderRow(int rownum) {
        String currentLine = mapLines.get(tileRowIndex(rownum));
        for (int j = 0; j <= width; j++) {
            gameField.placeBorder(j, rownum, Direction.LEFT, objectFactory.convertSymbolToBorderObject(currentLine.charAt(j * 2)));
        }
    }

    private int tileRowIndex(int x) {
        return totalHeight - 2 - 2 * x;
    }
}
