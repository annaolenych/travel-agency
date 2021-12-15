package com.example.travel.database.config;


import com.example.travel.database.schema.tables.records.UserAccountRecord;
import com.example.travel.model.UserAccount;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.travel.database.schema.tables.UserAccount.USER_ACCOUNT;

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

        Connection connection = new DatabaseConnection().getConnection();
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

        Result<UserAccountRecord> result = context.selectFrom(USER_ACCOUNT)
                .where(USER_ACCOUNT.USERNAME.eq(username).and(USER_ACCOUNT.PASSWORD.eq(password)))
                .fetch();

        user = new UserAccount(result.get(0).getValue(USER_ACCOUNT.ACCOUNT_ID), username, password);
    }




}
