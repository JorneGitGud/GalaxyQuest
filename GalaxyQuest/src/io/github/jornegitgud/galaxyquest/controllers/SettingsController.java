package io.github.jornegitgud.galaxyquest.controllers;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import io.github.jornegitgud.galaxyquest.Galaxy;
import io.github.jornegitgud.galaxyquest.GalaxySettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.function.Consumer;


public class SettingsController {
    private GalaxySettings settings;

    public void setSettings(GalaxySettings settings) {
        this.settings = settings;
        int size = Math.max(settings.getWidth(), settings.getHeight());
        galaxySizeSlider.setValue(size);
        galaxySizeLabel.setText(String.valueOf(size));
    }

    @FXML
    private Slider galaxySizeSlider, planetSlider, pirateSlider, meteorSlider;

    @FXML
    private Label galaxySizeLabel;

    private double planetCount;
    private double pirateCount;
    private double meteoriteCount;

    public Consumer<GalaxySettings> onSettingsBackClicked = (settings) -> {
    };


    @FXML
    public void backToMenu() {
        this.onSettingsBackClicked.accept(this.settings);
    }



    @FXML
    public void setGalaxySize() {
        int value = (int) galaxySizeSlider.getValue();
        galaxySizeLabel.setText(String.valueOf(value));
        settings.setWidth(value);
        settings.setHeight(value);
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
