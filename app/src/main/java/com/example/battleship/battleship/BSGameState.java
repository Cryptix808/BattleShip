package com.example.battleship.battleship;

import com.example.battleship.GameFramework.infoMessage.GameState;

public class BSGameState extends GameState {
    public enum board { water, missed, hit, ship }

    private int[][] player1Board;
    private int[][] player2Board;

    int player1Hits;
    int player2Hits;

    // inital
    public BSGameState(){
        player1Board = new int[10][10];
        player2Board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player1Board[i][j] = board.water.ordinal();
                player2Board[i][j] = board.water.ordinal();
            }
        }
    }

    // placing ships
    public BSGameState(int[][] player1Board, int [][] player2Board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.player1Board[i][j] = player1Board[i][j];
                this.player2Board[i][j] = player2Board[i][j];
            }
        }
    }

    // starting game
    public BSGameState(BSGameState bs, int[][] player1Board, int [][] player2Board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.player1Board[i][j] = player1Board[i][j];
                this.player2Board[i][j] = player2Board[i][j];
            }
        }
        // initalize rest of data
        player1Hits = 0;
        player2Hits = 0;
    }

    // in game
    public BSGameState(BSGameState bs) {
        // deep copy everything

    }


    public boolean placeShip(int length, int x, int y) {
        return false;
    }
    public boolean firePlayer1(int x, int y){
        if(player2Board[x][y] == board.water.ordinal()) {
            //apply
            player1Hits++;
            return true;
        }
        return false;
    }

    public boolean firePlayer2(int x, int y){
        if(player1Board[x][y] == board.water.ordinal()) {
            //apply
            player2Hits++;
            return true;
        }
        return false;
    }

    public int winner() {
        if(player1Hits == 14) {
            return 1;
        }

    }

}
