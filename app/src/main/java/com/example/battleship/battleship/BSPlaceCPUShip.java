package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

/**
 * Authors: Nate Kline, Grant Nelson, Miggy Sabater
 *
 * This class is responsible for getting the hard coded board for the AI to use
 */

public class BSPlaceCPUShip extends GameAction {

    public int pattern;

    public BSPlaceCPUShip(GamePlayer player, int pattern) {
        super(player);
        this.pattern = pattern;
    }
}
