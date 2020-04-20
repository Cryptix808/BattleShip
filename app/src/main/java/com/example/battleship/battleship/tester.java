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
    int backgroundWaterx;
    int backgroundWatery;
    int aCSizex;
    int aCSizey;
    int bSSizey;
    int bSSizex;
    int cSizex;
    int cSizey;
    int dSizex;
    int dSizey;
    int sSizex;
    int sSizey;
    private Bitmap placeShipBoard;
    private Bitmap backgroundPlayer;
    private Bitmap backgroundComputer;
    private Bitmap waterBackground;
    private Bitmap pAC;
    private Bitmap pAC90;
    private Bitmap pBS;
    private Bitmap pBS90;
    private Bitmap pC;
    private Bitmap pC90;
    private Bitmap pD;
    private Bitmap pD90;
    private Bitmap pS;
    private Bitmap pS90;
    private Bitmap cAC;
    private Bitmap cAC90;
    private Bitmap cBS;
    private Bitmap cBS90;
    private Bitmap cC;
    private Bitmap cC90;
    private Bitmap cD;
    private Bitmap cD90;
    private Bitmap cS;
    private Bitmap cS90;
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
        backgroundWaterx = 600;
        backgroundWatery = 670;
        aCSizex= 100;
        aCSizey = 400;
        bSSizex = 88;
        bSSizey = 350;
        cSizex = 75;
        cSizey = 300;
        dSizex = 50;
        dSizey = 200;
        sSizex = 75;
        sSizey = 300;

        black.setTextSize(40);
        black.setStyle(Paint.Style.FILL);

        blue.setColor(Color.BLUE);
        grey.setColor(Color.GRAY);
        red.setColor(Color.RED);
        black.setColor(Color.BLACK);
        this.setBackgroundColor(WHITE);
        placeShipBoard = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        placeShipBoard = Bitmap.createScaledBitmap(placeShipBoard, computerBoardSize, computerBoardSize, true);
        waterBackground = BitmapFactory.decodeResource(getResources(), R.raw.ocean_resize);
        waterBackground = Bitmap.createScaledBitmap(waterBackground, backgroundWaterx, backgroundWatery, true);
        backgroundPlayer = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        backgroundPlayer = Bitmap.createScaledBitmap(backgroundPlayer, playerBoardSize, playerBoardSize, true);
        backgroundComputer = BitmapFactory.decodeResource(getResources(), R.raw.bsboard);
        backgroundComputer = Bitmap.createScaledBitmap(backgroundComputer, computerBoardSize, computerBoardSize, true);
        pAC = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier);
        pAC = Bitmap.createScaledBitmap(pAC, aCSizex, aCSizey, true);
        pAC90 = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier90);
        pAC90 = Bitmap.createScaledBitmap(pAC90, aCSizex, aCSizey, true);
        cAC = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier);
        cAC = Bitmap.createScaledBitmap(cAC, aCSizex, aCSizey, true);
        cAC90 = BitmapFactory.decodeResource(getResources(), R.raw.aircraftcarrier90);
        cAC90 = Bitmap.createScaledBitmap(cAC90, aCSizex, aCSizey, true);
        pBS = BitmapFactory.decodeResource(getResources(), R.raw.battleship);
        pBS = Bitmap.createScaledBitmap(pBS, bSSizex, bSSizey, true);
        pBS90 = BitmapFactory.decodeResource(getResources(), R.raw.battleship90);
        pBS90 = Bitmap.createScaledBitmap(pBS90, bSSizex, bSSizey, true);
        cBS = BitmapFactory.decodeResource(getResources(), R.raw.battleship);
        cBS = Bitmap.createScaledBitmap(cBS, bSSizex, bSSizey, true);
        cBS90 = BitmapFactory.decodeResource(getResources(), R.raw.battleship90);
        cBS90 = Bitmap.createScaledBitmap(cBS90, bSSizex, bSSizey, true);
        pC = BitmapFactory.decodeResource(getResources(), R.raw.cruiser);
        pC = Bitmap.createScaledBitmap(pC, cSizex, cSizey, true);
        pC90 = BitmapFactory.decodeResource(getResources(), R.raw.cruiser90);
        pC90 = Bitmap.createScaledBitmap(pC90, cSizex, cSizey, true);
        cC = BitmapFactory.decodeResource(getResources(), R.raw.cruiser);
        cC = Bitmap.createScaledBitmap(cC, cSizex, cSizey, true);
        cC90 = BitmapFactory.decodeResource(getResources(), R.raw.cruiser90);
        cC90 = Bitmap.createScaledBitmap(cC90, cSizex, cSizey, true);
        pS = BitmapFactory.decodeResource(getResources(), R.raw.submarine);
        pS = Bitmap.createScaledBitmap(pS, sSizex, sSizey, true);
        pS90 = BitmapFactory.decodeResource(getResources(), R.raw.submarine90);
        pS90 = Bitmap.createScaledBitmap(pS90, sSizex, sSizey, true);
        cS = BitmapFactory.decodeResource(getResources(), R.raw.submarine);
        cS = Bitmap.createScaledBitmap(cS, sSizex, sSizey, true);
        cS90 = BitmapFactory.decodeResource(getResources(), R.raw.submarine90);
        cS90 = Bitmap.createScaledBitmap(cS90, sSizex, sSizey, true);
        pD = BitmapFactory.decodeResource(getResources(), R.raw.destroyer);
        pD = Bitmap.createScaledBitmap(pD, dSizex, dSizey, true);
        pD90 = BitmapFactory.decodeResource(getResources(), R.raw.destroyer90);
        pD90 = Bitmap.createScaledBitmap(pD90, dSizex, dSizey, true);
        cD = BitmapFactory.decodeResource(getResources(), R.raw.destroyer);
        cD = Bitmap.createScaledBitmap(cD, dSizex, dSizey, true);
        cD90 = BitmapFactory.decodeResource(getResources(), R.raw.destroyer90);
        cD90 = Bitmap.createScaledBitmap(cD90, dSizex, dSizey, true);
        setWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(" ", 0, 0, black);
        canvas.drawText("x\t: \t" + (int) x, 100,100, black);
        canvas.drawText("y\t: \t" + (int) y, 100,200, black);
        if(bs != null && bs.inGame) {
            drawGrid(canvas);
        }
        else {
            drawPlaceShips(canvas);
        }
        invalidate();
    }

    protected void drawPlaceShips(Canvas c) {
        white.setColor(WHITE);
        c.drawBitmap(waterBackground, 180, 167, blue);
        c.drawBitmap(placeShipBoard, 100, 100, white);
        c.drawRect(200, 900, 760, 1030, red);
        c.drawRect(220, 920, 740, 1010, white);
        c.drawText("Done", 430, 980, black);
        if (bs != null) {
            if (bs.playerShips[0].selected) {
                c.drawRect(1500, 100, 1560, 435, red);
            }
            if (bs.playerShips[1].selected) {
                c.drawRect(1600, 100, 1660, 368, red);
            }
            if (bs.playerShips[2].selected) {
                c.drawRect(1700, 100, 1760, 301, red);
            }
            if (bs.playerShips[3].selected) {
                c.drawRect(1800, 100, 1860, 301, red);
            }
            if (bs.playerShips[4].selected) {
                c.drawRect(1400, 100, 1460, 234, red);
            }
        }
        c.drawBitmap(pAC, 1500, 100, grey);
        c.drawBitmap(pBS, 1600, 100, grey);
        c.drawBitmap(pC, 1700, 100, grey);
        c.drawBitmap(pS, 1800, 100, grey);
        c.drawBitmap(pD, 1400, 100, grey);
        int xSize = 60;
        int ySize = 67;
        if (bs == null) {
            return;
        }
        if(bs.playerShips[0].orientation == 0){
            pAC90 = Bitmap.createScaledBitmap(pAC90, xSize * 5, ySize, true);
        }
        else {
            pAC = Bitmap.createScaledBitmap(pAC, xSize, ySize * 5, true);
        }
        if(bs.playerShips[1].orientation == 0){
            pBS90 = Bitmap.createScaledBitmap(pBS90, xSize * 4, ySize, true);
        }
        else {
            pBS = Bitmap.createScaledBitmap(pBS, xSize, ySize * 4, true);
        }
        if(bs.playerShips[2].orientation == 0){
            pC90 = Bitmap.createScaledBitmap(pC90, xSize  * 3, ySize, true);
        }
        else {
            pC = Bitmap.createScaledBitmap(pC, xSize, ySize  * 3, true);
        }
        if(bs.playerShips[3].orientation == 0){
            pS90 = Bitmap.createScaledBitmap(pS90, xSize  * 3, ySize, true);
        }
        else {
            pS = Bitmap.createScaledBitmap(pS, xSize, ySize  * 3, true);
        }
        if(bs.playerShips[4].orientation == 0){
            pD90 = Bitmap.createScaledBitmap(pD90, xSize  * 2, ySize, true);
        }
        else {
            pD = Bitmap.createScaledBitmap(pD, xSize, ySize  * 2, true);
        }
        int dupx = 60;
        int dupy = 67;
        for(int i = 0; i < 5; i++){
            if(bs.playerShips[i].isPlaced()){
                if(i == 0){
                    if(bs.playerShips[i].orientation == 1){
                        c.drawBitmap(pAC, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                    else{
                        c.drawBitmap(pAC90, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                }
                if(i == 1){
                    if(bs.playerShips[i].orientation == 1){
                        c.drawBitmap(pBS, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                    else{
                        c.drawBitmap(pBS90, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                }
                if(i == 2){
                    if(bs.playerShips[i].orientation == 1){
                        c.drawBitmap(pC, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                    else{
                        c.drawBitmap(pC90, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                }
                if(i == 3){
                    if(bs.playerShips[i].orientation == 1){
                        c.drawBitmap(pS, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                    else{
                        c.drawBitmap(pS90, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                }
                if(i == 4){
                    if(bs.playerShips[i].orientation == 1){
                        c.drawBitmap(pD, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                    else{
                        c.drawBitmap(pD90, 180 + dupx * bs.playerShips[i].x, 167 + dupy * bs.playerShips[i].y, grey);
                    }
                }
            }
        }
        c.drawRect(1500, 900, 1800, 1030, red);
        c.drawRect(1520, 920, 1780, 1010, white);
        if(bs.orientation == 1){
            c.drawText("Vertical", 1580, 980, black);
        }
        else{
            c.drawText("Horizontal", 1580, 980, black);
        }
        c.drawText("Orientation", 1550, 880, black);

    }

    protected void drawGrid(Canvas c) {
        c.drawBitmap(waterBackground, 1088, 177, blue);
        //c.drawRect(1088, 177, 1680, 840, blue);
        waterBackground = Bitmap.createScaledBitmap(waterBackground, backgroundWaterx, backgroundWatery, true);
        c.drawRect(152, 446, 527, 861, blue);
        c.drawBitmap(backgroundComputer, 1000, 100, white);
        c.drawBitmap(backgroundPlayer, 100, 400, white);

        int dupx = 38;
        int dupy = 42;

        if(bs == null || bs.computerShips[0] == null) {
            //
        }
        else if(bs.computerShips[0].isSunk()){
            int x = 152 + (dupx * (bs.computerShips[0].getX() - 1));
            int y = 446 + (dupy * (bs.computerShips[0].getY()-1));
            if (bs.computerShips[0].getOrientation() == 0) {
                cAC.createScaledBitmap(cAC, dupx * 5, dupy, true);
            }
            else {
                cAC.createScaledBitmap(cAC, dupy * 5, dupx, true);
            }
            c.drawBitmap(cAC, dupy, dupx, grey);
        }




        if(bs == null || bs.humanPlayerBoard == null){
            return;
        }

        for (int j = 9; j > -1; j--) {
            for (int i = 9; i > -1; i--) {
                if(bs.humanPlayerBoard[j][i] == BSGameState.board.hit.ordinal()){
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
