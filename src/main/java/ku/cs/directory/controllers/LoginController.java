package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;

public class LoginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label errorText;

    private UserModelList userModelList;

    private DataSource<UserModelList> usersModelListDataSource;


    public void initialize() {
        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

    }

    @FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        errorText.setText("");

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.equals("") || password.equals("")) {
            errorText.setText(" * Please Enter Username and Password");
            return;
        }

        UserModel user = userModelList.findUser(username, password);


        if (user != null) {
            user.stampTimeNow();
            usersModelListDataSource.writeData(userModelList);


            try {
                com.github.saacsos.FXRouter.goTo("home", user.getUsername());
            } catch (IOException e) {
                System.err.println("ไปที่หน้า home ไม่ได้");
            }

        } else {
            errorText.setText("   * Username or Password is Incorrect");
        }
    }

    @FXML
    public void handleUserRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("user_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user_register ไม่ได้");
        }
    }

    @FXML
    public void handleOfficerRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("officer_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officer_register ไม่ได้");
        }
    }

    @FXML
    public void handleDirectionButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("guide_for_user");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า guide_for_user ไม่ได้");
        }
    }

}
