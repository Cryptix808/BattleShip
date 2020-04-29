package com.example.battleship.battleship;

/**
 * Authors: Nate Kline, Grant Nelson, Miggy Sabater
 *
 * This class is responsible for recording data based on what type of ship it is
 */

public class Ship {

    protected boolean sunk, placed;
    protected int x;
    protected int y;
    protected int length;
    protected int orientation;
    public boolean selected;

    /**
     * This constructor is the ship so the game knows how
     * all of the params required for the ship
     * object
     *
     * @param length is the length of the ship
     *
     * @param x is the x coord of the ship
     *
     * @param y is the y coord of the ship
     *
     * @param orientation is the 180 degree flip of the ship
     *
     */
    public Ship(int length, int x, int y, int orientation){
        sunk = false;
        this.x = x;
        this.y = y;
        this.length = length;
        this.orientation = orientation;
        placed = false;
    }

    public Ship(Ship s) {
        sunk = s.sunk;
        this.x = s.x;
        this.y = s.y;
        this.length = s.length;
        this.orientation = s.orientation;
        placed = s.placed;
        selected = s.selected;

    }

    /**
     * This method sets the ship on the board so that
     * the correct coordinates on the board are filled
     * based on what ship was placed
     *
     *
     * @param x is the x coord of the ship
     * @param y is the y coord of the ship
     *
     * @param orientation is the 180 orientation of the ship
     *
     *
     */
    public void setShip(int x, int y, int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean isPlaced() {
        return placed;
    }

    public boolean isSunk() {
        return sunk;
    }
}
