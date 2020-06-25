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

        planetSlider.setValue(settings.getPlanetCount());
        planetCountLabel.setText(String.valueOf(settings.getPlanetCount()));

        meteoriteSlider.setValue(settings.getMeteoriteCount());
        meteoriteCountLabel.setText(String.valueOf(settings.getMeteoriteCount()));

        pirateSlider.setValue(settings.getPirateCount());
        pirateCountLabel.setText(String.valueOf(settings.getPirateCount()));
    }

    @FXML
    private Slider galaxySizeSlider, planetSlider, pirateSlider, meteoriteSlider;

    @FXML
    private Label galaxySizeLabel, planetCountLabel, meteoriteCountLabel, pirateCountLabel;

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
        int value = (int) planetSlider.getValue();
        planetCountLabel.setText(String.valueOf(value));
        settings.setPlanetCount(value);
    }

    @FXML
    public void setPirateCount() {
        int value = (int) pirateSlider.getValue();
        pirateCountLabel.setText(String.valueOf(value));
        settings.setPirateCount(value);
    }

    @FXML
    public void setMeteoriteCount() {
        int value = (int) meteoriteSlider.getValue();
        meteoriteCountLabel.setText(String.valueOf(value));
        settings.setMeteoriteCount(value);
    }

}
