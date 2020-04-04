package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

public class BSPlaceCPUShip extends GameAction {

    public int pattern;

    public BSPlaceCPUShip(GamePlayer player, int pattern) {
        super(player);
        this.pattern = pattern;
    }
}
