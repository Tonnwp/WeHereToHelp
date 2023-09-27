package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.directory.models.OfficerModel;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.File;
import java.io.IOException;

public class OfficersRegisterController {

    private DataSource<UserModelList> usersModelListDataSource;

    private UserModelList userModelList;

    private String[] agency = {"Safety","Service","Health","Transport","Legal"};
    @FXML ChoiceBox<String> agencyChoiceBox;

    @FXML
    ImageView defaultImage;

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField displayNameTextField;

    @FXML
    TextField confirmPassWordTextField;

    @FXML
    Label errorText1;
    @FXML
    Label errorText2;

    public void initialize() {

        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        File imageFile = new File("images/profile.png");
        defaultImage.setImage(new Image(imageFile.toURI().toString()));

        agencyChoiceBox.setValue("Please Choose Your Agency");
        agencyChoiceBox.getItems().addAll(agency);
    }

    @FXML
    public void handleSignUpButton(ActionEvent actionEvent) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPassWordTextField.getText();
        String displayName = displayNameTextField.getText();
        String agencys = agencyChoiceBox.getValue();

        UserModel user = userModelList.findByUsername(username);



        if (user != null) {
            errorText1.setText("   * Username Already used"); //fx:id
            errorText2.setText(" Please Try Again");


        } else {
            if (!password.equals(confirmPassword)) {
                errorText1.setText("   * Incorrect Password"); //fx:id
                errorText2.setText(" Please Try Again");

            } else {
                userModelList.addUsers(new OfficerModel(username, password, displayName,agencys));


                usersModelListDataSource.writeData(userModelList);

                try {
                    com.github.saacsos.FXRouter.goTo("login");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า login ไม่ได้");
                }

            }
        }
    }


    @FXML
    public void handleBackButton (ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
        }
    }

}

