package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.directory.models.OfficerModel;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;

public class HomeController {

    private UserModelList userModelList;
    private UserModel user;

    private DataSource<UserModelList> usersModelListDataSource;

    @FXML
    private Label staffErrorLabel;

    public void initialize() {
        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        String username = (String) com.github.saacsos.FXRouter.getData();
        user = userModelList.findUser(username);
    }

    @FXML
    public void adminProfileButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_profile ไม่ได้");
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

    @FXML
    public void handleComplaintButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint", user.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint ไม่ได้");
        }
    }

    @FXML
    public void handleProfileButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("profile", user.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
        }
    }

    @FXML
    public void handleComplaintCategoryButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint_category", user.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint_category ไม่ได้");
        }
    }

    @FXML
    public void handleComplaintStatusButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint_status", user.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint_status ไม่ได้");
        }
    }

    @FXML
    public void handleOfficerOnlyComplaintButton(ActionEvent actionEvent) {
        if (user instanceof OfficerModel) {
            try {
                com.github.saacsos.FXRouter.goTo("officer_only_complaint", user.getUsername());
            } catch (IOException e) {
                System.err.println("ไปที่หน้า officer_only_complaint ไม่ได้");
            }
        } else {
            staffErrorLabel.setText("* You are not Staff!!");
        }
    }


    @FXML
    public void handleMyComplaintButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("my_complaint", user.getUsername());
        } catch (IOException e) {
            System.err.println("ไปที่หน้า my_complaint ไม่ได้");
        }


    }
}
