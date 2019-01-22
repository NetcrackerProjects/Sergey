package com.netcracker.study.general;

import com.netcracker.study.objects.borders.BorderObject;
import com.netcracker.study.objects.borders.Wall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GameField {
    private int width;
    private int height;
    private BorderObject[] borders;


    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        borders = new BorderObject[totalCountOfBorders(width, height)];
    }

    public GameField(String mapFilePath) throws FileNotFoundException {
        try{
            File mapFile = new File(mapFilePath);
            FileReader input = new FileReader(mapFile);
            Scanner scan = new Scanner(input);
            ArrayList<String> mapLines = new ArrayList<String>();
            while (scan.hasNextLine()){
                mapLines.add(scan.nextLine());
            }
            this.width = (mapLines.get(0).length()-1)/2;
            this.height = (mapLines.size()-1)/2;

            for (int i = mapLines.size()-1; i > 0; i = i - 2){

            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Map file not found!");
        }
    }

    private int totalCountOfBorders(int width, int height){
        return ((2*width+1)*height + width);
    }

    private Integer getBorderBetweenTiles(int x1, int x2, int y1, int y2) throws IllegalArgumentException {

        if (Math.abs(x1 - x2) > 1 || Math.abs(y1 - y2) > 1 || (x1 - x2 != 0 && y1 - y2 != 0)) {
            throw new IllegalArgumentException("Tiles selected are not adjacent => border cannot be specified!");
        }
        if (x1 - x2 == 0 && y1 - y2 == 0) {
            throw new IllegalArgumentException("Tiles selected are actually the same tile => unable to determine border");
        }
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > width + 1 || x2 > width + 1 || y1 > height + 1 || y2 > height + 1) {
            throw new IllegalArgumentException("One of the tiles lies beyond game space!");
        }

        Integer index;
        if (x1 == x2) {
            int topTile = Math.max(y1, y2);
            index = topTile*(2*width+1) + x1;
            return index;
        } else {
            int rightTile = Math.max(x1, x2);
            index = y1*(2*width+1) + width + rightTile;
            return index;
        }
    }

    private Integer getBorderByDirection(int x, int y, Direction dir) {

        switch (dir) {
            case TOP:
                return getBorderBetweenTiles(x, x, y, y+1);
            case BOTTOM:
                return getBorderBetweenTiles(x, x, y, y-1);
            case LEFT:
                return getBorderBetweenTiles(x, x-1, y, y);
            case RIGHT:
                return getBorderBetweenTiles(x, x+1, y, y);
        }
        throw new RuntimeException("You somehow managed to circumvent full switch on destination enum. What");
    }

    public void placePassage (int x, int y, Direction dir) {
        this.borders[getBorderByDirection(x, y, dir)] = new Wall(0);
    }

    public void placeBorderDefault (int x, int y, Direction dir, Class C) throws InstantiationException, IllegalAccessException{
        this.borders[getBorderByDirection(x, y, dir)] = (BorderObject) C.newInstance();
    }

    public BorderObject getBorderReference(int x, int y, Direction dir) {
        return this.borders[getBorderByDirection(x, y, dir)];
    }

}
