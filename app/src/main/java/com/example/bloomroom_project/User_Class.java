package com.example.bloomroom_project;

public class User_Class {

    private String UserId;
    private String Password;
    private String UserType;

    public User_Class(){}

    public User_Class(String userId, String password, String userType) {

        UserId = userId;
        Password = password;
        UserType = userType;

    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
