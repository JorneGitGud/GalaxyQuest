package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * the GameManager
 */
public class GameManager {

    private final GalaxyRenderer renderer;
    private final Galaxy galaxy;
    private final AnimationTimer mainLoop;
    public Consumer<GameResult> onGameEnded = (result) -> { };

    private final long startTime;

    private static final double MOVE_FRAME_DURATION_SECONDS = 1d / 60d;
    private static final double SPRITE_FRAME_DURATION_SECONDS = 1d / 4d;

    private Direction lastDirection = null;
    private int planetsVisited = 0;
    private Wormhole wormhole;



    public GameManager(GalaxySettings galaxySettings, GalaxyRenderer galaxyRenderer) throws IOException {

        startTime = System.currentTimeMillis();
        galaxySettings.freezeSettings();
        galaxy = new Galaxy(galaxySettings);
        this.renderer = galaxyRenderer;

        populateGalaxy(galaxy);
        KeyboardListener keyboardListener = new KeyboardListener(renderer.getScene());
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

        renderer.onStageClosed = (closedStage) -> mainLoop.stop();

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

        galaxy.getPlayer().onDirectionChanged = renderer::updateDirection;

        mainLoop.start();

        for (var object : galaxy.getObjects()) {
            if (object instanceof Meteorite) {
                ((Meteorite) object).onMoveEnded = (meteorite) -> {
                    checkCurrentTileMovableObject(meteorite);
                    meteorite.move(15, Direction.randomDirection());
                };
                var meteorite = (Meteorite) object;
                meteorite.move(15, Direction.randomDirection());
            } else if (object instanceof SpacePirate) {
                var spacePirate = (SpacePirate) object;
                spacePirate.onMoveEnded = (pirate) -> {
                    checkCurrentTileMovableObject(pirate);
                    pirate.move(20, pirate.getTile().getDirectionTo(galaxy.getPlayer().getTile()));
                };
                spacePirate.onDirectionChanged = renderer::updateDirection;
                spacePirate.move(20, spacePirate.getTile().getDirectionTo(galaxy.getPlayer().getTile()));
            }
        }

    }

    /**
     * this method check if the given GameObject is a Player object, if so calls the checkCurrentTilePlayer method.
     * if not calls the gameOver function.
     * @param object the Object to be checked
     */
    private void checkCurrentTileMovableObject(GameObject object) {
        if (object instanceof Player)
            checkCurrentTilePlayer((Player) object);

        if (object.getTile().contains(Player.class))
            gameOver(false);
    }

    /**
     * checks if the tile where the player is at currently contains another object.
     * if the other object is a planet sets it to visited.
     * if meteorite or spacePirate calls the gameOver method with boolean false
     * if wormhole calls the gameOver method with boolean true
     * @param player the spaceship in the galaxy
     */
    private void checkCurrentTilePlayer(Player player) {
        if (player.getTile().contains(Planet.class)) {
            var planet = player.getTile().getFirst(Planet.class);
            if (planet.hasBeenVisited())
                return;
            planet.setVisited();
            planetsVisited++;
            renderer.addSprite(galaxy, planet.getTile(), "assets/Planets/Planet_Visited.png");
            if (planetsVisited == galaxy.getSettings().getPlanetCount())
                wormhole.activate();

        } else if (player.getTile().containsAny(Meteorite.class, SpacePirate.class)) {
            gameOver(false);
        } else if (player.getTile().contains(Wormhole.class)) {
            var wormhole = player.getTile().getFirst(Wormhole.class);
            if (wormhole.isActive())
                gameOver(true);
        }
    }

    /**
     * this method is called when the Game is over. it has different functions for a Winning Case of Losing Case.
     * if Win = true it sets a {@link HighScore }
     * if Win = false return immediately to the Menu
     * @param win this tells the method if a player has won or lost
     */
    public void gameOver(Boolean win) {
        final HighScore highScore = new HighScore((int) (System.currentTimeMillis() - startTime) / 1000, galaxy.getSettings());

        renderer.destroyScene();
        mainLoop.stop();
        Platform.runLater(() -> onGameEnded.accept(new GameResult(win, highScore)));
    }

    /**
     * this method populates the Galaxy, before calling this method the galaxy contains only empty tiles.
     * this method creates an arrayList filled with all possible coordinates for this grid.
     * then it gives each object {@link Planet},{@link Player}{@link Meteorite}{@link Wormhole} a {@link Coordinate} object and removes it from the arrayList.
     * And gives this object to the Galaxy class, which sets it in the right Tile
     * @param galaxy it uses a Galaxy object to check the number of coordinates it should create.
     * @throws IOException if an input or output operation is failed
     */
    protected void populateGalaxy(Galaxy galaxy) throws IOException {
        ArrayList<Coordinate> availableCoordinates = new ArrayList<>();
        Random random = new Random();

        for (int x = 0; x < galaxy.getSettings().getHeight(); x++) {
            for (int y = 0; y < galaxy.getSettings().getWidth(); y++) {
                availableCoordinates.add(new Coordinate(x, y));
            }
        }

        Player player = GameObjectFactory.createPlayer(Direction.RIGHT);

        galaxy.addToTile(0, 0, player);
        galaxy.setPlayer(player);
        availableCoordinates.remove(0);

        //Spawn planets
        for (int x = 0; x < galaxy.getSettings().getPlanetCount(); x++) {
            Planet planet = GameObjectFactory.createPlanet();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.addToTile(tempX, tempY, planet);
            availableCoordinates.remove(tempPos);
        }

        //spawn Meteorites
        for (int x = 0; x < galaxy.getSettings().getMeteoriteCount(); x++) {
            Meteorite meteorite = GameObjectFactory.createMeteorite();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.addToTile(tempX, tempY, meteorite);
            availableCoordinates.remove(tempPos);
        }

        //spawn pirates
        for (int x = 0; x < galaxy.getSettings().getPirateCount(); x++) {
            SpacePirate spacePirate = GameObjectFactory.createSpacePirate();
            int tempPos = random.nextInt(availableCoordinates.size());
            int tempX = availableCoordinates.get(tempPos).x;
            int tempY = availableCoordinates.get(tempPos).y;
            galaxy.addToTile(tempX, tempY, spacePirate);
            availableCoordinates.remove(tempPos);
        }

        this.wormhole = GameObjectFactory.createWormhole();
        int tempPos = random.nextInt(availableCoordinates.size());
        int tempX = availableCoordinates.get(tempPos).x;
        int tempY = availableCoordinates.get(tempPos).y;
        galaxy.addToTile(tempX, tempY, this.wormhole);
        availableCoordinates.remove(tempPos);

    }

    /**
     * Returns the Game Manager's instance of the Galaxy class. Used as an entry point by unit tests.
     * @return the Galaxy instance that this Game Manager holds.
     */
    public Galaxy getGalaxy() {
        return this.galaxy;
    }
}



