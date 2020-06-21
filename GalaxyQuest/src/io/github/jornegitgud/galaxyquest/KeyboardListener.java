package io.github.jornegitgud.galaxyquest;

import javafx.scene.Scene;

import java.util.function.Consumer;

public class KeyboardListener {
    Scene gameScene;

    public boolean areAllKeysPressed() {
        return isUpPressed && isDownPressed && isLeftPressed && isRightPressed;
    }

    public boolean isAnyKeyPressed() {
        return isUpPressed || isDownPressed || isLeftPressed || isRightPressed;
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public boolean isDownPressed() {
        return isDownPressed;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    boolean isUpPressed;
    boolean isRightPressed;
    boolean isDownPressed;
    boolean isLeftPressed;

    public Consumer<Direction> onKeyPressed;
    public Consumer<Direction> onKeyReleased;


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
}
