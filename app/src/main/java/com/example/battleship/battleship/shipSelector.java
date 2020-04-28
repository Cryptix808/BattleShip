package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

/**
 * Authors: Nate Kline, Grant Nelson, Miggy Sabater
 *
 * This class is responsible for selecting the different types of ships the player wants to us
 */

public class shipSelector extends GameAction {
    int shipSelected;
    public shipSelector(GamePlayer player, int selectedShip) {
        super(player);
        shipSelected = selectedShip;
    }

    public int getShipID() {
        return shipSelected;
    }
}
