package io.github.jornegitgud.galaxyquest;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;
    Galaxy galaxy;

    public GameManager(Stage stage, GalaxySettings galaxySettings) {
        galaxy = new Galaxy("John", galaxySettings);
        populateGalaxy(galaxy);
        highScores = new ArrayList<>();
        renderer = new GalaxyRenderer(stage, galaxySettings);
        keyboardListener = new KeyboardListener(renderer.getScene());


    }

    private void populateGalaxy(Galaxy galaxy){

        //player
        //pirates
        //meteorites
        //planets



    }



    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
