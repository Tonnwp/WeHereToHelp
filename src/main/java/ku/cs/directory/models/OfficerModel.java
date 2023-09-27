package ku.cs.directory.models;

public class OfficerModel extends UserModel {

    private String agency;

    public OfficerModel(String username, String password, String displayName, String agency) {
        super(username, password, displayName, "images/profile.png");
        this.agency = agency;
    }

    public OfficerModel(String username, String password, String displayName, String imagePath, String agency) {
        super(username, password, displayName, imagePath);
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public String toCSV() {
        return "officer" + "," + getUsername() + "," + getPassword() + ","
                + getDisplayName() + "," + getImagePath() + "," + getDate() + "," + getAgency();
    }
}
