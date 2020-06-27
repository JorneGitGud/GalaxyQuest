package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;

import java.util.ArrayList;

public class Tile {

    private Tile tileAbove;
    private Tile tileBelow;
    private Tile tileRight;
    private Tile tileLeft;

    private Coordinate coordinate;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public Tile(Coordinate coordinate) {
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

    public void addGameObject(GameObject gameObject) {
        if(!this.gameObjects.contains(gameObject))
            gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        if(this.gameObjects.contains(gameObject))
            this.gameObjects.remove(gameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public boolean contains(Class tClass) {
        for(GameObject object : gameObjects) {
            if(object.getClass().isAssignableFrom(tClass))
                return true;
        }
        return false;
    }

    public boolean containsAny(Class... tClasses) {
        for(GameObject object : gameObjects) {
            String className = object.getClass().getName();
            for(Class tClass : tClasses)
                if(className.equals(tClass.getName()))
                    return true;
        }
        return false;
    }

    public <T extends GameObject> T getFirst(Class<T> tClass) {
        for(GameObject object : gameObjects) {
            if(object.getClass().isAssignableFrom(tClass))
                //noinspection unchecked <-- type has actually been checked by comparing classes
                return (T) object;
        }
        return null;
    }

    public Coordinate getCoordinate(GalaxyRenderer gr) {
        return coordinate;
    }

    public Direction getDirectionTo(Tile otherTile) {
        var deltaX = Math.abs(otherTile.coordinate.x - this.coordinate.x);
        var deltaY = Math.abs(otherTile.coordinate.y - this.coordinate.y);

        if(deltaX >= deltaY)
            return otherTile.coordinate.x - this.coordinate.x < 0 ? Direction.LEFT : Direction.RIGHT;
        return otherTile.coordinate.y - this.coordinate.y < 0 ? Direction.UP : Direction.DOWN;
    }
}
