package ku.cs.directory.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import ku.cs.directory.models.*;
import ku.cs.directory.services.ComplainModelListDataSource;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.Filterer;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class ComplaintStatusController {

    @FXML
    private Label categoryLabel;

    @FXML
    private ListView<ComplaintModel> statusListView;

    @FXML
    private Label dataLabel;

    @FXML
    private Label detailLabel;

    @FXML
    private Label headlineLabel;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private Label statusLabel;

    @FXML private Label replyLabel;

    @FXML private ToggleButton dateToggleButton;

    @FXML private ToggleButton scoreToggleButton;

    @FXML private Label officerCategoryLabel;

    @FXML private Label replyNameLabel;


    private ComplaintModelList complaintModelList;

    private DataSource<ComplaintModelList> complaintModelListDataSource;

    private ComplaintModel complaintModel;

    private DataSource<UserModelList> usersModelListDataSource;

    private UserModel user;

    private UserModelList userModelList;

    private String[] statusBox = {"Completed", "Progressing", "Not Complete"};

    public void initialize() {

        complaintModelListDataSource = new ComplainModelListDataSource("data", "Complaint.csv");
        complaintModelList = complaintModelListDataSource.readData();

        usersModelListDataSource = new UsersModelListFileDataSource("data","Users.csv");
        userModelList = usersModelListDataSource.readData();

        String username = (String) com.github.saacsos.FXRouter.getData();
        user = userModelList.findUser(username);


        showChoiceBox();
        showListView();
        handleSelectedListView();
    }

    private void showListView() {
        statusListView.getItems().clear();
        statusListView.getItems().addAll(complaintModelList.getAllComplaints());
        statusListView.refresh();
    }

    public void showChoiceBox(){
        statusChoiceBox.setValue("Please Choose the Status");
        statusChoiceBox.getItems().addAll(statusBox);
        statusChoiceBox.setOnAction(this::statusSelected);
    }

    private void statusSelected(ActionEvent actionEvent) {
        String status = statusChoiceBox.getValue();
        complaintModelList = complaintModelListDataSource.readData();
        complaintModelList = complaintModelList.filterBy(new Filterer<ComplaintModel>() {
            @Override
            public boolean filter(ComplaintModel o) {
                return o.getStatus().equals(status);
            }
        });
        showListView();
    }

    private void handleSelectedListView() {
        statusListView.getSelectionModel().selectedItemProperty().addListener(
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
        headlineLabel.setText("Headline: " + complaint.getHeadline());
        detailLabel.setText("Detail: " + complaint.getDetail());
        categoryLabel.setText("Category: " + complaint.getCategory());
        dataLabel.setText("Data: " + complaint.getData());
        statusLabel.setText("Status: " + complaint.getStatus());
        replyLabel.setText("Staff Reply: " + complaint.getReply());
        officerCategoryLabel.setText("By: " + complaint.getCategory());

        if(user instanceof OfficerModel && complaint.getCategory().equals(((OfficerModel) user).getAgency())){
            replyNameLabel.setText("OfficerReplyName: " + complaint.getReplyName());
        }
        else {
            replyNameLabel.setText("");
        }
    }


    public void sortScore(boolean clicked){
        Collections.sort(complaintModelList.getAllComplaints(), new Comparator<ComplaintModel>() {
            @Override
            public int compare(ComplaintModel o1, ComplaintModel o2) {

                return (o1.getScore() - o2.getScore()) * (clicked ? -1 : 1);
            }
        });
    }

    public void sortDate(boolean clicked){
        Collections.sort(complaintModelList.getAllComplaints(), new Comparator<ComplaintModel>() {
            @Override
            public int compare(ComplaintModel o1, ComplaintModel o2) {
                return o2.getDate().compareTo(o1.getDate())
                        * (clicked ? -1 : 1);
            }
        });
    }

    @FXML
    public void handleDateToggleButton(ActionEvent event){
        sortDate(dateToggleButton.isSelected());
        showListView();
    }

    @FXML
    public void handleScoreToggleButton(ActionEvent event){
        sortScore(scoreToggleButton.isSelected());
        showListView();
    }


    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }

    }

}
