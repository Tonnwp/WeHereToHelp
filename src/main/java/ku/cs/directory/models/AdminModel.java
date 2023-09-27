package ku.cs.directory.models;

public class AdminModel extends UserModel {
    public AdminModel(String username, String password, String displayName, String imagePath) {
        super(username, password, displayName, imagePath);
    }

    public AdminModel(String username, String password, String displayName) {
        super(username, password, displayName, "images/profile.png");
    }

    public String toCSV() {
        return "admin" + "," + getUsername() + "," + getPassword() + ","
                + getDisplayName() + "," + getImagePath() + "," + getDate() + "," + "Admin";
    }
}
