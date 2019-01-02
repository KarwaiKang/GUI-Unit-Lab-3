package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameCode extends Application {
    private ArrayList<Integer> sequence = new ArrayList<Integer>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GUI Unit Lab 3");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        addToSequence();
    }

    private void addToSequence() {
        int randInt = (int) (Math.random() * 4);
        sequence.add(randInt);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
