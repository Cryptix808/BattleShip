package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

public class BSPlaceCPUShipSmart extends GameAction {
    public int pattern;

    public BSPlaceCPUShipSmart(GamePlayer player, int pattern) {
        super(player);
        this.pattern = pattern;
    }
}
