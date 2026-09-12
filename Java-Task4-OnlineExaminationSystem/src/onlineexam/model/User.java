package onlineexam.model;

public class User {

    private int userId;
    private String username;
    private String password;
    private String fullName;

    public User(int userId, String username, String password, String fullName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}