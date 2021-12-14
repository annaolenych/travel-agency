package com.example.travel;

import com.example.travel.database.config.DatabaseConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static com.example.travel.database.schema.tables.Country.COUNTRY;
import static com.example.travel.database.schema.tables.Hotel.HOTEL;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);   // 520, 400

        scene.getStylesheets().add(this.getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Connection connection = new DatabaseConnection().getConnection();
//        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
//
//        context.insertInto(HOTEL, HOTEL.NAME, HOTEL.COUNTRY_NO)
//                .values("Park Hyatt Sydney", 1)
//                .execute();
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        launch();
    }
}