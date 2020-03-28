package com.example.battleship.battleship;

import com.example.battleship.GameFramework.infoMessage.GameState;

public class BSGameState extends GameState {
    public enum board { water, missed, hit, ship }

    private int[][] humanPlayerBoard;
    private int[][] computerPlayerBoard;

    int humanPlayerHits;
    int computerPlayerHits;

    boolean cpuHasPlaced;

    int patrolBoat = 2;
    int destroyer = 3;
    int submarine = 3;
    int battleship = 4;
    int aircraftCarrier = 5;
    int horizontal = 0;
    int vertical = 1;

    // inital
    public BSGameState(){
        humanPlayerBoard = new int[10][10];
        computerPlayerBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                humanPlayerBoard[i][j] = board.water.ordinal();
                computerPlayerBoard[i][j] = board.water.ordinal();
            }
        }
        cpuHasPlaced = false;
    }

    // placing ships
    public BSGameState(int[][] humanPlayerBoard, int [][] computerPlayerBoard, boolean cpuHasPlaced) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = computerPlayerBoard[i][j];
            }
        }
        this.cpuHasPlaced = cpuHasPlaced;
    }

    // starting game
    public BSGameState(int[][] humanPlayerBoard, int [][] computerPlayerBoard) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = computerPlayerBoard[i][j];
            }
        }
        // initalize rest of data
        humanPlayerHits = 0;
        computerPlayerHits = 0;
    }

    // in game
    public BSGameState(BSGameState bs) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = bs.humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = bs.computerPlayerBoard[i][j];
            }
        }
        // initalize rest of data
        humanPlayerHits = bs.humanPlayerHits;
        computerPlayerHits = bs.computerPlayerHits;
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
        switch(pattern){
            default:
                for (int i = 4; i < 9; i++) {
                    computerPlayerBoard[i][2] = board.ship.ordinal();
                }
                for (int i = 2; i < 5; i++){
                    computerPlayerBoard[8][i] = board.ship.ordinal();
                }

        }
        return true;
    }

    public boolean placeComputerShipsSmart(int pattern) {

        return true;
    }

    public boolean firehumanPlayer(int x, int y){
        if(computerPlayerBoard[x][y] == board.water.ordinal()) {
            computerPlayerBoard[x][y] = board.missed.ordinal();
            return true;
        }
        if (computerPlayerBoard[x][y] == board.ship.ordinal()) {
            computerPlayerBoard[x][y] = board.hit.ordinal();
            humanPlayerHits++;
        }

        return false;
    }

    public boolean firecomputerPlayer(int x, int y){
        if(humanPlayerBoard[x][y] == board.water.ordinal()) {
            humanPlayerBoard[x][y] = board.missed.ordinal();
            return true;
        }
        if (humanPlayerBoard[x][y] == board.ship.ordinal()) {
            humanPlayerBoard[x][y] = board.hit.ordinal();
            computerPlayerHits++;
        }
        return false;
    }

    public int winner() {
        if(humanPlayerHits == 14) {
            return 1;
        }
        if(computerPlayerHits == 14){
            return 2;
        }
        return 0;
    }

}
