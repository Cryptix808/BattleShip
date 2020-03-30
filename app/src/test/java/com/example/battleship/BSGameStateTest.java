package com.example.battleship;
import android.view.View;

import com.example.battleship.battleship.BSGameState;

import org.junit.Test;
import static org.junit.Assert.*;

public class BSGameStateTest {


    @Test
    public void cpu() throws Exception {
        BSGameState bs = new BSGameState();
        assertEquals(false, bs.getCPUhasPlaced());


    }

/*
    @Test
    //public void boolean placeShip(int length, int x, int y, int orientation) throws Exception {
    public void placeShip(int length, int x, int y, int orientation) {

        // public boolean placeShip(int length, int x, int y, int orientation) throws Exception {
        BSGameState bs = new BSGameState();
        if (orientation == 0) {
            if (length + y < 9) {

            }
        }
        if (orientation == 1) {
            if (length + x < 9) {

            }
        }
        for (int i = 0; i < length; i++) {
            if (humanPlayerBoard[x][y] != BSGameState.board.water.ordinal()) {

                if (bs.getHumanPlayerBoard()[x][y] != BSGameState.board.water.ordinal()) {
                    return false;
                }
            }
            for (int i = 0; i < length; i++) {
                bs.getHumanPlayerBoard()[x][y] = BSGameState.board.ship.ordinal();
            }


        }

    @Test
    public boolean fireHumanPlayer(int x, int y){


    public boolean fireHumanPlayer(int x, int y) throws Exception{
        BSGameState bs = new BSGameState();
        assertEquals(bs.computerPlayerBoard[x][y], BSGameState.board.water.ordinal());
        assertEquals(x,1);
        assertEquals(y,1);
        if(bs.computerPlayerBoard[x][y] == BSGameState.board.water.ordinal()) {
            bs.computerPlayerBoard[x][y] = BSGameState.board.missed.ordinal();
            return true;
        }
        if (bs.computerPlayerBoard[x][y] == BSGameState.board.ship.ordinal()) {
            bs.computerPlayerBoard[x][y] = BSGameState.board.hit.ordinal();
            bs.computerPlayerHits++;
        }


    }
*/
    @Test
    public void fireComputerPlayer() throws Exception{
        BSGameState bs =  new BSGameState();

        assertEquals(BSGameState.board.water.ordinal(), bs.humanPlayerBoard);
        //assertEquals(1, x);
        //assertEquals(1, y);
       /* if(bs.humanPlayerBoard[x][y] == BSGameState.board.water.ordinal()) {
            bs.humanPlayerBoard[x][y] = BSGameState.board.missed.ordinal();
        }
        if (bs.humanPlayerBoard[x][y] == BSGameState.board.ship.ordinal()) {
            bs.humanPlayerBoard[x][y] = BSGameState.board.hit.ordinal();
            bs.computerPlayerHits++;
        }
        */
    }



        @Test
        public void winner () throws Exception {
            BSGameState bs = new BSGameState();
            assertEquals(0, bs.humanPlayerHits);
            assertEquals(0, bs.computerPlayerHits);
            //if the game were actually played, we'd change these values to 17 so that the
            //computer would check for the winner at 17 hits, ending the game

        }

        @Test
        public void getPlayer () throws Exception {
            BSGameState bs = new BSGameState();
            bs.setPlayer(1);
            assertEquals(bs.getPlayer(), 1);
            bs.setPlayer(0);
            assertEquals(bs.getPlayer(), 0);
        }
    }



