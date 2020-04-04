package com.example.battleship.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class tester extends SurfaceView {

    private final int boardColor = Color.argb(0,255,255,255);
    private final int redColor = Color.rgb(255,69,0);
    private final int blackColor = Color.rgb(0,0,0);
    private final int whiteColor = Color.rgb(255,255,255);
    private final Paint boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint boardLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    int[][] gameCoord = new int[10][10];
    private int boardSize;
    {boardPaint.setColor(boardColor);}
    {redPaint.setColor(redColor);}
    {blackPaint.setColor(blackColor);}
    {whitePaint.setColor(whiteColor);}
    {int boardLineColor = Color.WHITE;
        boardLinePaint.setColor(boardLineColor);
        boardLinePaint.setStrokeWidth(3);
    }

    float x, y;
    Paint white = new Paint(Color.WHITE);

    public tester(Context context){
        super(context);
        setWillNotDraw(false);
    }

    public tester(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    //Creates a new board view with given attributes set
    public tester(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(x + " , " + y, 0,100, whitePaint);
        white.setColor(Color.WHITE);
        white.setTextSize(40);
        white.setStyle(Paint.Style.FILL);
        canvas.drawText(" ", 0, 0, white);
        canvas.drawText("x\t: \t" + (int) x, 100,100, white);
        canvas.drawText("y\t: \t" + (int) y, 100,200, white);

        drawBoard(canvas);
        drawGrid(canvas);
        
    }

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

    private float lineGap(){
        return Math.min(getMeasuredWidth(), getMeasuredHeight()) / (float) boardSize;
    }

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
}
