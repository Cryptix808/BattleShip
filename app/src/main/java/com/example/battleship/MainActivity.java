package com.example.battleship;

import com.example.battleship.GameFramework.GameMainActivity;
import com.example.battleship.GameFramework.GamePlayer;
import com.example.battleship.GameFramework.gameConfiguration.GameConfig;
import com.example.battleship.GameFramework.gameConfiguration.GamePlayerType;
import com.example.battleship.battleship.BSEasyAI;
import com.example.battleship.battleship.BSHumanPlayer;
import com.example.battleship.battleship.BSLocalGame;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends GameMainActivity implements Serializable {

    private static final int PORT_NUMBER = 2587;

    @Override
    public GameConfig createDefaultConfig() {
        //Defines allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        //Two Players: human and computer
        playerTypes.add(new GamePlayerType("Human") {
            public GamePlayer createPlayer(String name) {
                return new BSHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Dumb AI") {
            public GamePlayer createPlayer(String name){
                return new BSEasyAI(name);
            }
        });

        //Creates game config class for BS
        GameConfig defaultConfig = new GameConfig(playerTypes,2,2,"Battleship", PORT_NUMBER);
            defaultConfig.addPlayer("Human", 0);
            defaultConfig.addPlayer("Computer", 1);
            defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    @Override
    public BSLocalGame createLocalGame() {
        return new BSLocalGame();
    }



}
