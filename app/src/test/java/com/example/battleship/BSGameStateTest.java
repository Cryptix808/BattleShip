package com.example.battleship;
import android.view.View;

import com.example.battleship.battleship.BSGameState;

import org.junit.Test;
import static org.junit.Assert.*;

public class BSGameStateTest {


    @Test
    public boolean cpu() throws Exception{
        BSGameState bs = new BSGameState();
        if(bs.getCPUhasPlaced() == false){
            return false;
        }
        if(bs.getCPUhasPlaced() == true){
            return true;
        }
        return true;
    }

    @Test
    public void placeShip(int length, int x, int y, int orientation) {

    public boolean placeShip(int length, int x, int y, int orientation) throws Exception {
        BSGameState bs =  new BSGameState();
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

    @Test
    public void fireComputerPlayer(int x, int y) throws Exception{
        BSGameState bs =  new BSGameState();
        assertEquals(bs.humanPlayerBoard[x][y], BSGameState.board.water.ordinal());
        assertEquals(x, 1);
        assertEquals(y, 1);
        if(bs.humanPlayerBoard[x][y] == BSGameState.board.water.ordinal()) {
            bs.humanPlayerBoard[x][y] = BSGameState.board.missed.ordinal();
        }
        if (bs.humanPlayerBoard[x][y] == BSGameState.board.ship.ordinal()) {
            bs.humanPlayerBoard[x][y] = BSGameState.board.hit.ordinal();
            bs.computerPlayerHits++;
        }
        return false;
    }

    @Test
    public void winner() throws Exception{
        BSGameState bs =  new BSGameState();
        assertEquals(bs.humanPlayerHits, 12);
        assertEquals(bs.computerPlayerHits, 17);
        if(bs.humanPlayerHits == 17) {

        }
        if(bs.computerPlayerHits == 17){

        }

    }

    @Test
    public void getPlayer() throws Exception {
        BSGameState bs = new BSGameState();
        bs.setPlayer(1);
        assertEquals(bs.getPlayer(), 1);
        bs.setPlayer(0);
        assertEquals(bs.getPlayer(), 0);
    }
}
