package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.controllers.MainMenuController;
import io.github.jornegitgud.galaxyquest.controllers.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


/**
 * this class is where the game starts.
 */

public class Main extends Application {
    protected static HighScore[] highScoresList;

    public static void main(String[] args) {
        launch(args);
    }

    private Scene mainMenuScene;
    private Scene settingsScene;
    private GalaxySettings galaxySettings = new GalaxySettings();

    /**
     * This method starts the game.
     * @param stage is used to open a window.
     * @throws Exception is trowns when there is no stage found.
     */
    @Override
    public void start(Stage stage) throws Exception {
        highScoresList = HighScoreStorage.loadHighScores();

        stage.setTitle("Galaxy Quest");
        FXMLLoader fxmlLoaderMenu = new FXMLLoader(getClass().getClassLoader().getResource("GalaxyQuestMainMenu.fxml"));
        FXMLLoader fxmlLoaderSettings = new FXMLLoader( getClass().getClassLoader().getResource("Settings.fxml"));

        this.mainMenuScene = new Scene(fxmlLoaderMenu.load(), 768, 768);
        this.settingsScene = new Scene( fxmlLoaderSettings.load(), 768,768);
        stage.setScene(mainMenuScene);

        stage.show();
        stage.setResizable(false);

        var controller = (MainMenuController) fxmlLoaderMenu.getController();
        var settingsController = (SettingsController) fxmlLoaderSettings.getController();

        controller.setHighScores(highScoresList);


        controller.onStartButtonClicked = (event) -> {
            try {
                GalaxyRenderer galaxyRenderer = new GalaxyRenderer(stage, this.galaxySettings);
                GameManager gameManager = new GameManager(this.galaxySettings, galaxyRenderer);

                gameManager.onGameEnded = (gameResult) -> {
                    if(gameResult.win) {
                        String playerName = askForName();
                        HighScore highScore = gameResult.highScore;
                        highScore.setName(playerName);

                        if(highScoresList[4].getScore() < highScore.getScore()) {
                            highScoresList[4] = highScore;
                            Arrays.sort(highScoresList);
                            HighScoreStorage.saveHighscores(highScoresList);
                        }
                    }

                    controller.setHighScores(highScoresList);
                    stage.setScene(this.mainMenuScene);
                    this.galaxySettings.unfreezeSettings();
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        controller.onExitButtonClicked = (event) -> stage.close();

        controller.onSettingsbuttonClicked = (event) -> {
            stage.setScene(settingsScene);
            settingsController.setSettings(galaxySettings);
        };

        settingsController.onSettingsBackClicked = (settings) -> {
            this.galaxySettings = settings;
            stage.setScene(mainMenuScene);
        };

    }

    /**
     * This method is used to open a PopUp window. in the window you can enter your name if you won.
     * @return String with the name in all uppercases and with a maximum of 6 symbols.
     */
    public String askForName(){
        TextInputDialog dialog = new TextInputDialog("Wouter");
        dialog.setTitle("Choose wisely!");
        dialog.setHeaderText("You won! enter your name to display in the high scores.");
        dialog.setContentText("Please enter your name: (will be shortened to 6 characters)");


        Optional<String> result = dialog.showAndWait();
        String name = result.orElse("Player");
        if(name.length() > 6)
            return name.substring(0, 6);
        return name;

    }


}
