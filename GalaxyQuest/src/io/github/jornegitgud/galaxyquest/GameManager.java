package io.github.jornegitgud.galaxyquest;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    ArrayList<HighScore> highScores;
    GalaxyRenderer renderer;
    KeyboardListener keyboardListener;

    public GameManager(Stage stage, GalaxySettings galaxySettings) {
        highScores = new ArrayList<>();
        renderer = new GalaxyRenderer(stage, galaxySettings);
        keyboardListener = new KeyboardListener(renderer.getScene());

        keyboardListener.onKeyPressed = (direction) -> {
            switch(direction) {
                case UP:
                    break;
                case DOWN:

            }
        }
    }




    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }
}
