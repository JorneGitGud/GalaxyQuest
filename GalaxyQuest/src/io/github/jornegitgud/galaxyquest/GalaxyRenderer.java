package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;
import io.github.jornegitgud.galaxyquest.gameObjects.Wormhole;
import io.github.jornegitgud.galaxyquest.sprites.FileHelper;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import io.github.jornegitgud.galaxyquest.sprites.SpriteMapParser;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * this class renders the galaxy. It renders all images in the correct places and handles the visual movement of the objects.
 */

public class GalaxyRenderer {
    private final int GALAXY_GRID_SIZE = 48;

    private final Stage stage;
    private final Scene gameScene;
    private final Pane galaxyPane;
    private final HashMap<GameObject, ImageView> sprites = new HashMap<>();
    private final ArrayList<ImageView> staticSprites = new ArrayList<>();
    public Consumer<Stage> onStageClosed;

    private SimpleSpriteList galaxySprites;
    private ImageView background;

    /**
     * The GalaxyRenderer is responsible for turning the Galaxy data model into a graphical scene for the user.
     * @param stage The stage to use for rendering galaxies onto.
     * @param settings The galaxy's settings, used to determine the width and height of the window.
     * @throws IOException throws if a sprite could not be found.
     */
    public GalaxyRenderer(Stage stage, GalaxySettings settings) throws IOException {
        this.stage = stage;
        galaxyPane = new Pane();
        this.gameScene = new Scene(galaxyPane, settings.getWidth() * GALAXY_GRID_SIZE, settings.getHeight() * GALAXY_GRID_SIZE);
        stage.setResizable(false);

        stage.setScene(this.gameScene);
        try {
            var backgroundImage = FileHelper.createImage("assets/BackgroundSprites/Galaxy.png");
            galaxySprites = new SimpleSpriteList(SpriteMapParser.parseSpriteMapToImages(backgroundImage, 768, 768));

            background = new ImageView(galaxySprites.getNextSprite());
            background.setX(0);
            background.setY(0);
            background.setFitWidth(GALAXY_GRID_SIZE * Math.max(settings.getWidth(), settings.getHeight()));
            background.setFitHeight(GALAXY_GRID_SIZE * Math.max(settings.getHeight(), settings.getWidth()));

            galaxyPane.getChildren().add(background);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest((request) -> onStageClosed.accept(stage));
    }

    /**
     * this method updates the positions of all objects that are moving in the galaxy.
     * @param galaxy is the galaxy to use for updating the positions in the scene.
     */
    public void renderPositions(Galaxy galaxy) {
        var objects = galaxy.getObjects();
        for(GameObject object : objects) {
            if(object instanceof MovableObject) {
                MovableObject movableObject = (MovableObject)object;
                if(!movableObject.isMoving())
                    continue;

                double movePercentage = movableObject.updateMove();
                ImageView sprite = sprites.get(movableObject);
                switch (movableObject.getMoveDirection()) {
                    case UP:
                        sprite.setY((movableObject.getTile().getCoordinate().y * GALAXY_GRID_SIZE) - (1 * movePercentage * GALAXY_GRID_SIZE));
                        break;
                    case RIGHT:
                        sprite.setX((movableObject.getTile().getCoordinate().x * GALAXY_GRID_SIZE) + (1 * movePercentage * GALAXY_GRID_SIZE));
                        break;
                    case LEFT:
                        sprite.setX((movableObject.getTile().getCoordinate().x * GALAXY_GRID_SIZE) - (1 * movePercentage * GALAXY_GRID_SIZE));
                        break;
                    case DOWN:
                        sprite.setY((movableObject.getTile().getCoordinate().y * GALAXY_GRID_SIZE) + (1 * movePercentage * GALAXY_GRID_SIZE));
                        break;
                }
            }
        }
    }

    /**
     * this method renders the galaxy scene (background and all game objects).
     * @param galaxy the galaxy to render onto the scene.
     */
    public void renderGalaxy(Galaxy galaxy) {
        if(!stage.isShowing())
            stage.show();

        background.setImage(galaxySprites.getNextSprite());
        background.toBack();

        var objects = galaxy.getObjects();

        for(GameObject object : objects) {
            if(!sprites.containsKey(object)) {
                var imageView = new ImageView(object.getSpriteList().getNextSprite());
                sprites.put(object, imageView);
                galaxyPane.getChildren().add(imageView);
                imageView.setX(object.getTile().getCoordinate().x * GALAXY_GRID_SIZE);
                imageView.setY(object.getTile().getCoordinate().y * GALAXY_GRID_SIZE);
                imageView.setFitWidth(GALAXY_GRID_SIZE);
                imageView.setFitHeight(GALAXY_GRID_SIZE);
                if(object instanceof Wormhole) {
                    imageView.setFitHeight(GALAXY_GRID_SIZE * 1.5);
                    imageView.setFitWidth(GALAXY_GRID_SIZE * 1.5);
                    imageView.setX(imageView.getX() - GALAXY_GRID_SIZE * 0.25);
                    imageView.setY(imageView.getY() - GALAXY_GRID_SIZE * 0.25);
                    imageView.setVisible(false);
                }
                imageView.toBack();
                continue;
            }
            var imageView = sprites.get(object);
            if(object instanceof HasDirection) {
                var direction = ((HasDirection) object).getDirection();
                imageView.setImage(object.getSpriteList().getNextSprite(direction));
                imageView.toFront();
                continue;
            } else {
                imageView.setImage(object.getSpriteList().getNextSprite());
            }

            if(object instanceof Wormhole && !imageView.isVisible() && ((Wormhole) object).isActive())
                imageView.setVisible(true);
        }

        for(var sprite : staticSprites) {
            sprite.toFront();
        }

        sprites.get(galaxy.getPlayer()).toFront();
    }

    /**
     * This method updates the sprite of an object with multiple directions. Can be used to change the sprite directly instead of waiting for the next sprite frame.
     * @param object The object whose sprite to update.
     */
    public void updateDirection(GameObject object) {
        var imageView = sprites.get(object);
        imageView.setImage(object.getSpriteList().getNextSprite(((HasDirection)object).getDirection()));
    }

    /**
     * get the current sceme
     * @return The current game scene
     */
    public Scene getScene() {
        return gameScene;
    }

    /**
     * This method destroys all sprites and the scene.
     */
    public void destroyScene() {
        var imageViews = new ArrayList<>(sprites.values());
        imageViews.addAll(staticSprites);
        imageViews.add(background);

        for(var imageView : imageViews) {
            imageView.setVisible(false);
            galaxyPane.getChildren().remove(imageView);
        }

        this.stage.setResizable(true);
        this.stage.setScene(null);
    }

    /**
     * This method adds a static sprite on top of the galaxy scene. (i.e. flags on top of planets). The player is always rendered on top of static sprites.
     * @param galaxy is the galaxy to add the sprite on
     * @param tile the tile to add the sprite on
     * @param spritePath is the string to the location of the sprite.
     */
    public void addSprite(Galaxy galaxy, Tile tile, String spritePath) {
        var coordinate = tile.getCoordinate();
        try {
            var imageView = FileHelper.createImageView(spritePath);
            staticSprites.add(imageView);
            imageView.setX(coordinate.x * GALAXY_GRID_SIZE);
            imageView.setY(coordinate.y * GALAXY_GRID_SIZE);
            imageView.setFitHeight(GALAXY_GRID_SIZE);
            imageView.setFitWidth(GALAXY_GRID_SIZE);
            galaxyPane.getChildren().add(imageView);
            imageView.toFront();
            sprites.get(galaxy.getPlayer()).toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
