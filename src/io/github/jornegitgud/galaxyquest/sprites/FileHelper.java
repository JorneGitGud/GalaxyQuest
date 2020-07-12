package io.github.jornegitgud.galaxyquest.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * the fileHelper class is used in loading images for use as sprites.
 */
public class FileHelper {

    /**
     * Takes in a path to a file, and replaces all occurrences of '/' and '\' with the correct version for the OS the application is running on.
     * @param path to path that should be checked an may be corrected.
     * @return returns a string with a 'working' path
     */
    private static String normalizePath(String path) {
        if (!path.startsWith("/") && !path.startsWith("\\"))
            path = "/" + path;

        String[] parts = path.split("[/\\\\]");
        return System.getProperty("user.dir") + File.separator  + File.separator + String.join(File.separator, parts);

    }

    /**
     * Reads a file from the filesystem into memory, returning the file's contents as a FileInputStream
     *
     * @param path the path of the file
     * @return the memory stream representing the file
     * @throws FileNotFoundException if the file could not be read.
     */
    public static FileInputStream createInputStream(String path) throws FileNotFoundException {
        String normalizedPath = normalizePath(path);

        return new FileInputStream(normalizedPath);
    }

    /**
     * Takes a path to a file and creates a Swing image.
     * @param path the path to the file
     * @return the image as java.awt.image.BufferedImage object
     * @throws IOException  if this input operation is failed
     */
    public static java.awt.image.BufferedImage createImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return ImageIO.read(stream);
    }

    /**
     * Takes a path to a file and creates a JavaFX image
     * @param path the path to the file
     * @return the image as javafx.scene.image.Image object.
     * @throws IOException  if this input operation is failed
     */
    public static Image createFxImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return new Image(stream);
    }

    /**
     * Takes a path to a file and creates an ImageView.
     * @param path the path to the file
     * @return the image as javafx.scene.image.ImageView object
     * @throws IOException if this input operation is failed
     */
    public static ImageView createImageView(String path) throws IOException {
        Image image = createFxImage(path);

        return new ImageView(image);
    }

}
