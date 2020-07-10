package io.github.jornegitgud.galaxyquest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * this class stores the highscores in a text file. Wich is saved on the machine that runs the game.
 */
public class HighScoreStorage {
    private static final String HIGHSCORE_FILE_PATH = System.getProperty("user.dir") + File.separator  + File.separator + "highscores.txt";
    //private static final String HIGHSCORE_FILE_PATH = System.getProperty("user.dir") + File.separator + "GalaxyQuest" + File.separator + "highscores.txt";

    /**
     * This method saves the highscores in a text file.
     * @param highscores this ArrayList is used the save the scores.
     */
    public static void saveHighscores(HighScore[] highscores) {
        File file = new File(HIGHSCORE_FILE_PATH);
        if (file.exists())
            file.delete();

        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE_PATH);
            for (HighScore highscore : highscores)
                writer.write(highscore.getName() + ":" + highscore.getScore() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads the highscores out of the text file.
     * it creates a new file if the file does not exist.
     * Deletes the file if i contains errors.
     * @return this method returns an HighScore[]
     */
    public static HighScore[] loadHighScores() {
        File file = new File(HIGHSCORE_FILE_PATH);
        var highScoresList = new HighScore[5];
        for (int i = 0; i < highScoresList.length; i++) {
            highScoresList[i] = new HighScore();
        }

        if (!file.exists()) {
            return highScoresList;
        }

        try {
            Scanner reader = new Scanner(file);
            int counter = 0;
            while (reader.hasNextLine() && counter < 5) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                if(parts[0].length() > 6)
                    parts[0] = parts[0].substring(0, 6);
                highScoresList[counter] = new HighScore(parts[0], Integer.parseInt(parts[1]));
                counter++;
            }
        } catch (Exception e) {
            file.delete();
            return highScoresList;
        }

        Arrays.sort(highScoresList);
        return highScoresList;
    }
}
