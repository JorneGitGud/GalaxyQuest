package io.github.jornegitgud.galaxyquest.sprites;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * the fileHelper class is used in loading images for use as sprites.
 */
public class FileHelper {

    /**
     * Takes in a path to a file, and replaces all occurences of '/' and '\' with the correct version for the OS the application is running on.
     * @param path
     * @return
     */
    private static String normalizePath(String path) {
        if (!path.startsWith("/") && !path.startsWith("\\"))
            path = "/" + path;

        String[] parts = path.split("[/\\\\]");
        return System.getProperty("user.dir") + File.separator + "GalaxyQuest" + File.separator + String.join(File.separator, parts);
    }

    /**
     * Reads a file from the filesystem into memory, returning the file's contents as a FileInputStream
     *
     * @param path the path of the file
     * @return the memory stream representing the file
     * @throws FileNotFoundException
     */
    public static FileInputStream createInputStream(String path) throws FileNotFoundException {
        String normalizedPath = normalizePath(path);

        return new FileInputStream(normalizedPath);
    }

    /**
     * Takes a path to a file and creates a Swing image.
     * @param path the path to the file
     * @return the image as java.awt.image.BufferedImage object
     * @throws IOException
     */
    public static java.awt.image.BufferedImage createImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return ImageIO.read(stream);
    }

    /**
     * Takes a path to a file and creates a JavaFX image
     * @param path the path to the file
     * @return the image as javafx.scene.image.Image object.
     * @throws IOException
     */
    public static Image createFxImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return new Image(stream);
    }

    /**
     * Takes a path to a file and creates an ImaeView.
     * @param path the path to the file
     * @return the image as javafx.scene.image.ImageView object
     * @throws IOException
     */
    public static ImageView createImageView(String path) throws IOException {
        Image image = createFxImage(path);

        return new ImageView(image);
    }

}
