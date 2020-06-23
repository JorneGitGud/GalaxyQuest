package io.github.jornegitgud.galaxyquest.sprites;

import io.github.jornegitgud.galaxyquest.Direction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public interface SpriteList {

    Image getNextSprite();

    Image getNextSprite(Direction dir);
}
