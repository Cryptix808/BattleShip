package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

public class BSFire extends GameAction implements Serializable {


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BSFire(GamePlayer player) {
        super(player);
    }

    int x;
    int y;

    //constructor
    public void Fire(int x, int y){
        this.x = x;
        this.y = y;


    }
}
