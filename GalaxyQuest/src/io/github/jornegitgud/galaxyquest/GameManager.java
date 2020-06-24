package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;
    AnimationTimer mainLoop;
    Consumer<GameResult> onGameEnded = (result) -> { };

    private static final double MOVE_FRAME_DURATION_SECONDS = 1d / 60d;
    private static final double SPRITE_FRAME_DURATION_SECONDS = 1d / 4d;

    private Direction lastDirection = null;
    private int planetsVisited = 0;
    private Wormhole wormhole;

    public GameManager(Stage stage, GalaxySettings galaxySettings) throws IOException {
        galaxySettings.freezeSettings();
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
                if (elapsedSeconds < MOVE_FRAME_DURATION_SECONDS)
                    return;

                renderer.renderPositions(galaxy);

                if (elapsedSeconds < SPRITE_FRAME_DURATION_SECONDS)
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
            if (lastDirection == direction)
                lastDirection = null;
        };

        galaxy.getPlayer().onMoveEnded = (player) -> {
            if (lastDirection != null)
                player.move(15, lastDirection);
            checkCurrentTile((Player) player);
        };

        galaxy.getPlayer().onDirectionChanged = (player) -> {
            renderer.updateDirection((GameObject) player);
        };

    }


    private void checkCurrentTile(Player player) {

        GameObject currentGameObject = player.getTile().getGameObject();
        if (currentGameObject instanceof Planet && !((Planet) currentGameObject).hasBeenVisited()) {
                ((Planet) currentGameObject).setVisited();
                planetsVisited++;
                renderer.addSprite(galaxy, currentGameObject.getTile(), "assets/Planets/Planet_Visited.png");
                if(planetsVisited == galaxy.getSettings().getPlanetCount())
                    wormhole.activate();
            //move player to tile
        } else if (currentGameObject instanceof SpacePirate || currentGameObject instanceof Meteorite) {
            onGameEnded.accept(new GameResult(false, null));
            renderer.destroyScene();
            mainLoop.stop();
        } else if (currentGameObject instanceof Wormhole && ((Wormhole) currentGameObject).isActive()) {
            //elapsed Secconds!!
//            onGameEnded.accept(new GameResult(false, new HighScore(player.getName(), 10, galaxy.getSettings())));
            onGameEnded.accept(new GameResult(true, null));
            renderer.destroyScene();
            mainLoop.stop();
        } else {
            //move player to tile
        }

    }

    public void gameOver(Boolean win){
        if(!win){
            // died of life circumstances
            //close gameScene
        }else{
            //set highscore
            //show highscore
            //close gamescene
        }
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
        for (int x = 0; x < galaxy.getSettings().getPlanetCount(); x++) {
            Planet planet = GameObjectFactory.createPlanet();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, planet);
            availableCoordinates.remove(tempPos);
        }

        //spawn Meteorites
        for (int x = 0; x < galaxy.getSettings().getMeteoriteCount(); x++) {
            Meteorite meteorite = GameObjectFactory.createMeteorite();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, meteorite);
            availableCoordinates.remove(tempPos);
        }

        //spawn pirates
        for (int x = 0; x < galaxy.getSettings().getPirateCount(); x++) {
            SpacePirate spacePirate = GameObjectFactory.createSpacePirate();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.setGalaxyTile(tempX, tempY, spacePirate);
            availableCoordinates.remove(tempPos);
        }

        this.wormhole = GameObjectFactory.createWormhole();
        int tempPos = random.nextInt(availableCoordinates.size());
        int tempX = availableCoordinates.get(tempPos).x;
        int tempY = availableCoordinates.get(tempPos).y;
        galaxy.setGalaxyTile(tempX, tempY, this.wormhole);
        availableCoordinates.remove(tempPos);

    }


    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
