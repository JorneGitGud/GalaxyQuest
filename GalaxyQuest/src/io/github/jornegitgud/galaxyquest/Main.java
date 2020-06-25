package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private Scene mainMenuScene;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Galaxy Quest");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("GalaxyQuestMainMenu.fxml"));

        stage.setTitle("Main Menu");
        this.mainMenuScene = new Scene(fxmlLoader.load(), 768, 768);
        stage.setScene(mainMenuScene);
        stage.show();

        var controller = (MainMenuController) fxmlLoader.getController();

        controller.onStartButtonClicked = (event) -> {
            //eventually react to main menu buttons here
            var galaxySettings = new GalaxySettings(); //will be received from mainMenu later

            //test
            try {
                GameManager gameManager = new GameManager(stage, galaxySettings);
                gameManager.onGameEnded = (gameResult) -> {
                    stage.setScene(this.mainMenuScene);
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        controller.onExitButtonClicked = (event) -> {
            stage.close();
        };

    }
}
