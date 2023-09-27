package ku.cs.directory.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.directory.models.*;
import ku.cs.directory.services.ComplainModelListDataSource;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.Filterer;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.IOException;

public class OfficerOnlyComplaintController {


    @FXML
    private Label categoryLabel;

    @FXML
    private ListView<ComplaintModel> officerCategoryListView;

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

    @FXML
    private Button progressingButton;

    @FXML
    private TextField replyTextField;

    @FXML
    private Button completedButton;

    private ComplaintModelList complaintModelList; // all data for saving

    private DataSource<ComplaintModelList> complaintModelListDataSource;

    private ComplaintModel complaintModel;

    private DataSource<UserModelList> usersModelListDataSource;

    private UserModelList userModelList;

    private OfficerModel userModel;

    private String[] statusBox = {"Completed", "Progressing", "Not Complete"};

    public void initialize() {

        complaintModelListDataSource = new ComplainModelListDataSource("data", "Complaint.csv");
        complaintModelList = complaintModelListDataSource.readData();

        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        String username = (String) com.github.saacsos.FXRouter.getData();
        userModel = (OfficerModel) userModelList.findUser(username);

        sortCategoryList();
        showChoiceBox();
        handleSelectedListView();
    }

    private void showListView(ComplaintModelList complaintModelList) {
        officerCategoryListView.getItems().clear();
        officerCategoryListView.getItems().addAll(complaintModelList.getAllComplaints());
        officerCategoryListView.refresh();
    }

    public void showChoiceBox() {
        statusChoiceBox.setValue("Please Choose the Status");
        statusChoiceBox.getItems().addAll(statusBox);
        statusChoiceBox.setOnAction(this::statusSelected);
    }

    public void sortCategoryList() {
        String category = userModel.getAgency();

        showListView(complaintModelList.byCategory(category));
    }

    private void statusSelected(ActionEvent actionEvent) {
        clearSelected();
        String status = statusChoiceBox.getValue();

        String category = userModel.getAgency();
        ComplaintModelList filtered = complaintModelList.byCategory(category).filterBy(new Filterer<ComplaintModel>() {
            @Override
            public boolean filter(ComplaintModel o) {
                return o.getStatus().equals(status);
            }
        });
        showListView(filtered);
    }

    private void handleSelectedListView() {
        officerCategoryListView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<ComplaintModel>() {
                @Override
                public void changed(ObservableValue<? extends ComplaintModel>
                                            observable, ComplaintModel oldValue,
                                    ComplaintModel newValue) {
                    System.out.println(newValue + " is selected");
                    if (newValue != null) {
                        showSelected(newValue);
                        complaintModel = newValue;
                    }
                }
            });
    }

    private void showSelected(ComplaintModel complaint) {
        headlineLabel.setText("Headline: " + complaint.getHeadline());
        detailLabel.setText("Detail: " + complaint.getDetail());
        categoryLabel.setText("Category: " + complaint.getCategory());
        dataLabel.setText(complaint.getData());
        statusLabel.setText("Status: " + complaint.getStatus());
    }

    private void clearSelected() {
        headlineLabel.setText("");
        detailLabel.setText("");
        categoryLabel.setText("");
        dataLabel.setText("");
        statusLabel.setText("");
    }

    @FXML
    public void handleCompletedButton(ActionEvent event) {
        complaintModel.setStatus("Completed");
        complaintModelListDataSource.writeData(complaintModelList);
        officerCategoryListView.refresh();
        officerCategoryListView.getItems().clear();
    }

    @FXML
    public void handleProgressingButton(ActionEvent event) {
        complaintModel.setStatus("Progressing");
        complaintModelListDataSource.writeData(complaintModelList);
        officerCategoryListView.refresh();
        officerCategoryListView.getItems().clear();
    }

    @FXML
    public void handleReplyButton(ActionEvent event) {
        String reply = replyTextField.getText();
        String replyName = userModel.getUsername();
        complaintModel.setReply(reply);
        complaintModel.setReplyName(replyName);
        complaintModelListDataSource.writeData(complaintModelList);
        replyTextField.clear();
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
