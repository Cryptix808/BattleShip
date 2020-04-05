package com.example.battleship.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.battleship.R;

public class tester extends SurfaceView {
    float x, y;

    int playerBoardSize;
    int computerBoardSize;

    private Bitmap backgroundPlayer;
    private Bitmap backgroundComputer;

    BSGameState bs;
    Paint white = new Paint(Color.WHITE);
    Paint grey = new Paint(Color.GRAY);
    Paint red = new Paint(Color.RED);
    Paint black = new Paint(Color.BLACK);
    Paint blue = new Paint(Color.BLUE);

    public tester(Context context, AttributeSet attrs) {
        super(context, attrs);

        playerBoardSize = 500;
        computerBoardSize = 800;

        white.setColor(Color.WHITE);
        white.setTextSize(40);
        white.setStyle(Paint.Style.FILL);

        blue.setColor(Color.BLUE);
        grey.setColor(Color.GRAY);
        red.setColor(Color.RED);
        black.setColor(Color.BLACK);

        backgroundPlayer = BitmapFactory.decodeResource(getResources(), R.raw.bs);
        backgroundPlayer = Bitmap.createScaledBitmap(backgroundPlayer, playerBoardSize, playerBoardSize, true);

        backgroundComputer = BitmapFactory.decodeResource(getResources(), R.raw.bs);
        backgroundComputer = Bitmap.createScaledBitmap(backgroundComputer, computerBoardSize, computerBoardSize, true);

        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(" ", 0, 0, white);
        canvas.drawText("x\t: \t" + (int) x, 100,100, white);
        canvas.drawText("y\t: \t" + (int) y, 100,200, white);

        drawGrid(canvas);

        invalidate();
    }

    protected void drawGrid(Canvas c) {

        c.drawBitmap(backgroundComputer, 1000, 100, white);
        c.drawBitmap(backgroundPlayer, 100, 400, white);

        if(bs == null || bs.humanPlayerBoard == null){
            return;
        }

        int dup = 46;
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                c.drawRect(135 + (dup * i), 440 + (dup * j), 175 + (dup * i), 480 + (dup * j), blue);
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.ship.ordinal()){
                    c.drawRect(135 + (dup * i), 440 + (dup * j), 175 + (dup * i), 480 + (dup * j), grey);
                }
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.hit.ordinal()){
                    c.drawRect(145 + (dup * i), 450 + (dup * j), 165 + (dup * i), 470 + (dup * j), red);
                }
                //for debugging
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.missed.ordinal()){
                    c.drawRect(145 + (dup * i), 450 + (dup * j), 165 + (dup * i), 470 + (dup * j), white);
                }
            }
        }

        dup = 73;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                c.drawRect(1060 + (dup * i), 155 + (dup * j), 1120 + (dup * i), 217 + (dup * j), blue);
                if(bs.computerPlayerBoard[i][j] == BSGameState.board.hit.ordinal()){
                    c.drawRect(1060 + (dup * i), 155 + (dup * j), 1120 + (dup * i), 217 + (dup * j), grey);
                    c.drawRect(1060 + (dup * i), 155 + (dup * j), 1120 + (dup * i), 217 + (dup * j), red);
                }
                //for debugging
                if(bs.computerPlayerBoard[i][j] == BSGameState.board.missed.ordinal()){
                    c.drawRect(1060 + (dup * i), 155 + (dup * j), 1120 + (dup * i), 217 + (dup * j), white);
                }
            }
        }
    }

    public void getBs(BSGameState g) {
        bs = g;
    }
}
