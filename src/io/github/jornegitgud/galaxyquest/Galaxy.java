package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;

import java.util.ArrayList;
/**
 * this is the galaxy class. it contains the rules for setting up a galaxy. these rules are set in the {@link GalaxySettings} class.
 * the Galaxy contains all the tiles that make up the grid. these tiles are set in an Tile[][], this array is filled with default tiles,
 * specific tiles are set in populateGalaxy() in the {@link GameManager} class.
 * it has getters and setters
 */
public class Galaxy {

    private final GalaxySettings settings;
    private final Tile[][] galaxyTiles;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;

    /**
     * the constructor of the Galaxy class uses the GalaxySettings for its settings. the size of the grid and number of objects.
     * it sets the tiles and gives them their neighbours.
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

    public GalaxySettings getSettings() {
        return settings;
    }

    public void setGalaxyTile(int x, int y, GameObject gameObject) {
        galaxyTiles[x][y].addGameObject(gameObject);
        gameObject.setTile(galaxyTiles[x][y]);
        if (!objects.contains(gameObject))
            objects.add(gameObject);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}

