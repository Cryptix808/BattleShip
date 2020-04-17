package com.example.battleship.battleship;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.battleship.GameFramework.infoMessage.GameState;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;


public class BSGameState extends GameState {

    int count;
    public enum board { water, missed, hit, ship }

    public int[][] humanPlayerBoard;
    public int[][] computerPlayerBoard;
    private int player;
    private int turnCode;
    public int humanPlayerHits;
    public int computerPlayerHits;
    boolean cpuHasPlaced;
    boolean startGame;
    boolean inGame;
    boolean isAlpha;

    public Ship playerBattleship;
    public Ship playerAircraftCarrier;
    public Ship playerCruiser;
    public Ship playerSubmarine;
    public Ship playerDestroyer;
    public Ship computerBattleship;
    public Ship computerAircraftCarrier;
    public Ship computerCruiser;
    public Ship computerSubmarine;
    public Ship computerDestroyer;

    public int getHumanPlayerHits() {
        return humanPlayerHits;
    }
    public boolean getCPUhasPlaced(){ return cpuHasPlaced;  }
    public int getComputerPlayerHits() {
        return computerPlayerHits;
    }
    public int getPlayer(){return player;}
    public int getTurnCode(){return turnCode;}
    public int[][] getHumanPlayerBoard(){
        return humanPlayerBoard;
    }
    public int[][] getComputerPlayerBoard(){
        return computerPlayerBoard;
    }
    public void setPlayer(int player) {
        this.player = player;
    }

