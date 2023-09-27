package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.File;
import java.io.IOException;

public class UsersRegisterController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private TextField displayNameTextField;
    @FXML
    private Label errorText1;
    @FXML
    private Label errorText2;
    @FXML
    ImageView defaultImage;
    private UserModelList userModelList;
    private DataSource<UserModelList> usersModelListDataSource;


    public void initialize() {
        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        File imageFile = new File("images/profile.png");
        defaultImage.setImage(new Image(imageFile.toURI().toString()));
    }

    @FXML
    public void handleSignUpButton(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        String displayName = displayNameTextField.getText();

        UserModel user = userModelList.findByUsername(username);

        if (user != null) {
            // มีอยู่แล้ว
            errorText1.setText("* Username Already Used"); //fx:id
            errorText2.setText("Please Try Again");

        } else {
            if (!password.equals(confirmPassword)) {
                errorText1.setText("   * Incorrect Password"); //fx:id
                errorText2.setText(" Please Try Again");

            } else {
                userModelList.addUsers(new UserModel(username,password,displayName));

                usersModelListDataSource.writeData(userModelList);

                clearTextField();
                try {
                    com.github.saacsos.FXRouter.goTo("login");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า login ไม่ได้");
                }
            }
        }

    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
        }
    }

    public void clearTextField() {
        usernameTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
        displayNameTextField.clear();
    }

}
