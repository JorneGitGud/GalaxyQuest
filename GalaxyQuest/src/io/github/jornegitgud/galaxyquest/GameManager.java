package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

public class GameManager {
    ArrayList<HighScore> highScores = new ArrayList<>();
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;
    AnimationTimer mainLoop;
    Consumer<GameResult> onGameEnded = (result) -> { };

    private long startTime;

    private static final double MOVE_FRAME_DURATION_SECONDS = 1d / 60d;
    private static final double SPRITE_FRAME_DURATION_SECONDS = 1d / 4d;

    private Direction lastDirection = null;
    private int planetsVisited = 0;
    private Wormhole wormhole;
    private boolean playerOnPlanet = false;

    @SuppressWarnings("UnnecessaryContinue")
    public GameManager(Stage stage, GalaxySettings galaxySettings) throws IOException {
        startTime = System.currentTimeMillis();
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
            checkCurrentTilePlayer((Player) player);
        };

        galaxy.getPlayer().onDirectionChanged = (player) -> {
            renderer.updateDirection((GameObject) player);
        };

        for(var object : galaxy.getObjects()) {
            if(object instanceof Meteorite) {
                ((Meteorite) object).onMoveEnded = (meteorite) -> {
                    checkCurrentTileMoveableObject(meteorite);
                    while(!meteorite.move(15, Direction.randomDirection()));
                };
                var meteorite = (Meteorite) object;
                while(!meteorite.move(15, Direction.randomDirection())) {continue;};
            } else if (object instanceof SpacePirate) {
                ((SpacePirate) object).onMoveEnded = (spacePirate) -> {
                    checkCurrentTileMoveableObject(spacePirate);
                    if(!playerOnPlanet) {
                        while (!spacePirate.move(20, spacePirate.getTile().getDirectionTo(galaxy.getPlayer().getTile()))) {continue;}
                    }
                    else //try once but don't bother retrying as player is on planet
                        spacePirate.move(20, spacePirate.getTile().getDirectionTo(galaxy.getPlayer().getTile()));
                };
                var spacePirate = (SpacePirate) object;
                spacePirate.move(20, spacePirate.getTile().getDirectionTo(galaxy.getPlayer().getTile()));
            }
        }

    }

    private void checkCurrentTileMoveableObject(GameObject object) {
        if(object instanceof Player)
            checkCurrentTilePlayer((Player) object);

        if(object.getTile().contains(Player.class))
            gameOver(false);
    }


    private void checkCurrentTilePlayer(Player player) {
        if(playerOnPlanet && !player.getTile().contains(Planet.class)) {
            playerOnPlanet = false;
            for(GameObject gameObject : galaxy.getObjects())
                if(gameObject instanceof SpacePirate && !((SpacePirate) gameObject).isMoving()) {
                    var spacePirate = (SpacePirate)gameObject;
                    spacePirate.move(20, spacePirate.getTile().getDirectionTo(player.getTile()));
                }
        }

        if(player.getTile().contains(Planet.class)) {
            this.playerOnPlanet = true;
            var planet = player.getTile().getFirst(Planet.class);
            if(planet.hasBeenVisited())
                return;
            planet.setVisited();
            planetsVisited++;
            renderer.addSprite(galaxy, planet.getTile(), "assets/Planets/Planet_Visited.png");
            if (planetsVisited == galaxy.getSettings().getPlanetCount())
                wormhole.activate();
            //move player to tile
        } else if (player.getTile().containsAny(Meteorite.class, SpacePirate.class)) {
            gameOver(false);
        } else if (player.getTile().contains(Wormhole.class)) {
            var wormhole = player.getTile().getFirst(Wormhole.class);
            if(wormhole.isActive())
                gameOver(true);
        }
    }

    // even checken hoe we de player naam op halen en setten in de game
    public void gameOver(Boolean win){
        HighScore highScore = null;

        if(win) {
            highScore = new HighScore((int)( System.currentTimeMillis() - startTime) / 1000, galaxy.getSettings());
            highScores.add(highScore);
            System.out.println(galaxy.getPlayer().getName()+ " : " + highScore.score);
            //close gamescene
        }

        renderer.destroyScene();
        mainLoop.stop();
        onGameEnded.accept(new GameResult(win, highScore));
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