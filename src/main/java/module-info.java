module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.directory.controllers;
    opens ku.cs.directory.controllers to javafx.fxml;

}