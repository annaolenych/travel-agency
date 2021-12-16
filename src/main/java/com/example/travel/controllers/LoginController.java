package com.example.travel.controllers;

import com.example.travel.Application;
import com.example.travel.database.config.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static com.example.travel.database.schema.tables.UserAccount.USER_ACCOUNT;

public class LoginController {

    @FXML
    private Button cancelButton, loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpLabel;

    public void loginButtonOnAction(ActionEvent event) {

        if (!usernameTextField.getText().isBlank() || !passwordField.getText().isBlank()) {
            loginMessageLabel.setText("Waiting...");
            loginMessageLabel.setStyle("-fx-text-fill: black");

            if(isLoginValid(usernameTextField.getText(), passwordField.getText())) {
                loginMessageLabel.setText("Connected!");
                loginMessageLabel.setStyle("-fx-text-fill: green");

                DatabaseConnection.initUserAccount(usernameTextField.getText(), passwordField.getText());

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                try {
                    Parent root = FXMLLoader.load(Application.class.getResource("view/mainWindow.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root, 1000, 435);
                    scene.getStylesheets().add(Application.class.getResource("style/style.css").toExternalForm());
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                loginMessageLabel.setText("Invalid username or password. Please try again!");
                loginMessageLabel.setStyle("-fx-text-fill: red");
            }
        } else {
            loginMessageLabel.setText("Please enter username and password...");
        }

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean isLoginValid(String username, String password) {

        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection()){
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            result = context.select().
                    from(USER_ACCOUNT).
                    where(USER_ACCOUNT.USERNAME.eq(username).and(USER_ACCOUNT.PASSWORD.eq(password)))
                    .execute();

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }


        return result == 1 ? true : false;
    }

    public void signUpLabelOnAction() {

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root, 520, 570);
            scene.getStylesheets().add(Application.class.getResource("style/style.css").toExternalForm());
            registerStage.setScene(scene);
            registerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
