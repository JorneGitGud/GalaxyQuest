package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;

import java.util.ArrayList;
/**
 * The Galaxy class represents a galaxy in the game and contains all the tiles that make up the grid, as well as references to all game objects.
 */
public class Galaxy {

    private final GalaxySettings settings;
    private final Tile[][] galaxyTiles;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;

    /**
     * the constructor of the Galaxy class uses an instance of {@link GalaxySettings} to set itself up.
     * This includes the size of the grid and the amount of planets, meteorites, and pirates to be spawned.
     * @param settings the GalaxySettings
     */
    public Galaxy(GalaxySettings settings) {

        this.settings = settings;
        this.galaxyTiles = new Tile[settings.getHeight()][settings.getWidth()];

        //create tiles
        for (int y = 0; y < settings.getHeight(); y++) {
            for (int x = 0; x < settings.getWidth(); x++) {
                this.galaxyTiles[y][x] = new Tile(new Coordinate(x, y));
            }
        }

        //set tile neighbours
        for (int y = 0; y < settings.getHeight(); y++) {
            for (int x = 0; x < settings.getWidth(); x++) {

                if (y > 0) {
                    this.galaxyTiles[y][x].setTileAbove(this.galaxyTiles[y - 1][x]);
                }
                if (y < settings.getHeight() - 1) {
                    this.galaxyTiles[y][x].setTileBelow(this.galaxyTiles[y + 1][x]);
                }

                if (x < settings.getWidth() - 1) {
                    this.galaxyTiles[y][x].setTileRight(this.galaxyTiles[y][x + 1]);
                }

                if (x > 0) {
                    this.galaxyTiles[y][x].setTileLeft(this.galaxyTiles[y][x - 1]);
                }
            }
        }
    }

    /**
     * Returns an individual tile in the galaxy.
     * @param xPos the X coordinate of the tile to get
     * @param yPos the Y coordinate of the tile to get
     * @return the tile at the specified position, or null if the position was out of bounds.
     */
    public Tile getGalaxyTile(int xPos, int yPos) {
        if(yPos < this.galaxyTiles.length && xPos < this.galaxyTiles[0].length && xPos >= 0 && yPos >= 0)
            return this.galaxyTiles[yPos][xPos];
        return null;
    }

    /**
     * Get the settings that this galaxy uses.
     * @return returns the galaxy settings.
     */
    public GalaxySettings getSettings() {
        return settings;
    }

    /**
     * Add a game object to the list of objects on a specified coordinate.
     * @param x the x coordinate of the tile to add the game object to.
     * @param y the y coordinate of the tile to add the game object to.
     * @param gameObject the game object to add to the list of game objects on the tile.
     */
    public void addToTile(int x, int y, GameObject gameObject) {
        galaxyTiles[x][y].addGameObject(gameObject);
        gameObject.setTile(galaxyTiles[x][y]);
        if (!objects.contains(gameObject))
            objects.add(gameObject);
    }

    /**
     * Returns a list of all game objects in the galaxy.
     * @return a list of all game objects in the galaxy.
     */
    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    /**
     * Tells the galaxy which object is the controllable player object.
     * @param player the object that acts as controllable character.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the controllable player object from the galaxy.
     * @return the player object.
     */
    public Player getPlayer() {
        return this.player;
    }
}

