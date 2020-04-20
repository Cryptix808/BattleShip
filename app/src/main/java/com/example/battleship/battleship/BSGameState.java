package com.example.battleship.battleship;

import android.nfc.Tag;
import android.text.Html;
import android.util.Log;

import com.example.battleship.GameFramework.infoMessage.GameState;

public class BSGameState extends GameState {

    int count;
    public enum board { water, missed, hit, ship }

    public int[][] humanPlayerBoard;
    public int[][] computerPlayerBoard;

    private int player;
    private int turnCode;
    public int humanPlayerHits;
    public int computerPlayerHits;
    public int orientation;

    boolean cpuHasPlaced;
    boolean startGame;
    boolean inGame;
    boolean isAlpha;

    public Ship playerShips[];
    public Ship computerShips[];

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
        orientation = 1;
        cpuHasPlaced = false;
        startGame = false;
        inGame = false;

        playerShips = new Ship[5];
        computerShips = new Ship[5];

        playerShips[0] = new Ship(5, -1, -1, 1);
        playerShips[1] = new Ship(4, -1, -1, 1);
        playerShips[2] = new Ship(3, -1, -1, 1);
        playerShips[3] = new Ship(3, -1, -1, 1);
        playerShips[4] = new Ship(2, -1, -1, 1);

        computerShips[0] = new Ship(5, -1, -1, 1);
        computerShips[1] = new Ship(4, -1, -1, 1);
        computerShips[2] = new Ship(3, -1, -1, 1);
        computerShips[3] = new Ship(3, -1, -1, 1);
        computerShips[4] = new Ship(2, -1, -1, 1);
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
        orientation = bs.orientation;
        startGame = false;
        inGame = false;

        playerShips = new Ship[5];
        computerShips = new Ship[5];

        for (int i = 0; i < 5; i++) {
            playerShips[i] = bs.playerShips[i];
            computerShips[0] = bs.computerShips[i];
        }

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
        orientation = bs.orientation;
        inGame = true;
        turnCode = bs.turnCode;

        playerShips = new Ship[5];
        computerShips = new Ship[5];

        for (int i = 0; i < 5; i++) {
            playerShips[i] = bs.playerShips[i];
            computerShips[0] = bs.computerShips[i];
        }
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

        playerShips = new Ship[5];
        computerShips = new Ship[5];

