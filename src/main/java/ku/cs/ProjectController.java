package ku.cs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProjectController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
