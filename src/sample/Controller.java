package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Integer> sequence = new ArrayList<Integer>();
    private int current, level;
    private boolean simonSays;
    private final int RED = 0;
    private final int GREEN = 1;
    private final int BLUE = 2;
    private final int YELLOW = 3;
    private BackEnd highScores = new BackEnd("src/sample/scores.csv");

    @FXML
    private Label score, console, highScore;
    @FXML
    private Button red, green, blue, yellow;

    @FXML
    public void initialize() {
        reset();
        displayScores();
        nextLevel();
    }

    private void reset() {
        sequence = new ArrayList<>();
        current = 0;
        level = 0;
        red.setDisable(false);
        green.setDisable(false);
        blue.setDisable(false);
        yellow.setDisable(false);
    }

    private void nextLevel() {
        simonSays = true;
        current = 0;
        score.setText("Level " + ++level);
        console.setText("Simon says...");
        sequence.add((int) (Math.random() * 4));
        new AnimationTimer() {
            Long time = System.currentTimeMillis();
            int i = 0;

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 1000) {
                    time = System.currentTimeMillis();
                    switch (sequence.get(i)) {
                        case RED:
                            redClick();
                            break;
                        case GREEN:
                            greenClick();
                            break;
                        case BLUE:
                            blueClick();
                            break;
                        case YELLOW:
                            yellowClick();
                            break;
                    }
                    i++;
                    if (i == sequence.size()) {
                        current = 0;
                        console.setText("Your turn.");
                        simonSays = false;
                        this.stop();
                    }
                }
            }
        }.start();
    }

    private void redClick() {
        red.setStyle("-fx-background-color: #F24F1C");
        new AnimationTimer() {
            Long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 500) {
                    red.setStyle("-fx-background-color: #77260E");
                    this.stop();
                }
            }
        }.start();
    }

    public void redClick(ActionEvent event) {
        if (!simonSays) {
            checkAnswer(RED);
            redClick();
        }
    }

    private void greenClick() {
        green.setStyle("-fx-background-color: #80BB00");
        new AnimationTimer() {
            Long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 500) {
                    green.setStyle("-fx-background-color: #425E00");
                    this.stop();
                }
            }
        }.start();
    }

    public void greenClick(ActionEvent event) {
        if (!simonSays) {
            checkAnswer(GREEN);
            greenClick();
        }
    }

    private void blueClick() {
        blue.setStyle("-fx-background-color: #00A6EF");
        new AnimationTimer() {
            Long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 500) {
                    blue.setStyle("-fx-background-color: #005377");
                    this.stop();
                }
            }
        }.start();
    }

    public void blueClick(ActionEvent e) {
        if (!simonSays) {
            checkAnswer(BLUE);
            blueClick();
        }
    }

    private void yellowClick() {
        yellow.setStyle("-fx-background-color: #FFBA00");
        new AnimationTimer() {
            Long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 500) {
                    yellow.setStyle("-fx-background-color: #7F5B00");
                    this.stop();
                }
            }
        }.start();
    }

    public void yellowClick(ActionEvent e) {
        if (!simonSays) {
            checkAnswer(YELLOW);
            yellowClick();
        }
    }

    private void checkAnswer(int type) {
        if (sequence.get(current) != type) {
            console.setText("Game over!");
            highScores.addScore(level);
            displayScores();
            red.setDisable(true);
            green.setDisable(true);
            blue.setDisable(true);
            yellow.setDisable(true);
            new AnimationTimer() {
                Long time = System.currentTimeMillis();

                @Override
                public void handle(long now) {
                    if (System.currentTimeMillis() - time > 1000) {
                        reset();
                        nextLevel();
                        this.stop();
                    }
                }
            }.start();
        } else {
            current++;
            if (current == sequence.size())
                nextLevel();
        }
    }

    private void displayScores() {
        StringBuilder out = new StringBuilder();
        ArrayList<String> dates = highScores.getDates();
        ArrayList<Integer> scores = highScores.getScores();
        for (int i = 0; i < dates.size(); i++)
            out.append(i + 1).append(". ").append(dates.get(i)).append(" ......... ").append(scores.get(i)).append("\n");
        highScore.setText(out.toString());
    }
}
