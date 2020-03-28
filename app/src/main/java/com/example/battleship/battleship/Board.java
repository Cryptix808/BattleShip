package com.example.battleship.battleship;

/*
    A game board where it ships can be placed. A place of the
    board is denoted by a pare of 0-based indices
 */

public class Board {

    private final int size;

    Board(int size, String player){
        this.size = size;

    }

    int size(){
        return size;
    }

}
