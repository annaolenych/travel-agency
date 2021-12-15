package com.example.travel.controllers;

import com.example.travel.Application;
import com.example.travel.dao.*;
import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.Country;
import com.example.travel.model.Hotel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class BookController implements Initializable {

    @FXML
    private ComboBox countryComboBox, hotelComboBox, nutritionComboBox, travelTypeComboBox, transportComboBox, customerComboBox;

    @FXML
    private Button customerButton, submitButton;

    private ObservableList<Country> countryObservableList;

    private Map<Integer, ObservableList<Hotel>> hotels = new HashMap<>();

    @FXML
    private DatePicker arrivalDatePicker, departureDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryObservableList = CountryDAO.getObservableList();
        countryComboBox.setItems(countryObservableList);
        nutritionComboBox.setItems(NutritionDAO.getObservableList());

        for (Country country : countryObservableList) {
            hotels.put(country.getCountryID(), HotelDAO.getObservableList(country.getCountryID()));
        }

        countryComboBox.setOnAction(e -> {
            Country selectedObj = (Country) countryComboBox.getSelectionModel().getSelectedItem();
            hotelComboBox.setItems(hotels.get(selectedObj.getCountryID()));
        });

        travelTypeComboBox.setItems(TravelTypeDAO.getObservableList());
        transportComboBox.setItems(TransportDAO.getObservableList());
        customerComboBox.setItems(CustomerDAO.getObservableList(DatabaseConnection.user.getUserID()));

    }

    public void customerButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/customerAddition.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root, 520, 490);
            scene.getStylesheets().add(Application.class.getResource("style/style.css").toExternalForm());
            registerStage.setScene(scene);
            registerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitButtonOnAction(ActionEvent event) {
        System.out.println("Hello, world!");
    }



}
