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
    int x;
    int y;
    int player;
    public BSPlaceShip(GamePlayer player, int x, int y) {
        super(player);
        this.player = 1;
        this.x = x;
        this.y =y;
    }




}

