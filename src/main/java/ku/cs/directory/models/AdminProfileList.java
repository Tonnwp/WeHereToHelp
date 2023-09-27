package ku.cs.directory.models;

import java.util.ArrayList;

public class AdminProfileList {
    private ArrayList<AdminProfile> profiles;

    public AdminProfileList() {

        profiles = new ArrayList<>();
    }

    public void addProfile(AdminProfile profile) {

        profiles.add(profile);
    }

    public ArrayList<AdminProfile> getAllProfiles() {
        return profiles;
    }

}
