package ku.cs.directory.models;

public class AdminProfile {
    private String name;
    private String id;
    private String nickName;

    public AdminProfile(String name, String id, String nickName) {
        this.name = name;
        this.id = id;
        this.nickName = nickName;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return name;
    }
}
