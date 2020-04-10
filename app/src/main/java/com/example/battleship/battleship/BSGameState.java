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
    // inital

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
        //carrier
        for (int i = 4; i < 9; i++) {
            humanPlayerBoard[i][3] = board.ship.ordinal();
        }
        //submarine
        for (int i = 7; i <= 9; i++){
            humanPlayerBoard[9][i] = board.ship.ordinal();
        }
        //destroyer
        for (int i = 5; i < 7; i++){
            humanPlayerBoard[5][i] = board.ship.ordinal();
        }
        //battleship
        for (int j = 4; j < 8; j++){
            humanPlayerBoard[j][2] = board.ship.ordinal();
        }
        //cruiser
        for (int i = 0; i < 3; i++){
            humanPlayerBoard[0][i] = board.ship.ordinal();
        }

        //carrier
        for (int i = 4; i < 9; i++) {
            computerPlayerBoard[i][2] = board.ship.ordinal();
        }
        //submarine
        for (int i = 7; i <= 9; i++){
            computerPlayerBoard[i][9] = board.ship.ordinal();
        }
        //destroyer
        for (int i = 5; i < 7; i++){
            computerPlayerBoard[i][5] = board.ship.ordinal();
        }
        //battleship
        for (int j = 4; j < 8; j++){
            computerPlayerBoard[2][j] = board.ship.ordinal();
        }
        //cruiser
        for (int i = 0; i < 3; i++){
            computerPlayerBoard[i][0] = board.ship.ordinal();
        }

    }


    // placing ships
    public BSGameState(int[][] humanPlayerBoard, int [][] computerPlayerBoard, boolean cpuHasPlaced) {
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
    }

    // starting game
    public BSGameState(int[][] humanPlayerBoard, int [][] computerPlayerBoard) {
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
            default:
                //carrier
                for (int i = 4; i < 9; i++) {
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                //submarine
                for (int i = 7; i <= 9; i++){
                    computerPlayerBoard[i][9] = board.ship.ordinal();
                }
                //destroyer
                for (int i = 5; i < 7; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //battleship
                for (int j = 4; j < 8; j++){
                    computerPlayerBoard[2][j] = board.ship.ordinal();
                }
                //cruiser
                for (int i = 0; i < 3; i++){
                    computerPlayerBoard[i][0] = board.ship.ordinal();
                }
                break;
            case 1:
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
            case 2:
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
            case 3:
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
            case 4:
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
            case 5:
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
            case 6:
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
            case 7:
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
            case 8:
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
            case 9:
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
            case 10:
                //carrier
                for(int i = 1; i < 6; i++){
                    computerPlayerBoard[3][i] = board.ship.ordinal();
                }
                //submarine
                for(int i = 6; i < 8; i++){
                    computerPlayerBoard[i][5] = board.ship.ordinal();
                }
                //destroyer
                for(int i = 0; i < 2; i++){
                    computerPlayerBoard[i][7] = board.ship.ordinal();
                }
                //battleship
                for(int i = 1; i < 5; i++){
                    computerPlayerBoard[6][i] = board.ship.ordinal();
                }
                //cruiser
                for(int i = 1; i < 4; i++){
                    computerPlayerBoard[0][i] = board.ship.ordinal();
                }
                break;




        }
        return true;
    }

    public boolean placeComputerShipsSmart(int pattern) {

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
