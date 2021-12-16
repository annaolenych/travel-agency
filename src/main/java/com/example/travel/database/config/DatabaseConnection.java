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

    public static Connection connection;

    private static final String HOST = "sql4.freemysqlhosting.net";
    private static final Integer PORT = 3306;
    private static final String USER = "sql4458432";
    private static final String PASSWORD = "7uCCB9zwEM";
    private static final String DB_NAME = "sql4458432";

    public static Connection getConnection() {

        try  {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USER, PASSWORD);
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
