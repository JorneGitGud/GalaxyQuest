package io.github.jornegitgud.galaxyquest.controllers;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

import java.util.function.Consumer;


public class SettingsController {

    @FXML
    private Slider galaxySizeSlider, planetSlider, pirateSlider, meteorSlider;

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
        this.galaxySize = galaxySizeSlider.getValue();
    }
    
    @FXML
    public void setPlanetSlider() {
        this.planetCount = planetSlider.getValue();
    }

    @FXML
    public void setPirateCount() {
        this.pirateCount = pirateSlider.getValue();
    }

    @FXML
    public void setMeteoriteCount() {
        this.meteoriteCount = meteorSlider.getValue();
    }

}
