package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;
    AnimationTimer mainLoop;

    private static final double MOVE_FRAME_DURATION_SECONDS = 1 / 60;
    private static final double SPRITE_FRAME_DURATION_SECONDS = 0.25;

    private Direction lastDirection = null;

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
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000d;

                // 1 second = 100,000,000(100 million) nanoseconds
                if(elapsedSeconds < MOVE_FRAME_DURATION_SECONDS)
                    return;

                renderer.renderPositions(galaxy);

                if(elapsedSeconds < SPRITE_FRAME_DURATION_SECONDS)
                    return;

                renderer.renderGalaxy(galaxy);

                lastUpdate = now;
            }
        };

        mainLoop.start();
        renderer.onStageClosed = (closedStage) -> {
            mainLoop.stop();
        };

        keyboardListener.onKeyPressed = (direction) -> {
            lastDirection = direction;
            galaxy.getPlayer().move(15, direction);
        };

        keyboardListener.onKeyReleased = (direction) -> {
            if(lastDirection == direction)
                lastDirection = null;
        };

        galaxy.getPlayer().onMoveEnded = (player) -> {
            if(lastDirection != null)
                player.move(15, lastDirection);
        };

        galaxy.getPlayer().onDirectionChanged = (player) -> {
            renderer.updateDirection((GameObject)player);
        };

    }

    private void populateGalaxy(Galaxy galaxy) throws IOException {
        ArrayList<Coordinate> availableCoordinates = new ArrayList<Coordinate>();
        Random random = new Random();

        for (int x = 0; x < galaxy.getSettings().getHeight(); x++) {
            for (int y = 0; y < galaxy.getSettings().getWidth(); y++) {
                availableCoordinates.add(new Coordinate(x, y));
            }
        }

        Player player = GameObjectFactory.createPlayer(Direction.RIGHT);

        galaxy.setGalaxyTile(0, 0, player);
        galaxy.setPlayer(player);
        availableCoordinates.remove(0);

        //Spawn planets
        for (int x = 0 ; x < galaxy.getSettings().getPlanetCount() ; x++) {
            Planet planet = GameObjectFactory.createPlanet();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, planet);
            availableCoordinates.remove(tempPos);
        }

        //spawn Meteorites
        for (int x = 0; x < galaxy.getSettings().getMeteoriteCount() ; x++) {
            Meteorite meteorite = GameObjectFactory.createMeteorite();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, meteorite);
            availableCoordinates.remove(tempPos);
        }

        //spawn pirates
        for (int x = 0 ; x < galaxy.getSettings().getPirateCount() ; x++) {
            SpacePirate spacePirate = GameObjectFactory.createSpacePirate();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, spacePirate);
            availableCoordinates.remove(tempPos);
        }

        Wormhole wormhole = GameObjectFactory.createWormhole();
        int tempPos = random.nextInt(availableCoordinates.size());
        int tempX = availableCoordinates.get(tempPos).x;
        int tempY = availableCoordinates.get(tempPos).y;
        galaxy.setGalaxyTile(tempX, tempY, wormhole);
        availableCoordinates.remove(tempPos);

    }


    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