    public BSGameState(){
        humanPlayerBoard = new int[10][10];
        computerPlayerBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                humanPlayerBoard[i][j] = board.water.ordinal();
                computerPlayerBoard[i][j] = board.water.ordinal();
            }
        }
        player = 0;
        humanPlayerHits = 0;
        computerPlayerHits = 0;
        cpuHasPlaced = false;
        startGame = false;
        inGame = false;

        playerBattleship = new Ship(4, -1, -1, 1);
        playerAircraftCarrier = new Ship(5, 5, 5, 0);
        playerCruiser = new Ship(5, -1, -1, 1);
        playerSubmarine = new Ship(5, -1, -1, 1);
        playerDestroyer = new Ship(5, -1, -1, 1);
        computerBattleship = new Ship(5, -1, -1, 1);
        computerAircraftCarrier = new Ship(5, -1, -1, 1);
        computerCruiser = new Ship(5, -1, -1, 1);
        computerSubmarine = new Ship(5, -1, -1, 1);
        computerDestroyer = new Ship(5, -1, -1, 1);
    }
    public BSGameState(boolean alpha){
        isAlpha = true;
        humanPlayerBoard = new int[10][10];
        computerPlayerBoard = new int[10][10];
        player = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                humanPlayerBoard[i][j] = board.water.ordinal();
                computerPlayerBoard[i][j] = board.water.ordinal();
            }
        }
        // initalize rest of data
        humanPlayerHits = 0;
        computerPlayerHits = 0;
        inGame = true;
        turnCode = 0;


        }


    // placing ships
    public BSGameState(BSGameState bs, int[][] humanPlayerBoard, int [][] computerPlayerBoard, boolean cpuHasPlaced) {
        this.humanPlayerBoard = new int[10][10];
        this.computerPlayerBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = computerPlayerBoard[i][j];
            }
        }
        this.cpuHasPlaced = cpuHasPlaced;
        startGame = false;
        inGame = false;

        playerBattleship = new Ship(bs.playerBattleship);
        playerAircraftCarrier = new Ship(bs.playerAircraftCarrier);
        playerCruiser = new Ship(bs.playerCruiser);
        playerSubmarine = new Ship(bs.playerSubmarine);
        playerDestroyer = new Ship(bs.playerDestroyer);
        computerBattleship = new Ship(bs.computerBattleship);
        computerAircraftCarrier = new Ship(bs.computerAircraftCarrier);
        computerCruiser = new Ship(bs.computerCruiser);
        computerSubmarine = new Ship(bs.computerSubmarine);
        computerDestroyer = new Ship(bs.computerDestroyer);

    }

    // starting game
    public BSGameState(int[][] humanPlayerBoard, int [][] computerPlayerBoard, BSGameState bs) {
        this.humanPlayerBoard = new int[10][10];
        this.computerPlayerBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = computerPlayerBoard[i][j];
            }
        }
        // initalize rest of data
        humanPlayerHits = 0;
        computerPlayerHits = 0;
        inGame = true;

        playerBattleship = new Ship(bs.playerBattleship);
        playerAircraftCarrier = new Ship(bs.playerAircraftCarrier);
        playerCruiser = new Ship(bs.playerCruiser);
        playerSubmarine = new Ship(bs.playerSubmarine);
        playerDestroyer = new Ship(bs.playerDestroyer);
        computerBattleship = new Ship(bs.computerBattleship);
        computerAircraftCarrier = new Ship(bs.computerAircraftCarrier);
        computerCruiser = new Ship(bs.computerCruiser);
        computerSubmarine = new Ship(bs.computerSubmarine);
        computerDestroyer = new Ship(bs.computerDestroyer);
    }

    // in game
    public BSGameState(BSGameState bs) {
        this.humanPlayerBoard = new int[10][10];
        this.computerPlayerBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = bs.humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = bs.computerPlayerBoard[i][j];
            }
        }
        // initalize rest of data
        turnCode = bs.turnCode;
        humanPlayerHits = bs.humanPlayerHits;
        computerPlayerHits = bs.computerPlayerHits;
        inGame = true;
        //deep copy ships
        //ship = new Ship(ship);
        playerAircraftCarrier = new Ship(bs.playerAircraftCarrier);
        playerBattleship = new Ship(bs.playerBattleship);
        playerCruiser = new Ship(bs.playerCruiser);
        playerSubmarine = new Ship(bs.playerSubmarine);
        playerDestroyer = new Ship(bs.playerDestroyer);
        computerAircraftCarrier = new Ship(bs.computerAircraftCarrier);
        computerBattleship = new Ship(bs.computerBattleship);
        computerCruiser = new Ship(bs.computerCruiser);
        computerSubmarine = new Ship(bs.computerSubmarine);
        computerDestroyer = new Ship(bs.computerDestroyer);
    }
    public boolean placeShip(int length, int x, int y, int orientation) {

        if (orientation == 0) {
            if (length + y < 9) {
                return false;
            }
        }
        if (orientation == 1) {
            if (length + x < 9) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            if (humanPlayerBoard[x][y] != board.water.ordinal()) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            humanPlayerBoard[x][y] = board.ship.ordinal();
        }
        return true;
    }

    public boolean placeComputerShipsDumb(int pattern) {
        if(cpuHasPlaced){
            return false;
        }
        switch(pattern){
            case 1:
                computerAircraftCarrier = new Ship(5, 4, 2, 1);
                    for (int i = 4; i < 9; i++) {
                        computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 7, 9, 1);
                for (int i = 7; i <= 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 5, 4, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 2, 4, 0);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[2][j] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 0, 0, 1);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                break;
            case 2:
                //carrier
                for(int i = 0; i < 6; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();

                }
                //submarine
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                //battleship
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                break;
            case 3:
                //carrier
                for(int i = 0; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();

                }
                //submarine
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                //battleship
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                break;
            case 4:
                //carrier
                for(int i = 3; i < 8; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();

                }
                //submarine
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 8; i <= 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                //battleship
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                break;
            case 5:
                //carrier
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //submarine
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 8; i <= 9; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //battleship
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                break;
            case 6:
                //carrier
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //submarine
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[7][i] = board.ship.ordinal();
                }
                //battleship
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                break;
            case 7:
                //carrier
                for(int i = 3; i < 8; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                //submarine
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                //battleship
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                break;
            case 8:
                //carrier
                for(int i = 2; i < 7; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                //submarine
                for(int i = 2; i < 4; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 2; i < 4; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //battleship
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 4; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                break;
            case 9:
                //carrier
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //submarine
                for(int i = 5; i < 7; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                //battleship
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 7; i <= 9; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                break;
            case 10:
                //carrier
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                //submarine
                for(int i = 5; i < 7; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //battleship
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 7; i <= 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;


        }
        return true;
    }

    public boolean placeComputerShipsSmart(int pattern) {
        if(cpuHasPlaced){
            return false;
        }
        switch(pattern) {
            case 1:
                computerAircraftCarrier = new Ship(5, 1, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 4, 3, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][3] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 5, 3, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 1, 4, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 8, 5, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }

                break;
            case 2:
                computerAircraftCarrier = new Ship(5, 4, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 6, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 6, 0, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 1, 8, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 1, 2, 1);
                for (int i = 2; i < 4; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }

                break;
            case 4:
                computerAircraftCarrier = new Ship(5, 0, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 8, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][8] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 5, 2, 1);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 3, 8, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 0, 9, 0);
                for (int i = 0; i < 2; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }

                break;
            case 5:
                computerAircraftCarrier = new Ship(5, 7, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 1, 1, 1);
                for (int j = 1; j < 5; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 4, 2, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 1, 8, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 3, 6, 0);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }

                break;
            case 6:
                computerAircraftCarrier = new Ship(5, 3, 2, 0);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 3, 7, 0);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[7][j] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 7, 4, 0);
                for (int i =7; i < 10; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 2, 5, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 8, 8, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }

                break;
            case 7:
                computerAircraftCarrier = new Ship(5, 3, 6, 0);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 1, 2, 1);
                for (int j = 2; j < 6; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 7, 4, 1);
                for (int i =7; i < 10; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 2, 5, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 8, 8, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }

                break;
            case 8:
                computerAircraftCarrier = new Ship(5, 5, 1, 0);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 7, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 4, 6, 1);
                for (int i =6; i < 9; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 2, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 0, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }

                break;
            case 9:
                computerAircraftCarrier = new Ship(5, 5, 1, 0);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 2, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 6, 3, 1);
                for (int i =3; i < 6; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 0, 1, 0);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 5, 8, 1);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }

                break;
            case 10:
                computerAircraftCarrier = new Ship(5, 9, 3, 1);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerBattleship = new Ship(4, 7, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerSubmarine = new Ship(3, 4, 6, 1);
                for (int i =6; i < 9; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerCruiser = new Ship(3, 2, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerDestroyer = new Ship(2, 0, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }

                break;
            case 11:
                //carrier
                computerAircraftCarrier = new Ship(5, 1, 3, 0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                //battleship
                computerBattleship = new Ship(4, 1, 6, 0);
                for(int i = 1; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                //submarine
                computerSubmarine = new Ship(3, 5, 6, 1);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //cruiser
                computerCruiser = new Ship(3, 1, 0, 0);
                for(int i = 1; i < 4; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                //destroyer
                computerDestroyer = new Ship(2, 7, 0, 1);
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }

                break;
            case 12:
                //carrier (5)
                computerAircraftCarrier = new Ship(5, 2, 1, 0);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //battleship (4)
                computerBattleship = new Ship(4, 4, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][4] = board.ship.ordinal();
                }
                //submarine (3)
                computerSubmarine = new Ship(3, 7, 3, 1);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)
                computerCruiser = new Ship(3, 2, 9, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //destroyer (2)
                computerDestroyer = new Ship(2, 1, 3, 1);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                break;
            case 13:
                //carrier (5)
                computerAircraftCarrier = new Ship(5, 9, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //battleship (4)
                computerBattleship = new Ship(4, 6, 0, 1);
                for (int j = 0; j < 4; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 4, 9, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 1, 1, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 3, 5, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                break;
            case 14:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 0, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 7, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 3, 3, 1);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 4, 5, 1);
                for (int i = 5; i < 8; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 5, 1, 0);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                break;
            case 15:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 1, 8, 0);
                for (int i = 1; i < 6; i++) {
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 7, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 6, 1, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 1, 5, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 2, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                break;
            case 16:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 0, 1, 1);
                for (int i = 1; i < 6; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 1, 1, 0);
                for (int j = 1; j < 5; j++){
                    computerPlayerBoard[9][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 9, 5, 1);
                for (int i = 5; i < 8; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 9, 1, 1);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 4, 0, 0);
                for (int i = 4; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;
            case 17:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 4, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 1, 5, 1);
                for (int j = 5; j < 9; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 7, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 6, 1, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 1, 1, 0);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                break;
            case 18:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 2, 3, 1);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 6, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 8, 1, 1);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 3, 2, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 4, 9, 0);
                for (int i = 4; i < 6; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                break;
            case 19:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 8, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 0, 0, 0);
                for (int j = 0; j < 4; j++){
                    computerPlayerBoard[0][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 7, 0, 1);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 4, 5, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 8, 3, 0);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                break;
            case 20:
                //carrier (5)

                computerAircraftCarrier = new Ship(5, 2, 5, 0);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //battleship (4)

                computerBattleship = new Ship(4, 2, 1, 0);
                for (int j = 2; j < 6; j++){
                    computerPlayerBoard[1][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerSubmarine = new Ship(3, 3, 7, 1);
                for (int i = 7; i < 10; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //cruiser (3)

                computerCruiser = new Ship(3, 6, 8, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerDestroyer = new Ship(2, 8, 0, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;

        }
        return true;
    }

    public boolean fireHumanPlayer(int x, int y){
        if(computerPlayerBoard[x][y] == board.water.ordinal() && turnCode == 0) {
            computerPlayerBoard[x][y] = board.missed.ordinal();
            turnCode = 1;
            return true;
        }
        if (computerPlayerBoard[x][y] == board.ship.ordinal() && turnCode == 0) {
            computerPlayerBoard[x][y] = board.hit.ordinal();
            turnCode = 1;
            humanPlayerHits++;
            return true;
        }
        return false;
    }

    public boolean fireComputerPlayer(int x, int y){
        if(humanPlayerBoard[x][y] == board.water.ordinal() && turnCode == 1) {
            humanPlayerBoard[x][y] = board.missed.ordinal();
            turnCode = 0;
            return true;
        }
        if (humanPlayerBoard[x][y] == board.ship.ordinal() && turnCode == 1) {
            humanPlayerBoard[x][y] = board.hit.ordinal();
            turnCode = 0;
            computerPlayerHits++;
            return true;
        }
        return false;
    }


    public int getNextPlayer() {
        if (player == 1) {
            return 0;
        }
        if (player == 0) {
            return 1;
        }
        return player;
    }

    public int winner() {
        if(humanPlayerHits == 17) {
            return 1;
        }
        if(computerPlayerHits == 17){
            return 2;
        }
        return 0;
    }

    public int getWinner() {return winner();}

}
