package com.netcracker.study.general;

import com.netcracker.study.general.Exceptions.NoSuchUserException;
import com.netcracker.study.objects.entities.Player;

import java.util.ArrayList;
import java.util.List;

class PlayerManager {
    private GameField gameField;
    private List<Player> players;

    PlayerManager(GameField gameField) {
        this.players = new ArrayList<>();
        this.gameField = gameField;
    }

    void addPlayer(int positionX, int positionY, String name) {
        if (!gameField.tileWithinGameBorders(positionX, positionY)) {
            throw new IllegalArgumentException("Attempt to add player beyond game borders");
        }

        Player player = new Player(positionX, positionY, name);
        players.add(player);
    }

    List<Player> getAllPlayersInTile(int X, int Y) {
        List<Player> playersToReturn = new ArrayList<>();

        for (Player player : this.players) {
            if ((player.getPositionX() == X) && (player.getPositionY() == Y)) {
                playersToReturn.add(player);
            }
        }

        return playersToReturn;
    }

    Player getPlayer(String name) throws NoSuchUserException {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        throw new NoSuchUserException("Cannot find user by the name of " + name);
    }

    Player getPlayer(int index) {
        return players.get(index);
    }

    int numberOfPlayers() {
        return players.size();
    }

}
