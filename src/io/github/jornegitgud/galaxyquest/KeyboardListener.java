package io.github.jornegitgud.galaxyquest;

import javafx.scene.Scene;

import java.util.function.Consumer;

/**
 * this is the KeyBoardListener class.
 * this class is used to react to different keyboard input while playing the game
 */


public class KeyboardListener {

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

}
