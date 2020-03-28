package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

public class BSPlaceShip extends GameAction implements Serializable {


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BSPlaceShip(GamePlayer player) {
        super(player);
    }

    int x;
    int y;
    int length;
    int orientation;
    //constructor
    public void PlaceShip(int length, int x, int y, int orientation){
        this.x = x;
        this.y = y;
        this.length = length;
        this.orientation = orientation;
    }
}

