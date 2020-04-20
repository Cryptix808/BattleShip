package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

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
