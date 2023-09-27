package ku.cs.directory.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserModel {
    private String username;
    private String password;
    private String displayName;

    private String imagePath;

    private String date;


    public UserModel(String username, String password, String displayName, String imagePath) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.imagePath = imagePath;
    }

    public UserModel(String username, String password, String displayName) {
        this(username, password, displayName, "images/profile.png");
    }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }


    public void stampTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", displayName='" + displayName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", time='" + getDate() + '\'' +
                '}';
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String toCSV() {
        return "user" + "," + username + "," + password + "," + displayName + "," + imagePath + "," + getDate();
    }

}
