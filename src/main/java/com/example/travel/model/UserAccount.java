package com.example.travel.model;

public class UserAccount {

    private String username;
    private String password;

    public UserAccount(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
