package com.example.travel.controllers;

import com.example.travel.database.config.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static com.example.travel.database.schema.tables.Customer.CUSTOMER;

public class CustomerAdditionController {

    @FXML
    private Button addButton, cancelButton;

    @FXML
    private TextField firstnameTextField, lastnameTextField, emailTextField, passportCodeTextField;

    @FXML
    private Label profileLabel;

    public void addButtonOnClick(ActionEvent event) {
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String passportCode = passportCodeTextField.getText();

        if (!formIsValid(firstname, lastname, email, passportCode)) {
            profileLabel.setText("Fill the form!");
            profileLabel.setStyle("-fx-text-fill: red");
        } else {
            insertCustomer(firstname, lastname, email, passportCode);
            profileLabel.setText("Successful!");
            profileLabel.setStyle("-fx-text-fill: green");
        }
    }

    private void insertCustomer(String firstname, String lastname, String email, String passportCord) {
        Connection connection = new DatabaseConnection().getConnection();
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

        context.insertInto(CUSTOMER, CUSTOMER.USER_ID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, CUSTOMER.EMAIL, CUSTOMER.PASSPORTCODE)
                .values(DatabaseConnection.user.getUserID(), firstname, lastname, email, passportCord)
                .execute();
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean formIsValid(String firstname, String lastname, String email, String passportCode) {
        return !firstname.isBlank() && !lastname.isBlank() && !email.isBlank()  && !passportCode.isBlank();
    }
}
