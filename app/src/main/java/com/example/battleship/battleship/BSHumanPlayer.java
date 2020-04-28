package com.example.battleship.battleship;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.battleship.GameFramework.GameHumanPlayer;
import com.example.battleship.GameFramework.GameMainActivity;
import com.example.battleship.GameFramework.infoMessage.GameInfo;
import com.example.battleship.R;

import java.io.Serializable;

/**
 * Authors: Nate Kline, Grant Nelson, Miggy Sabater
 *
 * This class is responsible for whatever the human player interacts with. This can
 * be anything from the setup and battle phase. It then sends the information to get processed
 * by the local game.
 */

public class BSHumanPlayer extends GameHumanPlayer implements Button.OnClickListener, SurfaceView.OnTouchListener, Serializable {

    private Button startButton = null;
    private Button doneButton = null;
    private Button fireButton = null;

    private GameMainActivity myActivity;
    private int playerID = getPlayerID();
    private BoardView boardView;
    private BSGameState bss;
    private tester tester;
    public int orientation;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public BSHumanPlayer(String name) {
        super(name);
        orientation = 1;
    }

    //Getter for the GUI
    public View getView(){
        return boardView;
    }


    @Override
    public void onClick(View button) {
        /**
        if(button.getId() == R.id.startButton) {
            game.sendAction(new PlayBSAction(this));
            boardView.invalidate();
            return;
        }
         */
        if(button.getId() == R.id.next){
            game.sendAction(new BSFire(this, 8, 5));
            boardView.invalidate();
            return;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        tester.x = event.getX();
        tester.y = event.getY();
        if (bss.inGame) {
            //1088, 177, 1680, 840
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if (tester.x >= 1088 + (60 * i) && tester.x <= 1148 + (60 * i) &&
                            tester.y >= 177 + (67 * j) && tester.y <= 244 + (67 * j)) {
                        game.sendAction(new BSFire(this, i, j));
                    }
                }
            }
        }
        else {
            if(tester.x >= 200 && tester.x <= 760 && tester.y >= 760 && tester.y <= 1030){
                game.sendAction(new BSSwitchPhase(this));
            }
            if(tester.x >= 1500 && tester.y >= 900 && tester.x <= 1800 && tester.y <= 1030){
                game.sendAction(new rotate(this, orientation));
            }
            //carrier selector
            if(tester.x >= 1500 && tester.x <=1600 && tester.y <= 500 && tester.y >= 100){
                game.sendAction(new shipSelector(this, 0));
            }
            //battleship selector
            if(tester.x >= 1600 && tester.x <= 1688 && tester.y <= 450 && tester.y >= 100){
                game.sendAction(new shipSelector(this, 1));
            }
            //cruiser selector
            if(tester.x >= 1700 && tester.x <= 1775 && tester.y <= 400 && tester.y >= 100){
                game.sendAction(new shipSelector(this, 2));
            }
            //submarine selector
            if(tester.x >= 1800 && tester.x <= 1875 && tester.y <= 400 && tester.y >=100){
                game.sendAction(new shipSelector(this, 3));
            }
            if(tester.x >= 1400 && tester.x <= 1450 && tester.y <= 300 && tester.y >= 100){
                game.sendAction(new shipSelector(this, 4));
            }
        //1088 + (60 * i), 175 + (67 * j), 1140 + (60 * i), 234 + (67 * j)
            //234-175 = 59
            //1140-1088 = 52
            //100 + (60 * i), 100 + (67 * j), 152 + (60 * i), 159 + (67 * j)
            //180,
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if (tester.x >= 180 + (60 * i) && tester.x <= 240 + (60 * i) &&
                            tester.y >= 167 + (67 * j) && tester.y <= 234 + (67 * j)) {
                        game.sendAction(new BSPlaceShip(this, i, j));
                    }
                }
            }
        }

        v.postInvalidate();
        return false;
    }

    @Override
    public View getTopView() {
        return boardView;
    }

    //Recieves info from the local game
    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof BSGameState){
            bss = ((BSGameState) info);
            tester.getBs(bss);
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        myActivity.setContentView(R.layout.tester);

        tester = myActivity.findViewById(R.id.testing);
        tester.setOnTouchListener(this);

        //startButton = myActivity.findViewById(R.id.playGameButton);
        //doneButton = myActivity.findViewById(R.id.next);

        //startButton.setOnClickListener(this);
        //doneButton.setOnClickListener(this);
    }


    public int getPlayerID(){
        return playerID;

    }




}
