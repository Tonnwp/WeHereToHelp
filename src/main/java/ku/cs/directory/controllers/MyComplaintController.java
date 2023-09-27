package ku.cs.directory.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.directory.models.ComplaintModel;
import ku.cs.directory.models.ComplaintModelList;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.ComplainModelListDataSource;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.Filterer;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;

public class MyComplaintController {
    private DataSource<ComplaintModelList> complaintModelListDataSource;
    private ComplaintModelList complaintModelList;
    private ComplaintModel complaintModel;
    private DataSource<UserModelList> usersModelListDataSource;
    private UserModelList userModelList;
    private UserModel user;
    @FXML
    private ListView myComplaintListView;
    @FXML
    private Label headLineLabel;

    @FXML
    private Label detailLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label statusLabel;


    public void initialize() {

        complaintModelListDataSource = new ComplainModelListDataSource("data", "Complaint.csv");
        complaintModelList = complaintModelListDataSource.readData();

        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        String userUsername = (String) com.github.saacsos.FXRouter.getData();

        user = userModelList.findUser(userUsername);

        showListView();
        handleSelectedListView();
    }

    private void showListView() {
        complaintModelList = complaintModelListDataSource.readData();
        complaintModelList = complaintModelList.filterBy(new Filterer<ComplaintModel>() {
            @Override
            public boolean filter(ComplaintModel o) {
                return o.getWriteBy().equals(user.getUsername());
            }
        });
        myComplaintListView.getItems().clear();
        myComplaintListView.getItems().addAll(complaintModelList.getAllComplaints());
        myComplaintListView.refresh();
    }

    private void handleSelectedListView() {
        myComplaintListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ComplaintModel>() {
                    @Override
                    public void changed(ObservableValue<? extends ComplaintModel>
                                                observable, ComplaintModel oldValue,
                                        ComplaintModel newValue) {
                        System.out.println(newValue + " is selected");
                        showSelected(newValue);
                        complaintModel = newValue;
                    }
                });
    }


    private void showSelected(ComplaintModel complaint) {
        headLineLabel.setText("Headline: " + complaint.getHeadline());
        detailLabel.setText("Detail: " + complaint.getDetail());
        categoryLabel.setText("Category: " + complaint.getCategory());
        dataLabel.setText("Data: " + complaint.getData());
        statusLabel.setText("Status: " + complaint.getStatus());
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


