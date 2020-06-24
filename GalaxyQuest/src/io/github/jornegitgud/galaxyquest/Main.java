package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Galaxy Quest");

        var mainMenu = new MainMenu(stage);

        //eventually react to main menu buttons here
        var galaxySettings = new GalaxySettings(); //will be received from mainMenu later
        galaxySettings.setWidth(12);
        galaxySettings.setHeight(12);
        galaxySettings.setPlanetCount(5);

        //test
        GameManager tempGameManager = new GameManager(stage, galaxySettings);


    }
}
