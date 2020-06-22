package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;

public class Tile {

    private Tile tileAbove;
    private Tile tileBelow;
    private Tile tileRight;
    private Tile tileLeft;

    private Coordinate coordinate;
    //MovableObject objectOnTile = new MovableObject();
    private GameObject gameObject;

    public Tile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    //check if needed
    public Tile(Coordinate coordinate, GameObject gameObject) {
        this.gameObject = gameObject;
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

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Coordinate getCoordinate(GalaxyRenderer gr) {
        return coordinate;
    }
}
