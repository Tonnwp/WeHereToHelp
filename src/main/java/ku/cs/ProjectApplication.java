package ku.cs;

import javafx.application.Application;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        com.github.saacsos.FXRouter.bind(this, stage, "We Here To Help", 800, 600);
        configRoute();
        FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr + "login.fxml");
        FXRouter.when("user_register",packageStr + "user_register.fxml");
        FXRouter.when("home",packageStr + "home.fxml");
        FXRouter.when("change_password", packageStr + "change_password.fxml");
        FXRouter.when("admin_profile", packageStr + "admin_profile.fxml");
        FXRouter.when("complaint",packageStr + "complaint.fxml");
        FXRouter.when("officer_register",packageStr + "officer_register.fxml");
        FXRouter.when("profile",packageStr + "profile.fxml");
        FXRouter.when("complaint_status", packageStr + "complaint_status.fxml");
        FXRouter.when("complaint_category", packageStr + "complaint_category.fxml");
        FXRouter.when("my_complaint", packageStr + "my_complaint.fxml");
        FXRouter.when("officer_only_complaint", packageStr + "officer_only_complaint.fxml");
        FXRouter.when("guide_for_user", packageStr + "guide_for_user.fxml");


    }

    public static void main(String[] args) {
        launch();
    }
}

