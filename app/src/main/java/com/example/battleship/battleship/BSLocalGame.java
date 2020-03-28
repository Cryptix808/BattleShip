package com.example.battleship.battleship;

import com.example.battleship.GameFramework.LocalGame;

public class BSLocalGame {
    //Tag for logging
    private static final String TAG = "BSLocalGame";
    // the game's state
    //protected BSState state;

    // the marks for player 0 and player 1, respectively
    private final static char[] mark = {'X','O'};

    // the number of moves that have been played so far, used to
    // determine whether the game is over
    protected int moveCount;

    public BSLocalGame() {

        // perform superclass initialization
        super();

        // create a new, unfilled-in TTTState object
        //state = new BSState();
    }

}
