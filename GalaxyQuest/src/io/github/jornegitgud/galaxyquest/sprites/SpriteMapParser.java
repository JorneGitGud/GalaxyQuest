package io.github.jornegitgud.galaxyquest.sprites;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



/**
 * the spriteMapParser class is used to create usable sprite from the project images. it has a couple of functions that take in single or multiple sprite images.
 */
public class SpriteMapParser {
    /**
     * this method creates an arrayList of one or more Images.
     * @param image the image to be cut
     * @param singleSpriteWidthPx height of the sprite
     * @param singleSpriteHeightPx width of the sprite
     * @return Arraylist with one or more Images
     */
    public static ArrayList<javafx.scene.image.Image> parseSpriteMapToImages(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx) {
        return parseSpriteMapToImages(image, singleSpriteWidthPx, singleSpriteHeightPx, 0, 0);
    }

    /**
     * this method creates an arrayList of one or more Images and can be used if an offset to the sprites is needed.
     * @param image the image to be cut up into smaller sprites
     * @param singleSpriteWidthPx the height of the desires sprites
     * @param singleSpriteHeightPx the width of the desires sprites
     * @param startX the start point from where to start cutting
     * @param startY the start point from where to start cutting
     * @return Arraylist with one or more Images.
     */
    public static ArrayList<javafx.scene.image.Image> parseSpriteMapToImages(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx, int startX, int startY) {
        BufferedImage bufferedImage;
        if(image instanceof BufferedImage)
            bufferedImage = (BufferedImage)image;
        else {
            bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = bufferedImage.createGraphics();
            graphics.drawImage(image, 0, 0, null);
            graphics.dispose();
        }

        if(image.getHeight(null) < startY + singleSpriteHeightPx)
            return new ArrayList<>();

        var imageWidth = image.getWidth(null);
        var spriteCount = (int) Math.floor((imageWidth - startX) / 1.0d / singleSpriteWidthPx);
        if(spriteCount == 0)
            return new ArrayList<>();

        var sprites = new ArrayList<javafx.scene.image.Image>();
        for(int i = 0; i < spriteCount; i++) {
            sprites.add(SwingFXUtils.toFXImage(bufferedImage.getSubimage(startX + (i * singleSpriteWidthPx), startY, singleSpriteWidthPx, singleSpriteHeightPx), null));
        }

        return sprites;
    }

    /**
     * this method creates an arrayList of one or more Images and can be used if an offset to the sprites is needed.
     * @param image the image to be cut up into smaller sprites
     * @param singleSpriteWidthPx the height of the desires sprites
     * @param singleSpriteHeightPx the width of the desires sprites
     * @param startX the start point from where to start cutting
     * @param startY the start point from where to start cutting
     * @return Arraylist with one or more Images in an ImageView
     */
    public static ArrayList<ImageView> parseSpriteMapToImageViews(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx, int startX, int startY) {
        ArrayList<javafx.scene.image.Image> images = parseSpriteMapToImages(image, singleSpriteWidthPx, singleSpriteHeightPx, startX, startY);
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (javafx.scene.image.Image value : images) {
            imageViews.add(new ImageView(value));
        }
        return imageViews;
    }
}
