package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GameComputerPlayer;
import com.example.battleship.GameFramework.infoMessage.GameInfo;

public class BSMedAI extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public BSMedAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof BSGameState) {
            if (((BSGameState) info).getTurnCode() == 0) {
                return;
            }
        } else {
            return;
        }
        if(((BSGameState) info).inGame == false){
            game.sendAction(new BSPlaceCPUShip(this, 1));
            // game.sendAction(new BSPlaceCPUShip(this, ((int)(Math.random()) * 19 + 1)));
        }

        int randX, randY;
        int medX, medY;
        do {
            randX = randomX();
            randY = randomY();
        } while (((BSGameState) info).humanPlayerBoard[randX][randY] == 1 ||
                ((BSGameState) info).humanPlayerBoard[randX][randY] == 2);
        do{

        } while (((BSGameState) info).computerPlayerHits == ((BSGameState) info).computerPlayerHits + 1);

        sleep(.75);

        game.sendAction(new BSFire(this, randX, randY));

    }


    public int randomX() {
        return (int) (Math.random() * 10);
    }
    public int randomY() {
        return  (int) (Math.random() * 10);
    }
    }

