import java.util.Date;

public class User {

    private String userID;
    private String name;
    private String email;
    private String password;//HashedPassword
    private Date date;

    public User(String userID, String name, String sureName, String email, String password) {
        this.userID = userID;//Need to be automatically generate
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", sureName='" + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {

        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }
}
