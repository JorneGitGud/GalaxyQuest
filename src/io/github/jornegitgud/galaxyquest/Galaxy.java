package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;

import java.util.ArrayList;
/**
 * The Galaxy class represents a galaxy in the game and contains all the tiles that make up the grid, as well as references to all game objects.
 */
public class Galaxy {

    private final GalaxySettings SETTINGS;
    private final Tile[][] GALAXY_TILES;
    private final ArrayList<GameObject> OBJECTS = new ArrayList<>();
    private Player player;

    /**
     * the constructor of the Galaxy class uses an instance of {@link GalaxySettings} to set itself up.
     * This includes the size of the grid and the amount of planets, meteorites, and pirates to be spawned.
     * @param SETTINGS the GalaxySettings
     */
    public Galaxy(GalaxySettings SETTINGS) {

        this.SETTINGS = SETTINGS;
        this.GALAXY_TILES = new Tile[SETTINGS.getHeight()][SETTINGS.getWidth()];

        //create tiles
        for (int y = 0; y < SETTINGS.getHeight(); y++) {
            for (int x = 0; x < SETTINGS.getWidth(); x++) {
                this.GALAXY_TILES[y][x] = new Tile(new Coordinate(x, y));
            }
        }

        //set tile neighbours
        for (int y = 0; y < SETTINGS.getHeight(); y++) {
            for (int x = 0; x < SETTINGS.getWidth(); x++) {

                if (y > 0) {
                    this.GALAXY_TILES[y][x].setTileAbove(this.GALAXY_TILES[y - 1][x]);
                }
                if (y < SETTINGS.getHeight() - 1) {
                    this.GALAXY_TILES[y][x].setTileBelow(this.GALAXY_TILES[y + 1][x]);
                }

                if (x < SETTINGS.getWidth() - 1) {
                    this.GALAXY_TILES[y][x].setTileRight(this.GALAXY_TILES[y][x + 1]);
                }

                if (x > 0) {
                    this.GALAXY_TILES[y][x].setTileLeft(this.GALAXY_TILES[y][x - 1]);
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
        if(yPos < this.GALAXY_TILES.length && xPos < this.GALAXY_TILES[0].length && xPos >= 0 && yPos >= 0)
            return this.GALAXY_TILES[yPos][xPos];
        return null;
    }

    /**
     * Get the settings that this galaxy uses.
     * @return returns the galaxy settings.
     */
    public GalaxySettings getSettings() {
        return SETTINGS;
    }

    /**
     * Add a game object to the list of objects on a specified coordinate.
     * @param x the x coordinate of the tile to add the game object to.
     * @param y the y coordinate of the tile to add the game object to.
     * @param gameObject the game object to add to the list of game objects on the tile.
     */
    public void addToTile(int x, int y, GameObject gameObject) {
        GALAXY_TILES[x][y].addGameObject(gameObject);
        gameObject.setTile(GALAXY_TILES[x][y]);
        if (!OBJECTS.contains(gameObject))
            OBJECTS.add(gameObject);
    }

    /**
     * Returns a list of all game objects in the galaxy.
     * @return a list of all game objects in the galaxy.
     */
    public ArrayList<GameObject> getObjects() {
        return OBJECTS;
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

