package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;

public class ChangePasswordController {

    @FXML private TextField usernameTextField;

    @FXML private TextField currentPasswordTextField;

    @FXML private TextField newPasswordTextField;

    @FXML private Label errorText;

    private UserModelList userModelList;

    private DataSource<UserModelList> usersModelListDataSource;

    public void initialize(){
        usersModelListDataSource = new UsersModelListFileDataSource("data","Users.csv");
        userModelList = usersModelListDataSource.readData();
    }

    @FXML
    public void handleUpdatePasswordButton(ActionEvent actionEvent){

        String username = usernameTextField.getText();
        String currentPassword = currentPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();

        UserModel user = userModelList.findUser(username,currentPassword);

        if(currentPassword != newPassword){
            errorText.setText("* Incorrect Password");
        }

        if(user != null){

            user.setPassword(newPassword);

            usersModelListDataSource.writeData(userModelList);

            try {
                com.github.saacsos.FXRouter.goTo("login");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า login ไม่ได้");
            }
        }
        else {
            errorText.setText("* Username not Found");
        }
    }


    @FXML
    public void handleBackButton(ActionEvent actionEvent){
            try {
                com.github.saacsos.FXRouter.goTo("profile");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า profile ไม่ได้");
            }
    }
}
