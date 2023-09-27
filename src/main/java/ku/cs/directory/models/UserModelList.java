package ku.cs.directory.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserModelList {
    private ArrayList<UserModel> users;

    private UserModel user;

    public UserModelList() {
        // ใช้ new เพื่อสร้าง instance ของ ArrayList
        users = new ArrayList<>();
    }

    public void addUsers(UserModel user) {
        // เรียก method add จาก ArrayList เพื่อเพิ่มข้อมูล
        users.add(user);
    }


    public UserModel findByUsername(String username) {

        for (UserModel user : users) {
            if (user.isUsername(username)) {
                return user;
            }
        }
        return null;
    }

    public UserModel findUser(String username, String password) {
        for (UserModel user : users) {
            if (user.isUsername(username) && user.isPassword(password)) {
                return user;
            }
        }
        return null;
    }

    public UserModel findUser(String username) {
        for (UserModel user : users) {
            if (user.isUsername(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<UserModel> getAllUsers() {

        return users;
    }
}