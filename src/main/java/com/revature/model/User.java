package com.revature.model;

import java.util.Objects;

public class User {


    private int userID;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userRole;
    private String userPassword;

    public User() {
    }

    public User(int userID, String userName, String userFirstName, String userLastName, String userEmail, String userRole, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userPassword = userPassword;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(userName, user.userName) && Objects.equals(userFirstName, user.userFirstName) && Objects.equals(userLastName, user.userLastName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(userRole, user.userRole) && Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, userFirstName, userLastName, userEmail, userRole, userPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
