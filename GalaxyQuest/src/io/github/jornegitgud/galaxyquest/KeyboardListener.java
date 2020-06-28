package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;
import javafx.scene.Scene;

import java.util.function.Consumer;

/**
 * this is the KeyBoardListener class.
 * this class is used to react to different keyboard input while playing the game
 */


public class KeyboardListener {

    private  Scene gameScene;
    private boolean isUpPressed;
    private boolean isRightPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;

    public Consumer<Direction> onKeyPressed;
    public Consumer<Direction> onKeyReleased;


    /**
     * This is the method that reacts to keys pressed. It react to buth wasd and the arrow keys on the keyboard.
     * @param gameScene the gameScene is given so the keyboardListener knows in what scene it needs
     * to respond to the keyboard.
     */

    public KeyboardListener(Scene gameScene) {
        this.gameScene = gameScene;

        gameScene.setOnKeyPressed((keyEvent -> {
            switch(keyEvent.getCode()) {
                case UP:
                case W:
                    if (!isUpPressed) {
                        isUpPressed = true;
                        onKeyPressed.accept(Direction.UP);
                     }
                    break;
                case RIGHT:
                case D:
                    if (!isRightPressed) {
                        isRightPressed = true;
                        onKeyPressed.accept(Direction.RIGHT);
                    }
                    break;
                case DOWN:
                case S:
                    if(!isDownPressed) {
                        isDownPressed = true;
                        onKeyPressed.accept(Direction.DOWN);
                    }
                    break;
                case LEFT:
                case A:
                    if(!isLeftPressed) {
                        isLeftPressed = true;
                        onKeyPressed.accept(Direction.LEFT);
                    }
                    break;
            }
        }));

        gameScene.setOnKeyReleased((keyEvent -> {
            switch(keyEvent.getCode()) {
                case UP:
                case W:
                    if(isUpPressed) {
                        isUpPressed = false;
                        onKeyReleased.accept(Direction.UP);
                    }
                    break;
                case RIGHT:
                case D:
                    if(isRightPressed) {
                        isRightPressed = false;
                        onKeyReleased.accept(Direction.RIGHT);
                    }
                    break;
                case DOWN:
                case S:
                    if(isDownPressed) {
                        isDownPressed = false;
                        onKeyReleased.accept(Direction.DOWN);
                    }
                    break;
                case LEFT:
                case A:
                    if(isLeftPressed) {
                        isLeftPressed = false;
                        onKeyReleased.accept(Direction.LEFT);
                    }
            }
        }));
    }

    /**
     * method to check if all keys (wasd and arrows) are pressed
     * @return boolean
     */
    public boolean areAllKeysPressed() {
        return isUpPressed && isDownPressed && isLeftPressed && isRightPressed;
    }

    /**
     * method to check if any key (wasd and arrows) is pressed
     * @return boolean
     */
    public boolean isAnyKeyPressed() {
        return isUpPressed || isDownPressed || isLeftPressed || isRightPressed;
    }

    /**
     * method to check if w or up arrow key is pressed
     * @return boolean
     */
    public boolean isUpPressed() {
        return isUpPressed;
    }

    /**
     * method to check if a or right arrow key is pressed
     * @return boolean
     */
    public boolean isRightPressed() {
        return isRightPressed;
    }

    /**
     * method to check if s or down arrow key is pressed
     * @return boolean
     */
    public boolean isDownPressed() {
        return isDownPressed;
    }

    /**
     * method to check if d or left arrow key is pressed
     * @return boolean
     */
    public boolean isLeftPressed() {
        return isLeftPressed;
    }
}
