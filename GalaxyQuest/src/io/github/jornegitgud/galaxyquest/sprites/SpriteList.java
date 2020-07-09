package io.github.jornegitgud.galaxyquest.sprites;

import io.github.jornegitgud.galaxyquest.Direction;
import javafx.scene.image.Image;

/*
this interface makes sure that classes that implement this interface have the getNextSprite functions
 */
public interface SpriteList {

    Image getNextSprite();

    Image getNextSprite(Direction dir);
}
