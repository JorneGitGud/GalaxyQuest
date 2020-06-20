package io.github.jornegitgud.galaxyquest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

    public static Image createImage(String path) throws FileNotFoundException {
        FileInputStream stream = createInputStream(path);

        return new Image(stream);
    }

    public static ImageView createImageView(String path) throws FileNotFoundException {
        Image image = createImage(path);

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
