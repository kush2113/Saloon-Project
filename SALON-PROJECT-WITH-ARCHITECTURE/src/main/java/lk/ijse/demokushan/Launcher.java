package lk.ijse.demokushan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Launcher {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}