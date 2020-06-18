package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;

public class Tile {

    Tile tileAbove;
    Tile tileBelow;
    Tile tileRight;
    Tile tileLeft;

    Coordinate coordinate;
    MovableObject objectOnTile = new MovableObject();

    public Tile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Tile(Coordinate coordinate, MovableObject objectOnTil) {
        this.objectOnTile = objectOnTile;
        this.coordinate = coordinate;
    }

    public Tile getTileAbove() {
        return tileAbove;
    }

    public Tile getTileBelow() {
        return tileBelow;
    }

    public Tile getTileRight() {
        return tileRight;
    }

    public Tile getTileLeft() {
        return tileLeft;
    }

    public void setTileAbove(Tile tileAbove) {
        this.tileAbove = tileAbove;
    }

    public void setTileBelow(Tile tileBelow) {
        this.tileBelow = tileBelow;
    }

    public void setTileRight(Tile tileRight) {
        this.tileRight = tileRight;
    }

    public void setTileLeft(Tile tileLeft) {
        this.tileLeft = tileLeft;
    }



}
