package com.example.battleship.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class tester extends SurfaceView {
    float x, y;
    BSGameState bs;
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
        white.setColor(Color.WHITE);
        white.setTextSize(40);
        white.setStyle(Paint.Style.FILL);
        canvas.drawText(" ", 0, 0, white);
        canvas.drawText("x\t: \t" + (int) x, 100,100, white);
        canvas.drawText("y\t: \t" + (int) y, 100,200, white);

        drawGrid(canvas);
    }

    protected void drawGrid(Canvas c) {
        // draw grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                bs.humanPlayerBoard[i][j] = bs.board.water.ordinal();
                bs.computerPlayerBoard[i][j] = bs.board.water.ordinal();
            }
        }
    }

    public void getBs(BSGameState g) {
        bs = g;
    }
}
