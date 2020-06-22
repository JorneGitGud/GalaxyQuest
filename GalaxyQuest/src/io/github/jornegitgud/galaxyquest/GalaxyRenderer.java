package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.sprites.FileHelper;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import io.github.jornegitgud.galaxyquest.sprites.SpriteMapParser;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;

public class GalaxyRenderer {
    private final int GALAXY_GRID_SIZE = 48;

    private final Stage stage;
    private final Scene gameScene;
    private final Pane galaxyPane;
    private HashMap<GameObject, ImageView> sprites = new HashMap<>();
    public Consumer<Stage> onStageClosed;

    private SimpleSpriteList galaxySprites;
    private ImageView background;

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

    public void renderPositions(Galaxy galaxy) {
        var objects = galaxy.getObjects();
        for(GameObject object : objects) {
            var imageView = sprites.get(object);
            imageView.setX(imageView.getX() + (GALAXY_GRID_SIZE / 15));
            if(imageView.getX() > galaxy.getSettings().getWidth() * GALAXY_GRID_SIZE + GALAXY_GRID_SIZE)
                imageView.setX(0 - GALAXY_GRID_SIZE);
        }
    }

    public void renderGalaxy(Galaxy galaxy) {
        if(!stage.isShowing())
            stage.show();

        background.setImage(galaxySprites.getNextSprite());

        var objects = galaxy.getObjects();

        for(GameObject object : objects) {
            if(!sprites.containsKey(object)) {
                var imageView = new ImageView(object.getSpriteList().getNextSprite());
                sprites.put(object, imageView);
                galaxyPane.getChildren().add(imageView);
                imageView.setX(object.getTile().getCoordinate(this).x * GALAXY_GRID_SIZE);
                imageView.setY(object.getTile().getCoordinate(this).y * GALAXY_GRID_SIZE);
                imageView.setFitWidth(GALAXY_GRID_SIZE);
                imageView.setFitHeight(GALAXY_GRID_SIZE);
                imageView.toFront();
                continue;
            }
            var imageView = sprites.get(object);
            if(object instanceof HasDirection) {
                var direction = ((HasDirection) object).getDirection();
                imageView.setImage(object.getSpriteList().getNextSprite(direction));
                continue;
            } else {
                imageView.setImage(object.getSpriteList().getNextSprite());
            }

        }
    }

    public Scene getScene() {
        return gameScene;
    }


}
