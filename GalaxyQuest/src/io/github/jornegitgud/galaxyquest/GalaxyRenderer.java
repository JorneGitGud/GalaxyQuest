package io.github.jornegitgud.galaxyquest;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.FileNotFoundException;

public class GalaxyRenderer {
    private final int GALAXY_GRID_SIZE = 48;

    private final Stage stage;
    private final Scene gameScene;
    private final Pane galaxyPane;

    public GalaxyRenderer(Stage stage, GalaxySettings settings) {
        this.stage = stage;
        galaxyPane = new Pane();
        this.gameScene = new Scene(galaxyPane, settings.getWidth() * GALAXY_GRID_SIZE, settings.getHeight() * GALAXY_GRID_SIZE);

        stage.setScene(this.gameScene);
        try {
            Image galaxyBackground = FileHelper.createImage("assets/BackgroundSprites/Galaxy.png");
            BackgroundImage galaxyBackgroundImage = new BackgroundImage(galaxyBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 0, false), BackgroundSize.DEFAULT);
            galaxyPane.setBackground(new Background(galaxyBackgroundImage));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void renderGalaxy(Galaxy galaxy) throws FileNotFoundException {
        if(!stage.isShowing())
            stage.show();

        ImageView playerSample = FileHelper.createImageView("assets/MovableObjects/Player_Left.png");
        playerSample.setX(0);
        playerSample.setY(0);
        playerSample.setFitWidth(GALAXY_GRID_SIZE * 4);
        playerSample.setFitHeight(GALAXY_GRID_SIZE);
        galaxyPane.getChildren().add(playerSample);

        ImageView kai = FileHelper.createImageView("assets/Planets/Kai.png");
        kai.setX(GALAXY_GRID_SIZE);
        kai.setY(GALAXY_GRID_SIZE);
        kai.setFitWidth(GALAXY_GRID_SIZE);
        kai.setFitHeight(GALAXY_GRID_SIZE);
        galaxyPane.getChildren().add(kai);

        kai.toBack();
        playerSample.toFront();
    }

    public Scene getScene() {
        return gameScene;
    }


}
