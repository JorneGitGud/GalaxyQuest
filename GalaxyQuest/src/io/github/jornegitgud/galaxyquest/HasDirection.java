package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.*;

import java.util.function.Consumer;

/**
 * this is an interface for for classes that use a direction
 */
public interface HasDirection {

    /**
     * This method gets the Direction of an object
     * @return Direction
     */
    Direction getDirection();

    /**
     * This method sets the direction of an object
     * @param direction is used to set the new direction of an object.
     */
    void setDirection(Direction direction);

}
