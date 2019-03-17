package com.netcracker.study.general;

import com.netcracker.study.objects.borders.BorderObject;
import com.netcracker.study.objects.borders.Wall;
import com.netcracker.study.objects.entities.Player;

import java.util.HashSet;
import java.util.Set;

public class GameField {
    private int width;
    private int height;
    private BorderObject[] borders;
    private Set<Player> players;


    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        this.borders = new BorderObject[totalCountOfBorders(width, height)];
        this.players = new HashSet();
    }

    public void addPlayer(int positionX, int positionY, String name){
        Player player = new Player(positionX, positionY, name);
        players.add(player);
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }

    public int totalCountOfBorders(int width, int height){
        return ((2*width+1)*height + width);
    }

    private Integer getBorderBetweenTiles(int x1, int x2, int y1, int y2) throws IllegalArgumentException {

        if (!tilesAreAdjacent(x1, x2, y1, y2)) {
            throw new IllegalArgumentException("Tiles selected are not adjacent => border cannot be specified!");
        }
        if (x1 - x2 == 0 && y1 - y2 == 0) {
            throw new IllegalArgumentException("Tiles selected are actually the same tile => unable to determine border");
        }
        if (!tileWithinGameBorders(x1, y1) && !tileWithinGameBorders(x2,y2)) {
            throw new IllegalArgumentException("One of the tiles is not at least adjacent to game space!");
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

    public Set<Player> getAllPlayersInTile(int X, int Y){
        Set<Player> playersToReturn = new HashSet<>();
        for (Player player : this.players) {
            if ((player.getPositionX() == X) &&(player.getPositionY() == Y)) {
                playersToReturn.add(player);
            }
        }
        return playersToReturn;
    }

    private boolean tilesAreAdjacent(int x1, int x2, int y1, int y2) {
        if (Math.abs(x1 - x2) > 1) return false;
        if  (Math.abs(y1 - y2) > 1) return false;
        if (x1 - x2 != 0 && y1 - y2 != 0) return false;
        return true;
    }

    private boolean tileWithinGameBorders(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x > width || y > height ) return false;
        return true;
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

    public void placeWall (int x, int y, Direction dir, int thickness) {
        this.borders[getBorderByDirection(x, y, dir)] = new Wall(thickness);
    }

    public void placeBorderDefault (int x, int y, Direction dir, Class C) throws InstantiationException, IllegalAccessException{
        this.borders[getBorderByDirection(x, y, dir)] = (BorderObject) C.newInstance();
    }

    public void placeBorder (int x, int y, Direction dir, BorderObject border) {
        this.borders[getBorderByDirection(x, y, dir)] = border;
    }

    public BorderObject getBorderReference(int x, int y, Direction dir) {
        return this.borders[getBorderByDirection(x, y, dir)];
    }

}
