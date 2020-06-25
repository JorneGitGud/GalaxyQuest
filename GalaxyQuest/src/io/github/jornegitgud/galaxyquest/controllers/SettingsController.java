package io.github.jornegitgud.galaxyquest.controllers;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

import java.util.function.Consumer;


public class SettingsController {
    private Slider galaxySlider;
    private double galaxySize;
    private double planetCount;
    private double pirateCount;
    private double meteoriteCount;

    public Consumer<ActionEvent> onSettingsBackClicked = (event) -> {
    };


    @FXML
    public void backToMenu(ActionEvent event) {
        this.onSettingsBackClicked.accept(event); }

    @FXML
    public void setGalaxySize() {
        this.galaxySize = galaxySlider.getValue();
        System.out.println(this.galaxySize + " ");
    }

    @FXML
    public void setPlanetCount(Slider slider) {
        this.planetCount = slider.getValue();
    }

    @FXML
    public void setPirateCount(Slider slider) {
        this.pirateCount = slider.getValue();
    }

    @FXML
    public void setMeteoriteCount(Slider slider) {
        this.meteoriteCount = slider.getValue();
    }

    @FXML
    public void applySettings(ActionEvent event) {

    }
}
