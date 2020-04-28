package com.example.battleship.battleship;

import android.drm.DrmStore;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.LocalGame;
import com.example.battleship.GameFramework.actionMessage.GameAction;

public class BSLocalGame extends LocalGame{

    BSGameState bs;

    public BSLocalGame(){
        bs = new BSGameState();
    }

    protected boolean canMove(int playerCode){ return playerCode == bs.getTurnCode(); }

    @Override
    public boolean makeMove(GameAction action) {

         if (action instanceof BSFire) {
            if (bs.getTurnCode() == 0) {
                return bs.fireHumanPlayer(((BSFire) action).x, ((BSFire) action).y);
            } else {
                return bs.fireComputerPlayer(((BSFire) action).x, ((BSFire) action).y);
            }
        }
        else if (action instanceof BSPlaceShip ) {
            return bs.placeShip(((BSPlaceShip) action).player, ((BSPlaceShip) action).x, ((BSPlaceShip) action).y);
        }

        else if (action instanceof BSSwitchPhase) {
            return bs.switchPhase();
        }
        else if (action instanceof rotate) {
            bs.switchOr();
            return true;
         }
        else if (action instanceof shipSelector){
            return bs.select(((shipSelector) action).getShipID());
         }
            return false;
    }

    public void sendUpdatedStateTo (GamePlayer p){
            if (bs == null) {
                return;
            }
            if (bs.inGame) {
                BSGameState BScpy = new BSGameState(bs);
                p.sendInfo(BScpy);
            }
            else if(bs.startGame) {
                BSGameState BScpy = new BSGameState(bs.humanPlayerBoard, bs.computerPlayerBoard, bs);
                p.sendInfo(BScpy);
            }
            else if (bs.humanPlayerBoard != null) {
                BSGameState BScpy = new BSGameState(bs, bs.humanPlayerBoard, bs.computerPlayerBoard, bs.cpuHasPlaced);
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
            return ("The human has won. ");
        }
        else if(bs.getWinner() == 2){
            return ("The computer has won. ");
        }
        return null;
    }

    private static final String TAG = "BSLocalGame";
}
