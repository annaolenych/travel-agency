package com.example.travel.database.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {

        try  {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return connection;
    }


}
