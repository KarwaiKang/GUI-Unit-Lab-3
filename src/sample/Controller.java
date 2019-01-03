package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controller {
    private ArrayList<Integer> sequence = new ArrayList<Integer>();

    @FXML
    private Label label;

    @FXML
    private Button red, green, blue, yellow;

    @FXML
    public void initialize() {
        addToSequence();
        doSequence();
    }

    private void addToSequence() {
        int randInt = (int) (Math.random() * 4);
        sequence.add(randInt);
    }

    private void doSequence() {
        for (int i : sequence) {
            switch (i) {
                case 0:
                    red.setStyle("-fx-background-color: red; ");
                    break;
                case 1:
                    green.setStyle("-fx-background-color: green; ");
                    break;
                case 2:
                    blue.setStyle("-fx-background-color: blue; ");
                    break;
                case 3:
                    yellow.setStyle("-fx-background-color: yellow; ");
                    break;
            }
        }
    }

    public void click(ActionEvent e) {
        blue.setText("Clicked!");
    }
}
