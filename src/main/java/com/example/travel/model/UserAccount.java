package com.example.travel.model;

public class UserAccount {

    private Integer userID;
    private String username;
    private String password;

    public UserAccount(Integer userID, String login, String password) {
        this.userID = userID;
        this.username = login;
        this.password = password;
    }

    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserID() {
        return userID;
    }

}
