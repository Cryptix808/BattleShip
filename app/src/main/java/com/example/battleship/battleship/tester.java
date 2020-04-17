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

import static android.graphics.Color.WHITE;

public class tester extends SurfaceView {
    float x, y;

    int playerBoardSize;
    int computerBoardSize;
    int aCSize;
    int bSSize;
    int cSize;
    int dSize;
    int sSize;
    private Bitmap placeShipBoard;
    private Bitmap backgroundPlayer;
    private Bitmap backgroundComputer;
    private Bitmap pAC;
    private Bitmap pBS;
    private Bitmap pC;
    private Bitmap pD;
    private Bitmap pS;
    private Bitmap cAC;
    private Bitmap cBS;
    private Bitmap cC;
    private Bitmap cD;
    private Bitmap cS;

    BSGameState bs;
    Paint white = new Paint(WHITE);
    Paint grey = new Paint(Color.GRAY);
    Paint red = new Paint(Color.RED);
    Paint black = new Paint(Color.BLACK);
    Paint blue = new Paint(Color.BLUE);

    public tester(Context context, AttributeSet attrs) {
        super(context, attrs);

        playerBoardSize = 500;
        computerBoardSize = 800;
        aCSize = 50;
        bSSize = 40;
        cSize = 30;
        dSize = 20;
        sSize = 30;

        white.setColor(WHITE);
        white.setTextSize(40);
        white.setStyle(Paint.Style.FILL);

        blue.setColor(Color.BLUE);
        grey.setColor(Color.GRAY);
        red.setColor(Color.RED);
        black.setColor(Color.BLACK);
        this.setBackgroundColor(WHITE);
        placeShipBoard = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        placeShipBoard = Bitmap.createScaledBitmap(placeShipBoard, computerBoardSize, computerBoardSize, true);
        backgroundPlayer = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        backgroundPlayer = Bitmap.createScaledBitmap(backgroundPlayer, playerBoardSize, playerBoardSize, true);
        backgroundComputer = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        backgroundComputer = Bitmap.createScaledBitmap(backgroundComputer, computerBoardSize, computerBoardSize, true);
        pAC = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier);
        pAC = Bitmap.createScaledBitmap(pAC, aCSize, aCSize, true);
        cAC = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier);
        cAC = Bitmap.createScaledBitmap(cAC, aCSize, aCSize, true);
        pBS = BitmapFactory.decodeResource(getResources(), R.raw.battleship);
        pBS = Bitmap.createScaledBitmap(pBS, bSSize, bSSize, true);
        cBS = BitmapFactory.decodeResource(getResources(), R.raw.battleship);
        cBS = Bitmap.createScaledBitmap(cBS, bSSize, bSSize, true);
        pC = BitmapFactory.decodeResource(getResources(), R.raw.cruiser);
        pC = Bitmap.createScaledBitmap(pC, cSize, cSize, true);
        cC = BitmapFactory.decodeResource(getResources(), R.raw.cruiser);
        cC = Bitmap.createScaledBitmap(cC, cSize, cSize, true);
        pS = BitmapFactory.decodeResource(getResources(), R.raw.submarine);
        pS = Bitmap.createScaledBitmap(pS, sSize, sSize, true);
        cS = BitmapFactory.decodeResource(getResources(), R.raw.submarine);
        cS = Bitmap.createScaledBitmap(cS, sSize, sSize, true);
        pD = BitmapFactory.decodeResource(getResources(), R.raw.destroyer);
        pD = Bitmap.createScaledBitmap(pD, dSize, dSize, true);
        cD = BitmapFactory.decodeResource(getResources(), R.raw.destroyer);
        cD = Bitmap.createScaledBitmap(cD, dSize, dSize, true);
        setWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(" ", 0, 0, black);
        canvas.drawText("x\t: \t" + (int) x, 100,100, black);
        canvas.drawText("y\t: \t" + (int) y, 100,200, black);

        drawPlaceShips(canvas);
        //drawGrid(canvas);

        invalidate();
    }

    protected void drawPlaceShips(Canvas c) {
        c.drawBitmap(placeShipBoard, 100, 100, white);
    }

    protected void drawGrid(Canvas c) {

        c.drawRect(1088, 177, 1680, 840, blue);
        c.drawRect(152, 446, 527, 861, blue);
        c.drawBitmap(backgroundComputer, 1000, 100, white);
        c.drawBitmap(backgroundPlayer, 100, 400, white);

        int dupx = 38;
        int dupy = 42;

        if(bs == null || bs.computerAircraftCarrier == null) {
            //
        }
        else if(!bs.computerAircraftCarrier.isSunk()){
            int x = 152 + (dupx * (bs.computerAircraftCarrier.getX() - 1));
            int y = 446 + (dupy * (bs.computerAircraftCarrier.getY()-1));
            if (bs.computerAircraftCarrier.getOrientation() == 0) {
                cAC.createScaledBitmap(cAC, dupx * 5, dupy, true);
            }
            else {
                //scale
            }
            //draw
        }


        if(bs == null || bs.humanPlayerBoard == null){
            return;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.ship.ordinal()){
                    c.drawRect(152 + (dupx * i), 446 + (dupy * j), 188 + (dupx * i), 483 + (dupy * j), grey);
                }
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.hit.ordinal()){
                    c.drawRect(152 + (dupx * i), 446 + (dupy * j), 188 + (dupx * i), 483 + (dupy * j), red);
                }
                //for debugging
                if(bs.humanPlayerBoard[i][j] == BSGameState.board.missed.ordinal()){
                    c.drawRect(152 + (dupx* i), 446 + (dupy * j), 188 + (dupx * i), 483 + (dupy * j), white);
                }
            }
        }

        dupx = 60;
        dupy = 67;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(bs.computerPlayerBoard[i][j] == BSGameState.board.hit.ordinal()){
                    c.drawRect(1088 + (dupx * i), 175 + (dupy * j), 1140 + (dupx * i), 234 + (dupy * j), grey);
                    c.drawRect(1088 + (dupx * i), 175 + (dupy * j), 1140 + (dupx * i), 234 + (dupy * j), red);
                }
                //for debugging
                if(bs.computerPlayerBoard[i][j] == BSGameState.board.missed.ordinal()){
                    c.drawRect(1088 + (dupx * i), 175 + (dupy * j), 1140 + (dupx * i), 234 + (dupy * j), white);
                }
            }
        }

    }

    public void getBs(BSGameState g) {
        bs = g;
    }
}
