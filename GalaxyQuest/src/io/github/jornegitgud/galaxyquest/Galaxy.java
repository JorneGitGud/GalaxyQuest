package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;

import java.util.ArrayList;

public class Galaxy {

    private GalaxySettings settings;
    private KeyboardListener keyboardListener;
    private Tile[][] galaxyTiles;
    private ArrayList<GameObject> objects = new ArrayList<>();

    private String name;


    public Galaxy(String name, GalaxySettings settings) {
        this.name = name;
        this.settings = settings;
        this.settings.freezeSettings();
        this.galaxyTiles = new Tile[settings.getHeight()][settings.getWidth()];

        //create tiles
        for (int y = 0; y < settings.getHeight(); y++) {
            for (int x = 0; x < settings.getWidth(); x++) {
                this.galaxyTiles[y][x] = new Tile(new Coordinate(y, x));
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

    //Asking for GameManager to ensure ol
    public Tile getGalaxyTile(GameManager gameManager, int xPos, int yPos) {
        if (yPos < this.galaxyTiles.length && xPos < this.galaxyTiles[0].length && xPos >= 0 && yPos >= 0){
            return this.galaxyTiles[yPos][xPos];
        }
        return null;
    }

    public Tile getGalaxyTile(GalaxyRenderer galaxyRenderer, int xPos, int yPos) {
        if (yPos < this.galaxyTiles.length && xPos < this.galaxyTiles[0].length && xPos >= 0 && yPos >= 0){
            return this.galaxyTiles[yPos][xPos];
        }
        return null;
    }

    public GalaxySettings getSettings() {
        return settings;
    }

    public String getName() {
        return name;
    }

    public void setGalaxyTile(int x, int y, GameObject gameObject){
        galaxyTiles[x][y].setGameObject(gameObject);
        gameObject.setTile(galaxyTiles[x][y]);
        if(!objects.contains(gameObject))
            objects.add(gameObject);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
