package ku.cs.directory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import ku.cs.directory.models.AdminModel;
import ku.cs.directory.models.OfficerModel;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;
import ku.cs.directory.services.DataSource;
import ku.cs.directory.services.UsersModelListFileDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ProfileController {

    private UserModelList userModelList;
    private UserModel user;
    private DataSource<UserModelList> usersModelListDataSource;


    @FXML private Label userNameLabel;

    @FXML private ImageView userImageView;

    @FXML private Label displayNameLabel;

    @FXML private AnchorPane imagePane;

    @FXML private Label dateTimeLabel;

    @FXML private Label agencyLabel;



    public void initialize(){
        usersModelListDataSource = new UsersModelListFileDataSource("data","Users.csv");
        userModelList = usersModelListDataSource.readData();

        String userUsername = (String) com.github.saacsos.FXRouter.getData();

        user = userModelList.findUser(userUsername);
        displayNameLabel.setText(String.valueOf(user.getDisplayName()));
        userNameLabel.setText(user.getUsername());

        if(user instanceof OfficerModel){
            agencyLabel.setText("Agency: " + ((OfficerModel)user).getAgency());

        } else if(user instanceof AdminModel){
            agencyLabel.setText("Agency: Admin");
        }
        else{
            agencyLabel.setText("");
        }

        dateTimeLabel.setText(user.getDate());


//          รูปๆ
        File imageFile = new File(user.getImagePath());
        userImageView.setImage(new Image(imageFile.toURI().toString()));

        BorderPane pane = new BorderPane();
        userImageView.setPreserveRatio(true);
        pane.setCenter(userImageView);
        pane.setPrefWidth(200);
        pane.setPrefHeight(200);
        imagePane.getChildren().add(pane);
    }




    @FXML public void handleUploadImageButton(ActionEvent event){
        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                userImageView.setImage(new Image(target.toUri().toString()));
                user.setImagePath(destDir + "/" + filename);
                usersModelListDataSource.writeData(userModelList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleChangePassWordButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("change_password");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
        }
    }



    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }
    }
}
