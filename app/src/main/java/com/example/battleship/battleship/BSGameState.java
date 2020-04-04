package com.example.battleship.battleship;

import com.example.battleship.GameFramework.infoMessage.GameState;

public class BSGameState extends GameState {
    public enum board { water, missed, hit, ship }

    public int[][] humanPlayerBoard;
    public int[][] computerPlayerBoard;
    private int player;

    public int humanPlayerHits;
    public int computerPlayerHits;
    boolean cpuHasPlaced;
    boolean startGame;
    boolean inGame;
    // inital

    public int getHumanPlayerHits() {
        return humanPlayerHits;
    }
    public boolean getCPUhasPlaced(){ return cpuHasPlaced;  }
    public int getComputerPlayerHits() {
        return computerPlayerHits;
    }
    public int getPlayer(){return player;}
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
        humanPlayerHits = 0;
        computerPlayerHits = 0;
        cpuHasPlaced = false;
        startGame = false;
        inGame = false;
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.humanPlayerBoard[i][j] = bs.humanPlayerBoard[i][j];
                this.computerPlayerBoard[i][j] = bs.computerPlayerBoard[i][j];
            }
        }
        // initalize rest of data
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

    public boolean fireHumanPlayer(int x, int y){
        if(computerPlayerBoard[x][y] == board.water.ordinal()) {
            computerPlayerBoard[x][y] = board.missed.ordinal();
            //draw a white dot on that spot
            return true;
        }
        if (computerPlayerBoard[x][y] == board.ship.ordinal()) {
            computerPlayerBoard[x][y] = board.hit.ordinal();
            //draw
            humanPlayerHits++;
        }

        return false;
    }

    public boolean fireComputerPlayer(int x, int y){
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
