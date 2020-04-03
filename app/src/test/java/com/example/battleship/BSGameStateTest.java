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


    @Test
    //public void boolean placeShip(int length, int x, int y, int orientation) throws Exception {
    public void placeShip() throws Exception{

        // public boolean placeShip(int length, int x, int y, int orientation) throws Exception {
        BSGameState bs = new BSGameState();
        for(int i = 0;i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(BSGameState.board.water.ordinal(), bs.humanPlayerBoard[i][j]);
                //if the game were played, the real values would show instead of random values

            }
        }


    }

    @Test
    public void fireHumanPlayer() throws Exception{



        BSGameState bs = new BSGameState();
        for(int i = 0;i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(BSGameState.board.water.ordinal(), bs.computerPlayerBoard[i][j]);
                //if game were played, real values would show instead of random values
            }
        }


    }

    @Test
    public void fireComputerPlayer() throws Exception{
        BSGameState bs =  new BSGameState();
        for(int i = 0;i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(BSGameState.board.water.ordinal(), bs.humanPlayerBoard[i][j]);
                //if game were played, real values would be displayed instead of random ones
            }
        }

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



