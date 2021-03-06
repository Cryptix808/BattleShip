package com.example.battleship.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.battleship.GameFramework.infoMessage.GameInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends View implements Serializable {


    private final int boardColor = Color.argb(0,255,255,255);
    private final int redColor = Color.rgb(255,69,0);
    private final int blackColor = Color.rgb(0,0,0);
    private final int whiteColor = Color.rgb(255,255,255);
    private final Paint boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint boardLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);



    float x;
    float y;

    //Size of the board
    private int boardSize;
    {boardPaint.setColor(boardColor);}
    {redPaint.setColor(redColor);}
    {blackPaint.setColor(blackColor);}
    {whitePaint.setColor(whiteColor);}
    {int boardLineColor = Color.WHITE;
    boardLinePaint.setColor(boardLineColor);
    boardLinePaint.setStrokeWidth(3);
    }

    //Creates a new board view to be run
    public BoardView(Context context){
        super(context);
    }

    //Creates a new board view with given attributes set
    public BoardView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    //Set the board to be displayed by this view
    void setBoard(Board board){
        this.boardSize = board.size();
    }



    //Draw a 2-D representation of the board
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawText(x + " , " + y, 0,100, whitePaint);
        //drawP1Ships(canvas);
        //drawBoard(canvas);
        //drawGrid(canvas);



    }
/*
protected void receiveInfo(GameInfo info) {
    if(info instanceof BSGameState) {
            if(BSGameState.fireHumanPlayer(this,this) == true){

            }
        }
    }
*/

/*
    private void drawP1Ships(Canvas canvas){
        if(p1Coord != null){
            for(int i = 0; i < p1Coord.length; i++){
                for(int j = 0; j < p1Coord.length; j++){
                    if(p1Coord[i][j] >= 1){
                        float drawX = (i * lineGap()) + (lineGap()/2);
                        float drawY = (j * lineGap()) + (lineGap() / 2);
                        int left = (int) (drawX - (lineGap() / 2));
                        int top = (int) (drawY - (lineGap() / 2));
                        int right = (int) (drawX + (lineGap() / 2));
                        int bottom = (int) (drawY + (lineGap() / 2));
                        canvas.drawRect(left, top, right, bottom, blackPaint);

                    }
                }
            }
        }
    }

    //Keeps track of all places either AI or Human has shot in boardView
    private void drawBoard(Canvas canvas){
        if(gameCoord != null){
            for(int i = 0; i < gameCoord.length; i++){
                for(int j = 0; j < gameCoord.length; j++){
                    if (gameCoord[i][j] == 8) { // HIT
                        float xMiss = (i * lineGap()) + (lineGap() / 2);
                        float yMiss = (j * lineGap()) + (lineGap() / 2);
                        canvas.drawCircle(xMiss, yMiss, (lineGap() / 2), redPaint); // HIT
                    }
                    if (gameCoord[i][j] == -9) { // MISS
                        float xMiss = (i * lineGap()) + (lineGap() / 2);
                        float yMiss = (j * lineGap()) + (lineGap() / 2);
                        canvas.drawCircle(xMiss, yMiss, (lineGap() / 2), whitePaint);  // MISS
                    }
                }

            }

        }
    }

    //Draws horizontal and vertical lines
    private void drawGrid(Canvas canvas){
        final float maxCoord = maxCoord();
        final float placeSize = lineGap();
        canvas.drawRect(0,0, maxCoord, maxCoord, boardPaint);
        for(int i = 0; i < numLines(); i++){
            float xy = i * placeSize;
            canvas.drawLine(0, xy, maxCoord, xy, boardLinePaint);
            canvas.drawLine(xy, 0, xy, maxCoord, boardLinePaint);
        }
    }

    //Calculate the gaps between two horizontal/vertical lines
    private float lineGap(){
        return Math.min(getMeasuredWidth(), getMeasuredHeight()) / (float) boardSize;
    }

    private int locatePlace(float x, float y){
        if(x <= maxCoord() && y <= maxCoord()){
            final float placeSize = lineGap();
            int ix = (int) (x / placeSize);
            int iy = (int) (y / placeSize);
            return ix * 100 + iy;

        }
        return - 1;
    }

    //Calculate the number of horizontal/vertical lines
    private int numLines(){
        return boardSize + 1;
    }

    //Calculate the max screen coordinates
    private float maxCoord(){
        return lineGap() * (numLines() - 1);
    }

    int locateX(float x){
        if(x <= maxCoord()){
            final float placeSize = lineGap();
            return (int) (x / placeSize);
        }
        return -1;
    }

    int locateY(float y){
        return locateX(y);

    }

*/
}