        for (int i = 0; i < 5; i++) {
            playerShips[i] = bs.playerShips[i];
            computerShips[0] = bs.computerShips[i];
        }
    }

    public boolean placeShip(int x, int y) {

        Ship ship = null;

        for (int i = 0; i < 5; i++) {
            if (playerShips[i].selected) {
                ship = playerShips[i];
            }
        }
        if (ship == null) {
            return false;
        }

        if (orientation == 1) {
            if (ship.length + y > 10) {
                return false;
            }
        }
        if (orientation == 0) {
            if (ship.length + x > 10) {
                return false;
            }
        }
        for (int i = 0; i < ship.length; i++) {
            if(orientation == 0) {
                if (humanPlayerBoard[y][x+i] != board.water.ordinal()) {
                    return false;
                }
            }
            else{
                if(humanPlayerBoard[y+i][x] != board.water.ordinal()){
                    return false;
                }
            }
        }
        for (int i = 0; i < ship.length; i++) {
            if(orientation == 0) {
                humanPlayerBoard[y][x+i] = board.ship.ordinal();
            }
            else{
                humanPlayerBoard[y+i][x] = board.ship.ordinal();
            }
        }
        if(ship.placed){
            if(ship.orientation == 1){
                for(int i = 0; i < ship.length; i++){
                    humanPlayerBoard[ship.y+i][ship.x] = board.water.ordinal();
                }
            }
            else{
                for(int j = 0; j < ship.length; j++){
                    humanPlayerBoard[ship.y][ship.x+j] = board.water.ordinal();
                }
            }
        }
        ship.setShip(x, y, orientation);
        ship.placed = true;

        return true;
    }

    public boolean placeComputerShipsDumb(int pattern) {
        if(cpuHasPlaced){
            return false;
        }
        switch(pattern){
            case 1:
                computerShips[0].setShip(4, 2, 1);
                for (int i = 4; i < 9; i++) {
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[1].setShip(2, 4, 0);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[2][j] = board.ship.ordinal();
                }
                computerShips[2].setShip(0, 0, 1);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[3].setShip(7, 9, 1);
                for (int i = 7; i <= 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[4].setShip(5, 4, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                break;
            case 2:
                computerShips[0].setShip(0, 0, 1);
                for(int i = 0; i < 6; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();

                }
                computerShips[1].setShip( 0, 3, 1);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                computerShips[2].setShip(0, 4, 1);
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[3].setShip(0,1,1);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                computerShips[4].setShip(0,2,1);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                break;
            case 3:
                computerShips[0].setShip(0,0,0);
                for(int i = 0; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();

                }
                computerShips[1].setShip( 0, 3, 0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(0, 4, 0);
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 0, 1, 0);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(0,2,0);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                break;
            case 4:
                computerShips[0].setShip(3,4,1);
                for(int i = 3; i < 8; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();

                }
                computerShips[1].setShip( 3, 6, 1);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                computerShips[2].setShip(3, 3, 1);
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                computerShips[3].setShip( 7, 9, 1);
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[4].setShip(8,0,0);
                for(int i = 8; i <= 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;
            case 5:
                computerShips[0].setShip(0,9,0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,6,1);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                computerShips[2].setShip(3,3,0);
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(0,9,1);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[4].setShip(8,0,1);
                for(int i = 8; i <= 9; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                break;
            case 6:
                computerShips[0].setShip(0,9,0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(0,6,0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(0,5,0);
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(0,8,0);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(0,7,0);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[7][i] = board.ship.ordinal();
                }
                break;
            case 7:
                computerShips[0].setShip(3,3,0);
                for(int i = 3; i < 8; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,5,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(3,2,0);
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(3,4,0);
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                break;
            case 8:
                computerShips[0].setShip(2,4,1);
                for(int i = 2; i < 7; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[1].setShip(2,6,1);
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                computerShips[2].setShip(4,5,1);
                for(int i = 4; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerShips[3].setShip(2,3,1);
                for(int i = 2; i < 4; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                computerShips[4].setShip(2,5,1);
                for(int i = 2; i < 4; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                break;
            case 9:
                computerShips[0].setShip(0,0,1);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[1].setShip(2,1,1);
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                computerShips[2].setShip(7,0,1);
                for(int i = 7; i <= 9; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[3].setShip(5,0,1);
                for(int i = 5; i < 7; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[4].setShip(0,1,1);
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                break;
            case 10:
                computerShips[0].setShip(0,0,0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(2,1,0);
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(7,0,0);
                for(int i = 7; i <= 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(5,0,0);
                for(int i = 5; i < 7; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(0,1,0);
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                break;
            case 11:
                computerShips[0].setShip(2,3,1);
                for(int i = 2; i < 7; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                computerShips[1].setShip(2,5,1);
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerShips[2].setShip(3,0,0);
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(4,1,1);
                for(int i = 4; i < 6; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                computerShips[4].setShip(4,8,1);
                for(int i = 4; i < 6; i++){
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }
                break;
            case 12:
                computerShips[0].setShip(3,5,0);
                for(int i = 3; i < 8; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,2,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(4,2,1);
                for(int i = 4; i < 7; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[3].setShip(3,0,0);
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(3,9,0);
                for(int i = 3; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                break;
            case 13:
                computerShips[0].setShip(0,0,1);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[1].setShip(0,9,1);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[2].setShip(6,0,1);
                for(int i = 6; i < 9; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[3].setShip(6,9,1);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[4].setShip(8,9,1);
                for(int i = 8; i <= 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                break;
            case 14:
                computerShips[0].setShip(0,4,1);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[1].setShip(0,2,1);
                for(int i = 0; i < 4; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[2].setShip(5,4,1);
                for(int i = 5; i < 8; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[3].setShip(4,2,1);
                for(int i = 4; i < 6; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[4].setShip(6,2,1);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                break;
            case 15:
                computerShips[0].setShip(1,4,0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,5,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(6,4,0);
                for(int i = 6; i < 9; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(1,5,0);
                for(int i = 1; i < 3; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(7,5,0);
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                break;
            case 16:
                computerShips[0].setShip(1,1,0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,8,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(6,1,0);
                for(int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(1,8,0);
                for(int i = 1; i < 3; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(7,8,0);
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                break;
            case 17:
                computerShips[0].setShip(1,1,0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,2,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(6,1,0);
                for(int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(1,2,0);
                for(int i = 1; i < 3; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(7,2,0);
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                break;
            case 18:
                computerShips[0].setShip(1,6,0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(3,7,0);
                for(int i = 3; i < 7; i++){
                    computerPlayerBoard[7][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(6,6,0);
                for(int i = 6; i < 9; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(1,7,0);
                for(int i = 1; i < 3; i++){
                    computerPlayerBoard[7][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(7,7,0);
                for(int i = 7; i < 9; i++){
                    computerPlayerBoard[7][i] = board.ship.ordinal();
                }
                break;
            case 19:
                computerShips[0].setShip(0,5,0);
                for(int i = 0; i < 5; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(2,2,0);
                for(int i = 2; i < 6; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(0,9,0);
                for(int i = 0; i < 3; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(6,0,0);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(6,9,0);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                break;
            case 20:
                computerShips[0].setShip(2,5,1);
                for(int i = 2; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerShips[1].setShip(1,8,0);
                for(int i = 1; i < 5; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[2].setShip(3,0,0);
                for(int i = 3; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[3].setShip(1,8,0);
                for(int i = 1; i < 3; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(4,2,1);
                for(int i = 4; i < 6; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                break;

        }
        cpuHasPlaced = true;
        return true;
    }

    public boolean placeComputerShipsSmart(int pattern) {
        if(cpuHasPlaced){
            return false;
        }
        switch(pattern) {
            case 1:
                computerShips[0].setShip(1, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                computerShips[1].setShip(3, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][3] = board.ship.ordinal();
                }
                computerShips[2].setShip(5, 3, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerShips[3].setShip(4, 1, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(8, 5, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }

                break;
            case 2:
                computerShips[0].setShip(4, 2,  1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[1].setShip( 6, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                computerShips[2].setShip( 6, 0, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 1, 8, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[4].setShip( 1, 2, 1);
                for (int i = 2; i < 4; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }

                break;
            case 4:
                computerShips[0].setShip( 0, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                computerShips[1].setShip( 8, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][8] = board.ship.ordinal();
                }
                computerShips[2].setShip( 5, 2, 1);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                computerShips[3].setShip( 3, 8, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(0, 9, 0);
                for (int i = 0; i < 2; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }

                break;
            case 5:
                computerShips[0].setShip( 7, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                computerShips[1].setShip( 1, 1, 1);
                for (int j = 1; j < 5; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                computerShips[2].setShip( 4, 2, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 1, 8, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                computerShips[4].setShip( 3, 6, 0);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }

                break;
            case 6:
                computerShips[0].setShip( 3, 3, 0);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                computerShips[1].setShip( 3, 7, 0);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[7][j] = board.ship.ordinal();
                }
                computerShips[2].setShip( 7, 4, 0);
                for (int i =7; i < 10; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 2, 5, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[4].setShip(8, 8, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }

                break;
            case 7:
                computerShips[0].setShip( 3, 6, 0);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                computerShips[1].setShip(1, 2, 1);
                for (int j = 2; j < 6; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                computerShips[2].setShip( 7, 4, 1);
                for (int i =7; i < 10; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 2, 5, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                computerShips[4].setShip( 8, 8, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }

                break;
            case 8:
                computerShips[0].setShip(5, 1, 0);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[1].setShip( 7, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerShips[2].setShip( 4, 6, 1);
                for (int i =6; i < 9; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                computerShips[3].setShip( 2, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[4].setShip( 0, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }

                break;
            case 9:
                computerShips[0].setShip( 5, 1, 0);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[1].setShip( 7, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerShips[2].setShip( 6, 3, 1);
                for (int i =3; i < 6; i++){
                    computerPlayerBoard[i][6] = board.ship.ordinal();
                }
                computerShips[3].setShip( 0, 1, 0);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                computerShips[4].setShip( 5, 8, 1);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }

                break;
            case 10:
                computerShips[0].setShip( 9, 3, 1);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                computerShips[1].setShip( 7, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                computerShips[2].setShip( 4, 6, 1);
                for (int i =6; i < 9; i++){
                    computerPlayerBoard[4][i] = board.ship.ordinal();
                }
                computerShips[3].setShip( 2, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                computerShips[4].setShip( 0, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }

                break;
            case 11:
                //carrier
                computerShips[0].setShip( 1, 3, 0);
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                //battleship
                computerShips[1].setShip(1, 6, 0);
                for(int i = 1; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                //submarine
                computerShips[2].setShip( 5, 6, 1);
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //cruiser
                computerShips[3].setShip( 1, 0, 0);
                for(int i = 1; i < 4; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                //destroyer
                computerShips[4].setShip( 7, 0, 1);
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }

                break;
            case 12:
                //carrier (5)
                computerShips[0].setShip( 2, 1, 0);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //battleship (4)
                computerShips[1].setShip( 4, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][4] = board.ship.ordinal();
                }
                //submarine (3)
                computerShips[2].setShip( 7, 3, 1);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)
                computerShips[3].setShip( 2, 9, 0);
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //destroyer (2)
                computerShips[4].setShip( 1, 3, 1);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[i][1] = board.ship.ordinal();
                }
                break;
            case 13:
                //carrier (5)
                computerShips[0].setShip( 9, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //battleship (4)
                computerShips[1].setShip( 6, 0, 1);
                for (int j = 0; j < 4; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 4, 9, 0);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 1, 1, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 3, 5, 1);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                break;
            case 14:
                //carrier (5)

                computerShips[0].setShip( 0, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 7, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 3, 3, 1);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 4, 5, 1);
                for (int i = 5; i < 8; i++){
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 5, 1, 0);
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                break;
            case 15:
                //carrier (5)

                computerShips[0].setShip( 1, 8, 0);
                for (int i = 1; i < 6; i++) {
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 7, 3, 1);
                for (int j = 3; j < 7; j++){
                    computerPlayerBoard[j][7] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 6, 1, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 1, 5, 0);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 2, 1, 1);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                break;
            case 16:
                //carrier (5)

                computerShips[0].setShip( 0, 1, 1);
                for (int i = 1; i < 6; i++) {
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 1, 9, 0);
                for (int j = 1; j < 5; j++){
                    computerPlayerBoard[9][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 9, 5, 1);
                for (int i = 5; i < 8; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 9, 1, 1);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 4, 0, 0);
                for (int i = 4; i < 6; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;
            case 17:
                //carrier (5)

                computerShips[0].setShip( 4, 2, 1);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[i][4] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 1, 5, 1);
                for (int j = 5; j < 9; j++){
                    computerPlayerBoard[j][1] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 7, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 6, 1, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 1, 1, 0);
                for (int i = 1; i < 3; i++){
                    computerPlayerBoard[1][i] = board.ship.ordinal();
                }
                break;
            case 18:
                //carrier (5)

                computerShips[0].setShip( 2, 3, 1);
                for (int i = 3; i < 8; i++) {
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 6, 4, 1);
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[j][6] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 8, 1, 1);
                for (int i = 1; i < 4; i++){
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 3, 2, 0);
                for (int i = 3; i < 6; i++){
                    computerPlayerBoard[2][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 4, 9, 0);
                for (int i = 4; i < 6; i++){
                    computerPlayerBoard[9][i] = board.ship.ordinal();
                }
                break;
            case 19:
                //carrier (5)

                computerShips[0].setShip( 8, 5, 1);
                for (int i = 5; i < 10; i++) {
                    computerPlayerBoard[i][8] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 0, 0, 0);
                for (int j = 0; j < 4; j++){
                    computerPlayerBoard[0][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 7, 0, 1);
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 5, 4, 1);
                for (int i = 4; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 3, 8, 0);
                for (int i = 3; i < 5; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                break;
            case 20:
                //carrier (5)

                computerShips[0].setShip( 2, 5, 0);
                for (int i = 2; i < 7; i++) {
                    computerPlayerBoard[5][i] = board.ship.ordinal();
                }
                //battleship (4)

                computerShips[1].setShip( 2, 1, 0);
                for (int j = 2; j < 6; j++){
                    computerPlayerBoard[1][j] = board.ship.ordinal();
                }
                //submarine (3)

                computerShips[2].setShip( 3, 7, 1);
                for (int i = 7; i < 10; i++){
                    computerPlayerBoard[i][3] = board.ship.ordinal();
                }
                //cruiser (3)

                computerShips[3].setShip( 6, 8, 0);
                for (int i = 6; i < 9; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }
                //destroyer (2)

                computerShips[4].setShip( 8, 0, 0);
                for (int i = 8; i < 10; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;

        }
        return true;

    }

    public boolean switchPhase() {
        int cpuShipCounter = 0;
        int playerShipCounter = 0;
        do {
            cpuShipCounter = 0;
            playerShipCounter = 0;
            if (!cpuHasPlaced) {
                placeComputerShipsDumb((int) (Math.random() * 19) + 1);
            }
            for(int i = 0; i < 10; i++){
                for(int j = 0; j <10; j++){
                    if(humanPlayerBoard[i][j] == board.ship.ordinal()){
                        playerShipCounter++;
                    }
                    if (computerPlayerBoard[i][j] == board.ship.ordinal()) {
                        cpuShipCounter++;
                    }
                }
            }
            if (cpuShipCounter != 17) {
                cpuHasPlaced = false;
                cpuShipCounter = 0;
                playerShipCounter = 0;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        computerPlayerBoard[i][j] = 0;
                    }
                }
            }

        } while (cpuShipCounter != 17);

        if(cpuShipCounter == 17 && playerShipCounter == 17){
            startGame = true;
            return true;
        }

        return false;
    }

    public boolean fireHumanPlayer(int x, int y){
        if (turnCode == 1) {
            return false;
        }
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
        if(humanPlayerBoard[x][y] == board.water.ordinal() || humanPlayerBoard[x][y] == board.missed.ordinal() && turnCode == 1) {
            humanPlayerBoard[x][y] = board.missed.ordinal();
            turnCode = 0;
            return true;
        }
        if (humanPlayerBoard[x][y] == board.ship.ordinal() || humanPlayerBoard[x][y] == board.missed.ordinal() && turnCode == 1) {
            humanPlayerBoard[x][y] = board.hit.ordinal();
            turnCode = 0;
            computerPlayerHits++;
            return true;
        }
        return false;
    }

    public void switchOr() {
        if (orientation == 1) {
            orientation = 0;
        }
        else {
            orientation = 1;
        }
    }

    public boolean select(int selectedShip){
        for(int i = 0; i < 5; i++) {
            playerShips[i].selected = false;
        }
        playerShips[selectedShip].selected = true;
        return true;
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
