package com.netcracker.study.general.messaging;

import com.netcracker.study.objects.entities.Player;

public class EndTurnResult implements EventResult {
    private Player lastPlayer, newPlayer;

    @Override
    public EventTypes getEventType() {
        return EventTypes.TURN_END;
    }

    public EndTurnResult(Player lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public Player getNewPlayer() {
        return newPlayer;
    }
}
