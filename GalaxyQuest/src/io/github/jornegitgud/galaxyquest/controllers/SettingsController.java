package io.github.jornegitgud.galaxyquest.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.function.Consumer;


public class SettingsController {

    public Consumer<ActionEvent> onSettingsBackClicked = (event) -> {};


    @FXML
    public void backToMenu(ActionEvent event) {
        this.onSettingsBackClicked.accept(event);
    }


}
