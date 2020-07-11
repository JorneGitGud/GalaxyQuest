package io.github.jornegitgud.galaxyquest.controllers;

import io.github.jornegitgud.galaxyquest.HighScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.function.Consumer;

public class MainMenuController {

    @FXML
    private Label highScore1, highScore2, highScore3, highScore4, highScore5;

    public Consumer<ActionEvent> onStartButtonClicked = (event) -> {
    };
    public Consumer<ActionEvent> onExitButtonClicked = (event) -> {
    };
    public Consumer<ActionEvent> onSettingsButtonClicked = (event) -> {
    };

    @FXML
    public void startGame(ActionEvent event) {
        this.onStartButtonClicked.accept(event);
    }

    @FXML
    public void exitGame(ActionEvent event) {
        this.onExitButtonClicked.accept(event);
    }

    @FXML
    public void openSettings(ActionEvent event) {
        this.onSettingsButtonClicked.accept(event);
    }

    @FXML
    public void setHighScores(HighScore[] highScores){
        this.highScore1.setText(highScores[0].getScore() > 0 ? highScores[0].toString() : "");
        this.highScore2.setText(highScores[1].getScore() > 0 ? highScores[1].toString() : "");
        this.highScore3.setText(highScores[2].getScore() > 0 ? highScores[2].toString() : "");
        this.highScore4.setText(highScores[3].getScore() > 0 ? highScores[3].toString() : "");
        this.highScore5.setText(highScores[4].getScore() > 0 ? highScores[4].toString() : "");
    }
}

