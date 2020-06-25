package io.github.jornegitgud.galaxyquest.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.function.Consumer;

public class MainMenuController {
    public Consumer<ActionEvent> onStartButtonClicked = (event) -> {};
    public Consumer<ActionEvent> onExitButtonClicked = (event) -> {};

    @FXML
    public void startGame(ActionEvent event) {
        this.onStartButtonClicked.accept(event);
    }

    @FXML
    public void exitGame(ActionEvent event) { this.onExitButtonClicked.accept(event); }
}
