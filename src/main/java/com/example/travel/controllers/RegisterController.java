package com.example.travel.controllers;

import com.example.travel.database.config.DatabaseConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;

import static com.example.travel.database.schema.tables.UserAccount.USER_ACCOUNT;

public class RegisterController {

    @FXML
    TextField firstnameTextField, lastnameTextField;

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordField, confirmPasswordField;

    @FXML
    Button registerButton;

    @FXML
    Button cancelButton;

    @FXML
    Label registeredLabel, confirmationLabel;

    public void registerButtonOnClick(ActionEvent event) {

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (!formIsValid(firstname, lastname, username, password)) {
            registeredLabel.setText("Fill the form!");
            registeredLabel.setStyle("-fx-text-fill: red");
        } else
            registerUser(firstname, lastname, username, password);

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerUser(String firstname, String lastname, String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()){
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            context.insertInto(USER_ACCOUNT, USER_ACCOUNT.FIRSTNAME, USER_ACCOUNT.LASTNAME, USER_ACCOUNT.USERNAME, USER_ACCOUNT.PASSWORD)
                    .values(firstname, lastname, username, password)
                    .execute();
            registeredLabel.setText("User registered successfully!");
            registeredLabel.setStyle("-fx-text-fill: green");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean formIsValid(String firstname, String lastname, String username, String password) {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            confirmationLabel.setText("You are set!");
            confirmationLabel.setStyle("-fx-text-fill: green");
        } else {
            confirmationLabel.setText("Password does not match!");
            confirmationLabel.setStyle("-fx-text-fill: red");
        }

        return  !firstname.isBlank() && !lastname.isBlank() && !username.isBlank() && !password.isBlank();
    }
}
