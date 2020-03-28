package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

public class PlayBSAction extends GameAction implements Serializable {
    public PlayBSAction(GamePlayer player){
        super(player);
    }
}
