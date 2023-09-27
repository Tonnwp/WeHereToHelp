package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import ku.cs.directory.models.ComplaintModel;
import ku.cs.directory.models.ComplaintModelList;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.ComplainModelListDataSource;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComplaintController {

    private DataSource<UserModelList> usersModelListDataSource;

    private UserModelList userModelList;

    private UserModel user;

    private DataSource<ComplaintModelList> complaintModelListDataSource;

    private ComplaintModelList complaintModelList;

    private String date;

    private String[] category = {"Safety", "Service", "Health", "Transport", "Legal"};
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TextField dataTextField;

    @FXML
    private TextField detailTextField;

    @FXML
    private TextField headlineTextField;

    public void initialize() {

        complaintModelListDataSource = new ComplainModelListDataSource("data", "Complaint.csv");
        complaintModelList = complaintModelListDataSource.readData();

        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        String userUsername = (String) com.github.saacsos.FXRouter.getData();

        user = userModelList.findUser(userUsername);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        categoryChoiceBox.setValue("Please Choose Your Category");
        categoryChoiceBox.getItems().addAll(category);
    }

    @FXML
    public void handleAddComplaintButton(ActionEvent actionEvent) {
        String headline = headlineTextField.getText();
        String detail = detailTextField.getText();
        String category = categoryChoiceBox.getValue();
        String data = dataTextField.getText();


        complaintModelList.addComplain(new ComplaintModel(headline, detail, category, data, date, 0, "Not Complete", user.getUsername(), "-", "-"));
        complaintModelListDataSource.writeData(complaintModelList);

        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }
    }
}
