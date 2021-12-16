package com.example.travel.controllers;

import com.example.travel.context.TravelContext;
import com.example.travel.dao.*;
import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.CustomerRecord;
import com.example.travel.database.schema.tables.records.TravelRecord;
import com.example.travel.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.travel.database.schema.tables.Customer.CUSTOMER;
import static com.example.travel.database.schema.tables.Travel.TRAVEL;
import static com.example.travel.database.schema.tables.TravelType.TRAVEL_TYPE;

public class EditTravelController implements Initializable {

    @FXML
    private Button saveButton, cleanButton, cancelButton;

    @FXML
    private TextField firstnameTextField, lastnameTextField, emailTextField, passportCodeTextField;

    @FXML
    private ComboBox countryComboBox;
    @FXML
    private ComboBox travelTypeComboBox;
    @FXML
    private ComboBox hotelComboBox;
    @FXML
    private ComboBox nutritionComboBox;
    @FXML
    private ComboBox transportComboBox;

    @FXML
    private ImageView successfulImageView, failedImageView;

    @FXML
    private DatePicker arrivalDatePicker, departureDatePicker;

    private ObservableList<Country> countryObservableList;
    private ObservableList<Nutrition> nutritionObservableList;
    private ObservableList<Transport> transportObservableList;
    private ObservableList<TravelType> travelTypeObservableList;

    private Map<Integer, ObservableList<Hotel>> hotels = new HashMap<>();
    private Integer travelID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successfulImageView.setVisible(false);
        failedImageView.setVisible(false);

        travelID = TravelContext.getInstance().getTravel().getTravelID();
        System.out.println(travelID);

        countryObservableList = CountryDAO.getObservableList();
        countryComboBox.setItems(countryObservableList);

        for (Country country : countryObservableList) {
            hotels.put(country.getCountryID(), HotelDAO.getObservableList(country.getCountryID()));
        }

        countryComboBox.setOnAction(e -> {
            Country selectedObj = (Country) countryComboBox.getSelectionModel().getSelectedItem();
            hotelComboBox.setItems(hotels.get(selectedObj.getCountryID()));
        });

        nutritionObservableList = NutritionDAO.getObservableList();
        nutritionComboBox.setItems(nutritionObservableList);

        transportObservableList = TransportDAO.getObservableList();
        transportComboBox.setItems(transportObservableList);

        travelTypeObservableList = TravelTypeDAO.getObservableList();
        travelTypeComboBox.setItems(travelTypeObservableList);
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setTextFields(Travel travel) {
        Integer travelID = travel.getTravelID();

        try (Connection connection = DatabaseConnection.getConnection()) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            TravelRecord travelRecord = context.selectFrom(TRAVEL)
                    .where(TRAVEL.TRAVEL_ID.eq(travelID))
                    .fetch()
                    .get(0);

            CustomerRecord customerRecord = context.selectFrom(CUSTOMER)
                    .where(CUSTOMER.CUSTOMER_ID.eq(travelRecord.getValue(TRAVEL.CUSTOMER_ID)))
                    .fetch()
                    .get(0);

            firstnameTextField.setText(customerRecord.getValue(CUSTOMER.FIRSTNAME));
            lastnameTextField.setText(customerRecord.getValue(CUSTOMER.LASTNAME));
            emailTextField.setText(customerRecord.getValue(CUSTOMER.EMAIL));
            passportCodeTextField.setText(customerRecord.getValue(CUSTOMER.PASSPORTCODE));


            arrivalDatePicker.setValue(travel.getArrival());
            departureDatePicker.setValue(travel.getDeparture());


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cleanButtonOnAction(ActionEvent event) {
        firstnameTextField.clear();
        lastnameTextField.clear();
        emailTextField.clear();
        passportCodeTextField.clear();

        countryComboBox.getSelectionModel().clearSelection();
        countryComboBox.setPromptText("Select a country");

        travelTypeComboBox.getSelectionModel().clearSelection();
        travelTypeComboBox.setPromptText("Select a voucher ");

        hotelComboBox.getSelectionModel().clearSelection();
        hotelComboBox.setPromptText("Select a hotel");

        nutritionComboBox.getSelectionModel().clearSelection();
        nutritionComboBox.setPromptText("Select a nutrition");

        transportComboBox.getSelectionModel().clearSelection();
        transportComboBox.setPromptText("Select a transport");

        arrivalDatePicker.getEditor().clear();
        departureDatePicker.getEditor().clear();
    }

    public void saveButtonOnAction(ActionEvent event) {

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String passportCode = passportCodeTextField.getText();

        Country country = (Country) countryComboBox.getSelectionModel().getSelectedItem();

        TravelType travelType = (TravelType)  travelTypeComboBox.getSelectionModel().getSelectedItem();
        Hotel hotel = (Hotel) hotelComboBox.getSelectionModel().getSelectedItem();

        Nutrition nutrition = (Nutrition) nutritionComboBox.getSelectionModel().getSelectedItem();
        Transport transport = (Transport) transportComboBox.getSelectionModel().getSelectedItem();

        LocalDate arrival = arrivalDatePicker.getValue();
        LocalDate departure = departureDatePicker.getValue();

        if (country == null || travelType == null || hotel == null ||
                nutrition == null || transport == null  || arrival == null || departure == null ||
                firstname.isBlank() || lastname.isBlank() || email.isBlank() || passportCode.isBlank()) {
            successfulImageView.setVisible(false);
            failedImageView.setVisible(true);
        } else {
            try (Connection connection = DatabaseConnection.getConnection()) {
                DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

                Integer customer = context.selectFrom(TRAVEL)
                        .where(TRAVEL.TRAVEL_ID.eq(travelID))
                        .fetch().get(0).getValue(TRAVEL.CUSTOMER_ID);

                Integer customerID = context.select(TRAVEL.CUSTOMER_ID)
                                    .from(TRAVEL)
                                    .where(TRAVEL.TRAVEL_ID.eq(travelID))
                                        .fetch().get(0).getValue(TRAVEL.CUSTOMER_ID);

                System.out.println("travel id = " + travelID);
                System.out.println("customer id = " + customerID);

                context.update(CUSTOMER)
                        .set(CUSTOMER.FIRSTNAME, firstname)
                        .set(CUSTOMER.LASTNAME, lastname)
                        .set(CUSTOMER.EMAIL, email)
                        .set(CUSTOMER.PASSPORTCODE, passportCode)
                        .where(CUSTOMER.CUSTOMER_ID.eq(customerID))
                        .execute();

                context.update(TRAVEL)
                        .set(TRAVEL.TYPE_ID, travelType.getTravelTypeID())
                        .set(TRAVEL.COUNTRY_ID, country.getCountryID())
                        .set(TRAVEL.HOTEL_ID, hotel.getHotelID())
                        .set(TRAVEL.TRANSPORT_ID, transport.getTransportID())
                        .set(TRAVEL.NUTRITION_ID, nutrition.getNutritionID())
                        .set(TRAVEL.ARRIVAL, arrival)
                        .set(TRAVEL.DEPARTURE, departure)
                        .where(TRAVEL.TRAVEL_ID.eq(travelID))
                        .execute();

                failedImageView.setVisible(false);
                successfulImageView.setVisible(true);

            } catch (SQLException e) {
                e.printStackTrace();;
                e.getCause();
            }

        }
    }



}
