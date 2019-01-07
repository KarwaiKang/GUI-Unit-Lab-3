package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;

public class BackEnd {
    private Path pathToFile;
    private ArrayList<String> dates;
    private ArrayList<Integer> scores;

    public BackEnd(String path) {
        this.pathToFile = Paths.get(new File(path).getPath());
        this.dates = new ArrayList<>();
        this.scores = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] values = line.split(",");
                dates.add(values[0]);
                scores.add(Integer.parseInt(values[1]));
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void addScore(int score) {
        String date = new Date().toString();
        dates.add(date);
        scores.add(score);
        String line = "\n" + date + "," + score;
        try {
            Files.write(pathToFile, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
