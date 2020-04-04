package com.example.battleship.battleship;

import android.drm.DrmStore;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.LocalGame;
import com.example.battleship.GameFramework.actionMessage.GameAction;

public class BSLocalGame extends LocalGame{

    BSGameState bs;

    public BSLocalGame(){
        bs = new BSGameState(true);
    }

    protected boolean canMove(int playerCode){ return playerCode == bs.getPlayer(); }

    @Override
    public boolean makeMove(GameAction action) {
        if (!canMove(action.getPlayer().hashCode())) {
            return false;
        }

        //get if cpu or player
        boolean isPlayer = true;

         if (action instanceof BSFire) {
            if (isPlayer) {
                return bs.fireHumanPlayer(((BSFire) action).x, ((BSFire) action).y);
            } else {
                return bs.fireComputerPlayer(((BSFire) action).x, ((BSFire) action).y);
            }
        }
        else if (action instanceof BSPlaceShip ) {

        }
            return false;
    }
        public void sendUpdatedStateTo (GamePlayer p){
            if (bs.inGame) {
                BSGameState BScpy = new BSGameState(bs);
                p.sendInfo(BScpy);
            }
            else if(bs.startGame) {
                BSGameState BScpy = new BSGameState(bs.humanPlayerBoard, bs.computerPlayerBoard);
                p.sendInfo(BScpy);
            }
            else if (bs.humanPlayerBoard != null) {
                BSGameState BScpy = new BSGameState(bs.humanPlayerBoard, bs.computerPlayerBoard, bs.cpuHasPlaced);
                p.sendInfo(BScpy);
            }
            else {
                BSGameState BScpy = new BSGameState();
                p.sendInfo(BScpy);
            }
        }

    @Override
    protected String checkIfGameOver(){
        if(bs.getWinner() == 1){
            return ("The human has won");
        }
        else if(bs.getWinner() == 2){
            return ("The computer has won");
        }
        return null;
    }

    //Tag for logging
    private static final String TAG = "BSLocalGame";
    // the game's state
    //protected BSState state;

    // the marks for player 0 and player 1, respectively
    private final static char[] mark = {'X','O'};

    // the number of moves that have been played so far, used to
    // determine whether the game is over
    protected int moveCount;
}
