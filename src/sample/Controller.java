package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Integer> sequence = new ArrayList<Integer>();

    @FXML
    private Label label;

    @FXML
    private Button red, green, blue, yellow;

    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            addToSequence();
        }
        doSequence();
    }

    private void addToSequence() {
        int randInt = (int) (Math.random() * 4);
        sequence.add(randInt);
    }

    private void doSequence() {
        new AnimationTimer() {
            Long time = System.currentTimeMillis();
            int i = 0;
            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time > 1000) {
                    switch (sequence.get(i)) {
                        case 0:
                            red.fire();
                            break;
                        case 1:
                            green.fire();
                            break;
                        case 2:
                            blue.fire();
                            break;
                        case 3:
                            yellow.fire();
                            break;
                    }
                    if (i == sequence.size()) {
                        this.stop();
                    }
                    time = System.currentTimeMillis();
                }
            }
        }.start();
    }

    public void redClick(ActionEvent event) {
        red.setText("Clicked!");
    }

    public void blueClick(ActionEvent e) {
        blue.setText("Clicked!");
    }

    public void greenClick(ActionEvent event) {
        green.setText("Clicked!");
    }

    public void yellowClick(ActionEvent e) {
        yellow.setText("Clicked!");
    }
}
