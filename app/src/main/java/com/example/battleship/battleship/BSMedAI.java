package com.example.battleship.battleship;

import com.example.battleship.GameFramework.Game;
import com.example.battleship.GameFramework.GameComputerPlayer;
import com.example.battleship.GameFramework.infoMessage.GameInfo;

public class BSMedAI extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */

    int posI;
    int posJ;
    int attkX;
    int attkY;

    public BSMedAI(String name) {
        super(name);
        posI = -1;
        posJ = -1;
        attkX = -1;
        attkY = -1;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof BSGameState) {
            if (((BSGameState) info).inGame == false) {
                game.sendAction(new BSPlaceCPUShip(this, ((int) (Math.random()) * 19 + 1)));
            }
            if (((BSGameState) info).getTurnCode() == 0) {
                return;
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(((BSGameState) info).humanPlayerBoard[i][j] == 2) {
                        // if all water store attks
                        if((i+1 == 10 || ((BSGameState) info).humanPlayerBoard[i+1][j] != 2)
                                &&  (j+1 == 10 || ((BSGameState) info).humanPlayerBoard[i][j+1] != 2)
                                && (i-1 == -1 || ((BSGameState) info).humanPlayerBoard[i-1][j] != 2)
                                && (j-1 == -1 || ((BSGameState) info).humanPlayerBoard[i][j-1] != 2)) {
                            if ((i+1 == 10 || ((BSGameState) info).humanPlayerBoard[i+1][j] != 1)) {
                                if(i != 9) {
                                    attkX = i + 1;
                                    attkY = j;
                                }
                            }
                            if ((j+1 == 10 || ((BSGameState) info).humanPlayerBoard[i][j+1] != 1)) {
                                if(j != 9) {
                                    attkX = i;
                                    attkY = j + 1;
                                }
                            }
                            if ((i-1 == -1 || ((BSGameState) info).humanPlayerBoard[i-1][j] != 1)) {
                                if(i != 0) {
                                    attkX = i - 1;
                                    attkY = j;
                                }
                            }
                            if ((j-1 == -1 || ((BSGameState) info).humanPlayerBoard[i][j-1] != 1)) {
                                if(j != 0) {
                                    attkX = i;
                                    attkY = j - 1;
                                }

                            }
                        }
                        posI = i + 1;
                        while(posI < 10 && ((BSGameState) info).humanPlayerBoard[posI][j] == 2){
                            posI++;
                            if ( posI < 10 && ((BSGameState) info).humanPlayerBoard[posI][j] == 0) {
                                game.sendAction(new BSFire(this, posI, j));
                                posI = -1;
                                posJ = -1;
                                attkX = -1;
                                attkY = -1;
                                return;
                            }
                        }
                        posJ = j + 1;
                        while(posJ < 10 && ((BSGameState) info).humanPlayerBoard[i][posJ] == 2){
                            posJ++;
                            if ( posJ < 10 && ((BSGameState) info).humanPlayerBoard[i][posJ] == 0) {
                                game.sendAction(new BSFire(this, i, posJ));
                                posI = -1;
                                posJ = -1;
                                attkX = -1;
                                attkY = -1;
                                return;
                            }
                        }
                        posI = i - 1;
                        while(posI >= 0 && ((BSGameState) info).humanPlayerBoard[posI][j] == 2){
                            posI--;
                            if ( posI >= 0 && ((BSGameState) info).humanPlayerBoard[posI][j] == 0) {
                                game.sendAction(new BSFire(this, posI, j));
                                posI = -1;
                                posJ = -1;
                                attkX = -1;
                                attkY = -1;
                                return;
                            }
                        }
                        posJ = j - 1;
                        while(posJ < 10 && ((BSGameState) info).humanPlayerBoard[i][posJ] == 2){
                            posJ--;
                            if ( posJ < 10 && ((BSGameState) info).humanPlayerBoard[i][posJ] == 0) {
                                game.sendAction(new BSFire(this, i, posJ));
                                posI = -1;
                                posJ = -1;
                                attkX = -1;
                                attkY = -1;
                                return;
                            }
                        }
                    }
                }
            }

            if(attkX != -1) {
                game.sendAction(new BSFire(this, attkX, attkY));
            }

            sleep(.75);
            game.sendAction(new BSFire(this, randomX(), randomY()));
            return;
        }
        else {
            return;
        }

    }

    public int randomX() {
        return (int) (Math.random() * 10);
    }
    public int randomY() {
        return  (int) (Math.random() * 10);
    }

}