package com.example.travel.controllers;

import com.example.travel.Application;
import com.example.travel.dao.*;
import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.*;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.travel.database.schema.tables.Travel.TRAVEL;


public class BookController implements Initializable {

    @FXML
    private ComboBox countryComboBox, hotelComboBox, nutritionComboBox, travelTypeComboBox, transportComboBox, customerComboBox;

    @FXML
    private Button customerButton, submitButton;

    private ObservableList<Country> countryObservableList;

    private Map<Integer, ObservableList<Hotel>> hotels = new HashMap<>();

    @FXML
    private DatePicker arrivalDatePicker, departureDatePicker;

    @FXML
    private ImageView failedImageView, successfulImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        failedImageView.setVisible(false);
        successfulImageView.setVisible(false);

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

        Country country = (Country) countryComboBox.getSelectionModel().getSelectedItem();
        TravelType travelType = (TravelType)  travelTypeComboBox.getSelectionModel().getSelectedItem();
        Hotel hotel = (Hotel) hotelComboBox.getSelectionModel().getSelectedItem();
        Nutrition nutrition = (Nutrition) nutritionComboBox.getSelectionModel().getSelectedItem();
        Transport transport = (Transport) transportComboBox.getSelectionModel().getSelectedItem();
        Customer customer = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
        LocalDate arrival = arrivalDatePicker.getValue();
        LocalDate departure = departureDatePicker.getValue();

        if (country == null || travelType == null || hotel == null ||
                nutrition == null || transport == null || customer == null || arrival == null || departure == null) {
            successfulImageView.setVisible(false);
            failedImageView.setVisible(true);
        }
        else {
            try (Connection connection = DatabaseConnection.getConnection()) {
                DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

                context.insertInto(TRAVEL, TRAVEL.USER_ID, TRAVEL.CUSTOMER_ID, TRAVEL.TYPE_ID,
                                TRAVEL.COUNTRY_ID, TRAVEL.HOTEL_ID, TRAVEL.TRANSPORT_ID, TRAVEL.NUTRITION_ID, TRAVEL.ARRIVAL, TRAVEL.DEPARTURE)
                        .values(DatabaseConnection.user.getUserID(), customer.getCustomerID(), travelType.getTravelTypeID(),
                                country.getCountryID(), hotel.getHotelID(), transport.getTransportID(), nutrition.getNutritionID(),
                                arrival, departure)
                        .execute();
                failedImageView.setVisible(false);
                successfulImageView.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
             }
        }
    }


}
