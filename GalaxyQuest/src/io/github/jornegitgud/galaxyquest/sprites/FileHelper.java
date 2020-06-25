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

//to do Javadoc
public class FileHelper {
    private static String normalizePath(String path) {
        if(!path.startsWith("/") && !path.startsWith("\\"))
            path = "/" + path;

        String[] parts = path.split("[/\\\\]");
        return System.getProperty("user.dir") + File.separator + "GalaxyQuest" + File.separator + String.join(File.separator, parts);
    }

    public static FileInputStream createInputStream(String path) throws FileNotFoundException {
        String normalizedPath = normalizePath(path);

        return new FileInputStream(normalizedPath);
    }

    public static java.awt.image.BufferedImage createImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return ImageIO.read(stream);
    }

    public static Image createFxImage(String path) throws IOException {
        FileInputStream stream = createInputStream(path);

        return new Image(stream);
    }

    public static ImageView createImageView(String path) throws IOException {
        Image image = createFxImage(path);

        return new ImageView(image);
    }

    public static ArrayList<Image> createImages(String directoryPath, String regex) throws FileNotFoundException {
        ArrayList<Image> images = new ArrayList<>();

        String normalizedPath = normalizePath(directoryPath);
        File folder = new File(normalizedPath);

        Pattern pattern = Pattern.compile(regex);

        for(File file : folder.listFiles()) {
            if(file.isDirectory())
                continue;
            if(pattern.matcher(file.getName()).matches())
                images.add(new Image(new FileInputStream(file.getAbsolutePath())));
        }

        return images;
    }

}
