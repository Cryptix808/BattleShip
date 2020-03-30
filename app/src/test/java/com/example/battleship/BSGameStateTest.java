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
    public boolean placeShip(int length, int x, int y, int orientation) throws Exception {
        BSGameState bs =  new BSGameState();
        if (orientation == 0) {
            if (length + y < 9) {
                return false;
            }
        }
        if (orientation == 1) {
            if (length + x < 9) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            if (bs.getHumanPlayerBoard()[x][y] != BSGameState.board.water.ordinal()) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            bs.getHumanPlayerBoard()[x][y] = BSGameState.board.ship.ordinal();
        }

        return true;
    }

    @Test
    public boolean fireHumanPlayer(int x, int y){

        if(computerPlayerBoard[x][y] == BSGameState.board.water.ordinal()) {
            computerPlayerBoard[x][y] = BSGameState.board.missed.ordinal();
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

        return false;
    }

    @Test
    public boolean fireComputerPlayer(int x, int y){
        BSGameState bs =  new BSGameState();
        assertEquals(bs.getHumanPlayerBoard(), BSGameState.board.water.ordinal());
        assertEquals(bs)
        if(humanPlayerBoard[x][y] == BSGameState.board.water.ordinal()) {
            humanPlayerBoard[x][y] = BSGameState.board.missed.ordinal();
            return true;
        }
        if (humanPlayerBoard[x][y] == BSGameState.board.ship.ordinal()) {
            humanPlayerBoard[x][y] = BSGameState.board.hit.ordinal();
            computerPlayerHits++;
        }
        return false;
    }

    @Test
    public int winner() throws Exception{
        BSGameState bs =  new BSGameState();
        assertEquals(bs.getHumanPlayerHits(), 12);
        assertEquals(bs.getComputerPlayerHits(), 17);
        if(bs.getHumanPlayerHits() == 17) {
            return 1;
        }
        if(bs.getComputerPlayerHits() == 17){
            return 2;
        }
        return 0;
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
