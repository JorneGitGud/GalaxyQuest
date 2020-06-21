package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;
    ArrayList<Coordinate> availableCoordinates = new ArrayList<Coordinate>();

    public GameManager(Stage stage, GalaxySettings galaxySettings) {
        galaxy = new Galaxy("John", galaxySettings);
        populateGalaxy(galaxy);
        highScores = new ArrayList<>();
        renderer = new GalaxyRenderer(stage, galaxySettings);
        keyboardListener = new KeyboardListener(renderer.getScene());


    }

    private void populateGalaxy(Galaxy galaxy) {
//        Random random;

        for (int x = 0; x < galaxy.getSettings().getHeight(); x++) {
            for (int y = 0; y < galaxy.getSettings().getWidth(); y++) {
                availableCoordinates.add(new Coordinate(x, y));
            }
        }

        //player
        DirectionalSpriteList playerSpriteList = new DirectionalSpriteList(Direction.UP);
        // playerDirectionalSpriteList could be made in Player using Direction from constructor
        Player player = new Player("Johny", Direction.UP, playerSpriteList);

        galaxy.setGalaxyTile(0, 0, player);
        availableCoordinates.remove(0);


        //spawn Meteorites
        for (int x = 0; x < galaxy.getSettings().getMeteoriteCount() ; x++) {
            Random random = new Random();
            SimpleSpriteList simpleSpriteList = new SimpleSpriteList();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(1).x;
            int tempY = availableCoordinates.get(1).y;
            Meteorite meteorite = new Meteorite(Direction.UP, simpleSpriteList);
            galaxy.setGalaxyTile(tempX, tempY, meteorite);
            availableCoordinates.remove(tempPos);
        }

        //spawn pirates
        for (int x = 0 ; x < galaxy.getSettings().getPirateCount() ; x++) {
            Random random = new Random();
            DirectionalSpriteList pirateSpriteList = new DirectionalSpriteList(Direction.UP);
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            SpacePirate spacePirate = new SpacePirate(Direction.UP, pirateSpriteList);
            galaxy.setGalaxyTile(tempX, tempY, spacePirate);
            availableCoordinates.remove(tempPos);
        }

        //planets
        for (int x = 0 ; x < galaxy.getSettings().getPlanetCount() ; x++) {
            Random random = new Random();
            SimpleSpriteList planetSpriteList = new SimpleSpriteList();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            Planet planet = new Planet(planetSpriteList);
            galaxy.setGalaxyTile(tempX, tempY, planet);
            availableCoordinates.remove(tempPos);
        }
    }


    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
