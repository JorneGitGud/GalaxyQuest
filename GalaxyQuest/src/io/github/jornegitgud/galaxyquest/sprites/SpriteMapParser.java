package io.github.jornegitgud.galaxyquest.sprites;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteMapParser {
    public static ArrayList<javafx.scene.image.Image> parseSpriteMapToImages(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx) {
        return parseSpriteMapToImages(image, singleSpriteWidthPx, singleSpriteHeightPx, 0, 0);
    }

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

    public static ArrayList<ImageView> parseSpriteMapToImageViews(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx) {
        return parseSpriteMapToImageViews(image, singleSpriteWidthPx, singleSpriteHeightPx, 0, 0);
    }

    public static ArrayList<ImageView> parseSpriteMapToImageViews(Image image, int singleSpriteWidthPx, int singleSpriteHeightPx, int startX, int startY) {
        ArrayList<javafx.scene.image.Image> images = parseSpriteMapToImages(image, singleSpriteWidthPx, singleSpriteHeightPx, startX, startY);
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for(var i = 0; i < images.size(); i++) {
            imageViews.add(new ImageView(images.get(i)));
        }
        return imageViews;
    }
}
