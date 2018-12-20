package com.example.gal.shotafim;

import java.util.Date;

public class User {

    private String userID;
    private String Name;
    private String Email;
    private String Password;//HashedPassword
    private Date Date;
    private double Credit;

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Date=" + Date +
                ", Credit=" + Credit +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }

    public User(String userID, String name, String email, String password) {
        this.userID = userID;
        Name = name;
        Email = email;
        Password = password;
        Date = new Date();
        Credit = 0;
    }
    public User (){
    }
}
