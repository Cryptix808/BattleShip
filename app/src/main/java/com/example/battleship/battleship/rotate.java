package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

/**
 * Authors: Nate Kline, Grant Nelson, Miggy Sabater
 *
 * This class is simple but it responsible for makine sure the ship gets rotated correctly.
 */

public class rotate extends GameAction {
    int orientation;

    /**
     * This method allows the orientation of the ship
     * to be rotated properly
     *
     *
     * @param player is the player of the game
     *
     * @param orr is the orientation of the ship
     *
     */
    public rotate(GamePlayer player, int orr) {
        super(player);
        orientation = orr;
    }
}
