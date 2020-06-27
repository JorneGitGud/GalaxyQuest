package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.controllers.MainMenuController;
import io.github.jornegitgud.galaxyquest.controllers.SettingsController;
import io.github.jornegitgud.galaxyquest.gameObjects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    protected static HighScore[] highScoresList;

    public static void main(String[] args) {
        launch(args);
    }

    private Scene mainMenuScene;
    private Scene settingsScene;
    private GalaxySettings galaxySettings = new GalaxySettings();


    @Override
    public void start(Stage stage) throws Exception {

        highScoresList = new HighScore[5];
        for(int i = 0 ; i < highScoresList.length; i++){
            highScoresList[i] = new HighScore();
        }

        stage.setTitle("Galaxy Quest");
        FXMLLoader fxmlLoaderMenu = new FXMLLoader(getClass().getClassLoader().getResource("GalaxyQuestMainMenu.fxml"));
        FXMLLoader fxmlLoaderSettings = new FXMLLoader( getClass().getClassLoader().getResource("Settings.fxml"));

        this.mainMenuScene = new Scene(fxmlLoaderMenu.load(), 768, 768);
        this.settingsScene = new Scene( fxmlLoaderSettings.load(), 768,768);
        stage.setScene(mainMenuScene);

        stage.show();

        var controller = (MainMenuController) fxmlLoaderMenu.getController();
        var settingsController = (SettingsController) fxmlLoaderSettings.getController();

        controller.onStartButtonClicked = (event) -> {
            try {
                GameManager gameManager = new GameManager(askForName(), stage, this.galaxySettings);



                gameManager.onGameEnded = (gameResult) -> {

                    stage.setScene(this.mainMenuScene);
                    this.galaxySettings.unfreezeSettings();
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        controller.onExitButtonClicked = (event) -> {
            stage.close();
        };

        controller.onSettingsbuttonClicked = (event) -> {
            stage.setScene(settingsScene);
            settingsController.setSettings(galaxySettings);
        };

        settingsController.onSettingsBackClicked = (settings) -> {
            this.galaxySettings = settings;
            stage.setScene(mainMenuScene);
        };

    }
    public String askForName(){
        TextInputDialog dialog = new TextInputDialog("Wouter");
        dialog.setTitle("player name");
        dialog.setHeaderText("Your score might end up in the highscores list");
        dialog.setContentText("Please enter your name: (6 Characters)");


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        } else return "Player";

    }



}
