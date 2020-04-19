package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

public class rotate extends GameAction {
    int orientation;

    public rotate(GamePlayer player, int orr) {
        super(player);
        orientation = orr;
    }
}
