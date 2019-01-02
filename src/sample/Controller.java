package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class Controller {
    @FXML
    private Label label;

    @FXML
    private Button button;

    public void click(ActionEvent actionEvent) {
        button.setText("Clicked!");
    }
}
