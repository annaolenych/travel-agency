package com.example.travel.database.config;


import com.example.travel.model.UserAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static UserAccount user;

    public Connection connection;

   // private static final String URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String URL = "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4458432";
  //  private static final String USER = "root";
    private static final String USER = "sql4458432";
  //  private static final String PASSWORD = "root";
    private static final String PASSWORD = "7uCCB9zwEM";

    public Connection getConnection() {

        try  {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return connection;
    }

    public static void initUserAccount(String username, String password) {
       user = new UserAccount(username, password);
    }




}
