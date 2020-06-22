package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.sprites.FileHelper;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;
    ArrayList<Coordinate> availableCoordinates = new ArrayList<Coordinate>();
    AnimationTimer mainLoop;

    public GameManager(Stage stage, GalaxySettings galaxySettings) throws IOException {
        galaxy = new Galaxy("John", galaxySettings);
        populateGalaxy(galaxy);
        highScores = new ArrayList<>();
        renderer = new GalaxyRenderer(stage, galaxySettings);
        keyboardListener = new KeyboardListener(renderer.getScene());
        renderer.renderGalaxy(galaxy);

        mainLoop = new AnimationTimer() {
            private long lastUpdate;

            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                long elapsedNanoSeconds = now - lastUpdate;

                // 1 second = 100,000,000(100 million) nanoseconds
                if(elapsedNanoSeconds / 1_000_000_000d < 0.25)
                    return;

                renderer.renderGalaxy(galaxy);

                lastUpdate = now;
            }
        };

        mainLoop.start();
        renderer.onStageClosed = (closedStage) -> {
            mainLoop.stop();
        };

    }

    private void populateGalaxy(Galaxy galaxy) throws IOException {
//        Random random;

        for (int x = 0; x < galaxy.getSettings().getHeight(); x++) {
            for (int y = 0; y < galaxy.getSettings().getWidth(); y++) {
                availableCoordinates.add(new Coordinate(x, y));
            }
        }

//        Player player = GameObjectFactory.createPlayer(Direction.RIGHT);
//
//        galaxy.setGalaxyTile(0, 0, player);
//        availableCoordinates.remove(0);

        var lastDirection = Direction.RIGHT;

        for(var y = 0; y < galaxy.getSettings().getHeight(); y++) {
            for(var x = 0; x < galaxy.getSettings().getWidth(); x++) {
                switch(lastDirection) {
                    case UP:
                        lastDirection = Direction.RIGHT;
                        break;
                    case RIGHT:
                        lastDirection = Direction.DOWN;
                        break;
                    case DOWN:
                        lastDirection = Direction.LEFT;
                        break;
                    case LEFT:
                        lastDirection = Direction.UP;
                        break;
                }
                Player testPlayer = GameObjectFactory.createPlayer(lastDirection);
                galaxy.setGalaxyTile(x, y, testPlayer);
            }
        }

//        //spawn Meteorites
//        for (int x = 0; x < galaxy.getSettings().getMeteoriteCount() ; x++) {
//            Random random = new Random();
//            SimpleSpriteList simpleSpriteList = new SimpleSpriteList();
//            int tempPos = random.nextInt(availableCoordinates.size());
//            int tempX = availableCoordinates.get(1).x;
//            int tempY = availableCoordinates.get(1).y;
//            Meteorite meteorite = new Meteorite(Direction.UP, simpleSpriteList);
//            galaxy.setGalaxyTile(tempX, tempY, meteorite);
//            availableCoordinates.remove(tempPos);
//        }
//
//        //spawn pirates
//        for (int x = 0 ; x < galaxy.getSettings().getPirateCount() ; x++) {
//            Random random = new Random();
//            DirectionalSpriteList pirateSpriteList = new DirectionalSpriteList(Direction.UP);
//            int tempPos = random.nextInt(availableCoordinates.size());
//            int tempX = availableCoordinates.get(tempPos).x;
//            int tempY = availableCoordinates.get(tempPos).y;
//            SpacePirate spacePirate = new SpacePirate(Direction.UP, pirateSpriteList);
//            galaxy.setGalaxyTile(tempX, tempY, spacePirate);
//            availableCoordinates.remove(tempPos);
//        }
//
//        //planets
//        for (int x = 0 ; x < galaxy.getSettings().getPlanetCount() ; x++) {
//            Random random = new Random();
//            SimpleSpriteList planetSpriteList = new SimpleSpriteList();
//            try {
//                planetSpriteList.addSprite(SwingFXUtils.toFXImage(FileHelper.createImage("assets/Planets/Andoria.png"), null));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            int tempPos = random.nextInt(availableCoordinates.size());
//            int tempX = availableCoordinates.get(tempPos).x;
//            int tempY = availableCoordinates.get(tempPos).y;
//            Planet planet = new Planet(planetSpriteList);
//            galaxy.setGalaxyTile(tempX, tempY, planet);
//            availableCoordinates.remove(tempPos);
//        }
    }


    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
