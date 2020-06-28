package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;

/**
 * this is the GameResult class.
 * this class is used to determine if the game is won or not.
 *
 */

public class GameResult {
    Boolean win;
    HighScore highScore;

    /**
     * This method can be called to determine the outcome of the game
     * @param win if this is true the game is won
     * @param highScore has a object of HighScore to make sure you can only win if you beat the game.
     */

    public GameResult(Boolean win, HighScore highScore) {
        this.win = win;
        this.highScore = highScore;
    }
}
