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
import java.util.ArrayList;
import java.util.List;

public class BSHumanPlayer extends GameHumanPlayer implements Button.OnClickListener, SurfaceView.OnTouchListener, Serializable {

    private Button startButton = null;
    private Button doneButton = null;

    private final List<BoardTouchListener> listeners = new ArrayList<>();
    private GameMainActivity myActivity;
    private int playerID = getPlayerID();
    private BoardView boardView;
    private BSGameState bss;
    private tester tester;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public BSHumanPlayer(String name) {

        super(name);
    }

    //Getter for the GUI
    public View getView(){
        return boardView;
    }


    @Override
    public void onClick(View button) {
        if(button.getId() == R.id.startButton) {
            game.sendAction(new PlayBSAction(this));
            boardView.invalidate();
            return;
        }
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
        v.postInvalidate();
        return false;
    }

    @Override
    public View getTopView() {
        return boardView;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof BSGameState){


        }
        else{
            bss = new BSGameState((BSGameState) info);

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

    //Overridden to detect a board touch. When board is touched corresponding place is identified
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                int xy = locatePlace(event.getX(), event.getY());
                boardView.invalidate();
                if(xy >= 0){
                    notifyBoardTouch(xy/100, xy%100);
                }
                break;

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:

        }
        return true;
    }

    private int locatePlace(float x, float y) {
        return 0;

    }

    //Registers the given listener
    void addBoardTouchListener(BoardTouchListener listener){
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    //Unregister the listener
    public void removeBoardTouchListener(BoardTouchListener listener){
        listeners.remove(listener);
    }

    //Notify listeners
    private void notifyBoardTouch(int x, int y){
        for(BoardTouchListener listener : listeners){
            listener.onTouch(x,y);
        }
    }


    public interface BoardTouchListener{
        void onTouch(int x, int y);

    }

    public int getPlayerID(){
        return playerID;

    }




}
