package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;
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
        var galaxy = new Galaxy("Test", galaxySettings);

        var galaxyRenderer = new GalaxyRenderer(stage, galaxySettings);

        galaxyRenderer.renderGalaxy(galaxy);

        MovableObject test = new MovableObject(new SimpleSpriteList(), new Tile(new Coordinate(0, 0)));
        test.onMoveEnded = (movableObject) -> {

        }
    }
}
