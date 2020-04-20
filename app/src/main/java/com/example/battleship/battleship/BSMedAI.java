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
        int prevX, prevY;




        do {
            randX = randomX();
            randY = randomY();

            prevX = randX;
            prevY = randY;
        } while (((BSGameState) info).humanPlayerBoard[randX][randY] == 1 ||
                ((BSGameState) info).humanPlayerBoard[randX][randY] == 2);

        sleep(.75);

        game.sendAction(new BSFire(this, randX, randY));



        if(((BSGameState) info).computerPlayerHits == ((BSGameState) info).computerPlayerHits + 1) {
            switch (rando()){
                case 1:
                    medX = medX1();
                    medY = prevY;
                    sleep(.75);

                    game.sendAction(new BSFire(this, medX, medY));
                    break;
                case 2:
                    medX = prevX;
                    medY = medY1();
                    sleep(.75);

                    game.sendAction(new BSFire(this, medX, medY));
                    break;
                case 3:
                    medX = medX2();
                    medY = prevY;
                    sleep(.75);

                    game.sendAction(new BSFire(this, medX, medY));
                    break;
                case 4:
                    medX = prevX;
                    medY = medY2();
                    sleep(.75);

                    game.sendAction(new BSFire(this, medX, medY));
                    break;
            }
        }




    }



    public int rando() {
        return (int) (Math.random() * 4);
    }
    public int randomX() {
        return (int) (Math.random() * 10);
    }
    public int randomY() {
        return  (int) (Math.random() * 10);
    }

    public int medX1(){
        return + 1;
    }
    public int medY1(){
        return + 1;
    }
    public int medX2(){
        return - 1;
    }
    public int medY2(){
        return - 1;
    }
    }

