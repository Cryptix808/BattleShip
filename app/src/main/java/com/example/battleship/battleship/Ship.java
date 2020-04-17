package com.example.battleship.battleship;

public class Ship {

    protected boolean sunk, placed;
    protected int x;
    protected int y;
    protected int length;
    protected int orientation;


    public Ship(int length, int x, int y, int orientation){
        sunk = false;
        this.x = x;
        this.y = y;
        this.length = length;
        this.orientation = orientation;
        placed = true;
    }

    public Ship(Ship s) {
        sunk = s.sunk;
        this.x = s.x;
        this.y = s.y;
        this.length = s.length;
        this.orientation = s.orientation;
        placed = s.placed;
    }

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
