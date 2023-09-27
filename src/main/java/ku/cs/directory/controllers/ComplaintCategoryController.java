package ku.cs.directory.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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

public class ComplaintCategoryController {

    @FXML
    private ListView<ComplaintModel> categoryListView;
    @FXML
    private Label headlineLabel;
    @FXML
    private Label detailLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label scoreLabel;
    private ComplaintModelList complaintModelList;
    private DataSource<ComplaintModelList> complaintModelListDataSource;

    private ComplaintModel complaintModel;

    private DataSource<UserModelList> usersModelListDataSource;

    private UserModelList userModelList;

    private UserModel user;

    private String[] categoryBox = {"Safety", "Service", "Health", "Transport", "Legal"};

    @FXML
    private ChoiceBox<String> categoryChoiceBox;


    public void initialize() {

        complaintModelListDataSource = new ComplainModelListDataSource("data", "Complaint.csv");
        complaintModelList = complaintModelListDataSource.readData();

        usersModelListDataSource = new UsersModelListFileDataSource("data", "Users.csv");
        userModelList = usersModelListDataSource.readData();

        String userUsername = (String) com.github.saacsos.FXRouter.getData();
        user = userModelList.findUser(userUsername);


        showChoiceBox();
        showListView(complaintModelList);
        handleSelectedListView();
    }

    private void showListView(ComplaintModelList complaintModelList) {
        categoryListView.getItems().clear();
        categoryListView.getItems().addAll(complaintModelList.getAllComplaints());
        categoryListView.refresh();
    }

    public void showChoiceBox() {
        categoryChoiceBox.setValue("Please Choose the Status");
        categoryChoiceBox.getItems().addAll(categoryBox);
        categoryChoiceBox.setOnAction(this::categorySelected);
    }

    private void categorySelected(ActionEvent actionEvent) {
        clearSelected();
        String category = categoryChoiceBox.getValue();
        complaintModelList = complaintModelListDataSource.readData();
        ComplaintModelList filtered = complaintModelList.filterBy(new Filterer<ComplaintModel>() {
            @Override
            public boolean filter(ComplaintModel o) {
                return o.getCategory().equals(category);
            }
        });
        showListView(filtered);
    }

    private void handleSelectedListView() {
        categoryListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ComplaintModel>() {
                    @Override
                    public void changed(ObservableValue<? extends ComplaintModel>
                                                observable, ComplaintModel oldValue,
                                        ComplaintModel newValue) {
                        System.out.println(newValue + " is selected");
                        if (newValue != null) {
                            showSelectedCategory(newValue);
                            complaintModel = newValue;
                        }
                    }
                });
    }

    private void showSelectedCategory(ComplaintModel complaint) {
        headlineLabel.setText("Headline: " + complaint.getHeadline());
        detailLabel.setText("Detail: " + complaint.getDetail());
        categoryLabel.setText("Category: " + complaint.getCategory());
        dataLabel.setText("Data: " + complaint.getData());
        scoreLabel.setText("Score: " + complaint.getScore());
    }

    private void clearSelected() {
        headlineLabel.setText("");
        detailLabel.setText("");
        categoryLabel.setText("");
        dataLabel.setText("");
        scoreLabel.setText("");
    }

    @FXML
    public void handleAddScoreButton(ActionEvent actionEvent) {
        complaintModel.addScore();
        complaintModelListDataSource.writeData(complaintModelList);
        categoryListView.refresh();

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
