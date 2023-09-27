package ku.cs.directory.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.directory.models.AdminProfile;
import ku.cs.directory.models.AdminProfileList;
import ku.cs.directory.services.AdminProfileHardCodeDataSource;

import java.io.IOException;

public class AdminProfileListController {
    @FXML
    private ListView<AdminProfile> adminListView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label nickNameLabel;

    @FXML
    private ImageView images;

    private AdminProfileHardCodeDataSource dataSource;

    private AdminProfileList profileList;

    @FXML
    public void initialize() {
        dataSource = new AdminProfileHardCodeDataSource();
        profileList = dataSource.getProfileList();
        showListView();
        clearSelectedAdminProfile();
        handleSelectedListView();
    }

    private void showListView() {
        adminListView.getItems().addAll(profileList.getAllProfiles());
        adminListView.refresh();
    }

    private void handleSelectedListView() {
        adminListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<AdminProfile>() {
                    @Override
                    public void changed(ObservableValue<? extends AdminProfile>
                                                observable, AdminProfile oldValue,
                                        AdminProfile newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedAdminProfile(newValue);
                    }
                });
    }

    private void showSelectedAdminProfile(AdminProfile profile) {
        nameLabel.setText("Name: " + profile.getName());
        idLabel.setText("ID: " + profile.getId());
        nickNameLabel.setText("Nickname: " + profile.getNickName());

        if (profile.getName() == "Nawapon Leelanawalikhit") {
            String ton = getClass().getResource("/ku/cs/images/Ton.jpg").toExternalForm();
            images.setImage(new Image(ton));
        } else if (profile.getName() == "Sittichai Panplod") {
            String folk = getClass().getResource("/ku/cs/images/Folk.jpeg").toExternalForm();
            images.setImage(new Image(folk));
        } else if (profile.getName() == "Kanchanok Nanual") {
            String kaohom = getClass().getResource("/ku/cs/images/Kaohom.jpeg").toExternalForm();
            images.setImage(new Image(kaohom));
        } else if (profile.getName() == "Atthakorn Kuasakul") {
            String rom = getClass().getResource("/ku/cs/images/Rom.jpg").toExternalForm();
            images.setImage(new Image(rom));
        }

    }

    private void clearSelectedAdminProfile() {
        nameLabel.setText("");
        idLabel.setText("");
        nickNameLabel.setText("");

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
