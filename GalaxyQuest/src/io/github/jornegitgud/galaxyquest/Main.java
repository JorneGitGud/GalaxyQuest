package io.github.jornegitgud.galaxyquest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Galaxy Quest");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GalaxyQuestMainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 768, 768));
        stage.show();
    }

//        var mainMenu = new MainMenu(stage);
//
//        //eventually react to main menu buttons here
//        var galaxySettings = new GalaxySettings(); //will be received from mainMenu later
//        galaxySettings.setWidth(12);
//        galaxySettings.setHeight(12);
//        galaxySettings.setPlanetCount(5);
//
//        //test
//        GameManager tempGameManager = new GameManager(stage, galaxySettings);
//    }
}
