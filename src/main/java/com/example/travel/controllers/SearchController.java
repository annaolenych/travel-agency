package com.example.travel.controllers;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.travel.database.schema.tables.Country.COUNTRY;
import static com.example.travel.database.schema.tables.Customer.CUSTOMER;
import static com.example.travel.database.schema.tables.Hotel.HOTEL;
import static com.example.travel.database.schema.tables.Nutrition.NUTRITION;
import static com.example.travel.database.schema.tables.Transport.TRANSPORT;
import static com.example.travel.database.schema.tables.Travel.TRAVEL;
import static com.example.travel.database.schema.tables.TravelType.TRAVEL_TYPE;
import static com.example.travel.database.schema.tables.UserAccount.USER_ACCOUNT;

public class SearchController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<Travel> travelTableView;

    @FXML
    private TableColumn<Travel, Integer> idColumn;

    @FXML
    private TableColumn<Travel, String> firstnameColumn;

    @FXML
    private TableColumn<Travel, String> lastnameColumn;

    @FXML
    private TableColumn<Travel, String> traveltypeColumn;

    @FXML
    private TableColumn<Travel, String> countryColumn;

    @FXML
    private TableColumn<Travel, String> hotelColumn;

    @FXML
    private TableColumn<Travel, LocalDate> arrivalColumn;

    @FXML
    private TableColumn<Travel, LocalDate> departureColumn;

    @FXML
    private TableColumn<Travel, String> nutritionColumn;

    @FXML
    private TableColumn<Travel, String> transportColumn;

    ObservableList<Travel> travelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connection = new DatabaseConnection().getConnection();

        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Result<?> select = context.
                select(TRAVEL.TRAVEL_ID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME,
                        TRAVEL_TYPE.NAME, COUNTRY.NAME, HOTEL.NAME, NUTRITION.NAME, TRANSPORT.NAME, TRAVEL.ARRIVAL, TRAVEL.DEPARTURE)
                .from(TRAVEL)
                .innerJoin(CUSTOMER)
                .on(CUSTOMER.CUSTOMER_ID.eq(TRAVEL.CUSTOMER_ID))
                .innerJoin(TRAVEL_TYPE)
                .on(TRAVEL_TYPE.TYPE_ID.eq(TRAVEL.TRAVEL_ID))
                .innerJoin(COUNTRY)
                .on(COUNTRY.COUNTRY_ID.eq(TRAVEL.COUNTRY_ID))
                .innerJoin(HOTEL)
                .on(HOTEL.HOTEL_ID.eq(TRAVEL.HOTEL_ID))
                .innerJoin(NUTRITION)
                .on(NUTRITION.NUTRITION_ID.eq(TRAVEL.NUTRITION_ID))
                .innerJoin(TRANSPORT)
                .on(TRANSPORT.TRANSPORT_ID.eq(TRAVEL.TRANSPORT_ID))
                .innerJoin(USER_ACCOUNT)
                .on(USER_ACCOUNT.ACCOUNT_ID.eq(TRAVEL.USER_ID))
                .where(USER_ACCOUNT.USERNAME.equal(DatabaseConnection.user.getLogin()))
                .fetch();

        for (Record record: select) {

            Integer travelID = record.getValue(TRAVEL.TRAVEL_ID);

            String firstname = record.getValue(CUSTOMER.FIRSTNAME);
            String lastname = record.getValue(CUSTOMER.LASTNAME);

            String travelType = record.getValue(TRAVEL_TYPE.NAME);
            String country = record.getValue(COUNTRY.NAME);
            String hotel = record.getValue(HOTEL.NAME);

            String nutrition = record.getValue(NUTRITION.NAME);
            String transport = record.getValue(TRANSPORT.NAME);

            LocalDate arrival = record.getValue(TRAVEL.ARRIVAL);
            LocalDate departure = record.getValue(TRAVEL.DEPARTURE);

            Travel travel = new Travel(travelID, firstname, lastname, travelType, country, hotel, nutrition, transport, arrival, departure);
            travelObservableList.add(travel);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("travelID"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        traveltypeColumn.setCellValueFactory(new PropertyValueFactory<>("travelType"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        nutritionColumn.setCellValueFactory(new PropertyValueFactory<>("nutrition"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        travelTableView.setItems(travelObservableList);

        FilteredList<Travel> filteredList = new FilteredList<>(travelObservableList, b -> true);

        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(travel -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null)
                    return true;

                String searchKeyword = newValue.toLowerCase();

                if (travel.getFirstname().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getLastname().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getTravelType().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getCountry().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getHotel().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getNutrition().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getTransport().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getArrival().toString().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getDeparture().toString().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Travel> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(travelTableView.comparatorProperty());
        travelTableView.setItems(sortedList);


    }
}
