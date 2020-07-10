package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;

import java.util.ArrayList;
/**
 * this class is used to create all the tiles that make up the galaxy.
 * the tile contains references to other tiles that are above, below left and right from this tile.
 * it has getters and setters.
 */

public class Tile {

    private Tile tileAbove;
    private Tile tileBelow;
    private Tile tileRight;
    private Tile tileLeft;

    private final Coordinate coordinate;
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();

    /**
     * the Tile constructor uses coordinates that help build and render the galaxy and are also used for path finding by spacePirates.
     *
     * @param coordinate {@link Coordinate}
     */
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

    /**
     * this methhod is used to set {@link GameObject} on the tile.
     *
     * @param gameObject the game object can be a {@link io.github.jornegitgud.galaxyquest.gameObjects.Meteorite}, {@link io.github.jornegitgud.galaxyquest.gameObjects.Planet}, {@link io.github.jornegitgud.galaxyquest.gameObjects.Player}, {@link io.github.jornegitgud.galaxyquest.gameObjects.SpacePirate} or a {@link io.github.jornegitgud.galaxyquest.gameObjects.Wormhole}.
     */
    public void addGameObject(GameObject gameObject) {
        if(!this.gameObjects.contains(gameObject))
            gameObjects.add(gameObject);
    }
    /**
     * this method remove a gameobject from the tile.
     *
     * @param gameObject the object that is to be removed
     */
    public void removeGameObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    /**
     * check to see if the tile contains a specific GameObject.
     *
     * @param tClass the type of object to check.
     * @return returns a boolean.
     */
    public boolean contains(Class tClass) {
        for(GameObject object : gameObjects) {
            if(object.getClass().isAssignableFrom(tClass))
                return true;
        }
        return false;
    }

    /**
     * check to see if the tile contains a specific GameObject.
     *
     * @param tClasses the type of object to check.
     * @return returns a boolean.
     */
    public boolean containsAny(Class... tClasses) {
        for(GameObject object : gameObjects) {
            String className = object.getClass().getName();
            for(Class tClass : tClasses)
                if(className.equals(tClass.getName()))
                    return true;
        }
        return false;
    }

    /**
     * this methode takes in an unspecified object and returns any instance of that object that is contained in the tile class
     *
     * @param tClass the object to check
     * @param <T> unspecified type.
     * @return returns an object if an object of "T" is found on this tile.
     */
    public <T extends GameObject> T getFirst(Class<T> tClass) {
        for(GameObject object : gameObjects) {
            if(object.getClass().isAssignableFrom(tClass))
                //noinspection unchecked <-- type has actually been checked by comparing classes
                return (T) object;
        }
        return null;
    }

    /**
     * this method returns an Coordinate object. it asks for a GalaxyRenderer as a security check. so that only a class that Class that contains this object can access this method.
     * @return returns an Coordinate object
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * returns the direction of the otherTile
     * @param otherTile the tile you are looking to get direction to
     * @return returns a {@link Direction} ENUMERATOR
     */
    public Direction getDirectionTo(Tile otherTile) {
        var deltaX = Math.abs(otherTile.coordinate.x - this.coordinate.x);
        var deltaY = Math.abs(otherTile.coordinate.y - this.coordinate.y);

        if(deltaX >= deltaY)
            return otherTile.coordinate.x - this.coordinate.x < 0 ? Direction.LEFT : Direction.RIGHT;
        return otherTile.coordinate.y - this.coordinate.y < 0 ? Direction.UP : Direction.DOWN;
    }
}
