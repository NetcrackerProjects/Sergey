package com.netcracker.study.general.messaging;

class MessageCreator {
    private Message message;
    private EventResult eventResult;

    MessageCreator(EventResult eventResult) {
        this.eventResult = eventResult;
    }

    Message generateMessage() {
        switch (eventResult.getEventType()) {
            case SHOOT:
                this.message = messageFromShootResult((ShootResult) eventResult);
                break;
            case MOVE:
                this.message = messageFromMoveResult((MoveResult) eventResult);
                break;
            case TURN_END:
                this.message = messageFromEndTurnResult((EndTurnResult) eventResult);
        }

        return message;
    }

    private Message messageFromShootResult(ShootResult shootResult) {
        StringBuilder messageText = new StringBuilder();
        messageText.append(shootResult.getPlayerWhoShot().getName() + " shoots to the " + shootResult.getDirection() + ". ");
        if (shootResult.isStoppedByWall()) {
            messageText.append("Bullet hits the wall. ");
        } else {
            if (shootResult.getKilledPlayers().size() > 0) {
                shootResult.getKilledPlayers().forEach(playerKilled -> messageText.append(playerKilled.getName() + " dies. "));
            }
        }
        return new Message(messageText.toString());
    }

    private Message messageFromMoveResult(MoveResult moveResult) {
        StringBuilder messageText = new StringBuilder();
        messageText.append(moveResult.getPlayerWhoMoved().getName() + " walks to the " + moveResult.getDirection() + ". ");
        if (moveResult.isStoppedByWall()) {
            messageText.append("But discovers that the wall is blocking his path.");
        }
        if (moveResult.isMovedOutsideLabyrinth()) {
            if (moveResult.getPlayerWhoMoved().hasTreasure()) {
                messageText.append("He manages to exit the labyrinth with treasure, and wins! ");
            } else {
                messageText.append("He realizes that the path ahead would have led him outside the labyrinth. He might return here once he get the treasure!");
            }
        }
        return new Message(messageText.toString());
    }

    private Message messageFromEndTurnResult(EndTurnResult turnResult) {
        StringBuilder messageText = new StringBuilder("Player " + turnResult.getLastPlayer().getName() + " ends his turn. ");
        messageText.append("Now is the turn of player " + turnResult.getNewPlayer().getName() + ".");
        return new Message(messageText.toString());
    }
}
