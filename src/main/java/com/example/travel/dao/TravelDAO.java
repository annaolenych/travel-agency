package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.travel.database.schema.tables.Country.COUNTRY;
import static com.example.travel.database.schema.tables.Customer.CUSTOMER;
import static com.example.travel.database.schema.tables.Hotel.HOTEL;
import static com.example.travel.database.schema.tables.Nutrition.NUTRITION;
import static com.example.travel.database.schema.tables.Transport.TRANSPORT;
import static com.example.travel.database.schema.tables.Travel.TRAVEL;
import static com.example.travel.database.schema.tables.TravelType.TRAVEL_TYPE;


public class TravelDAO {

    public static ObservableList<Travel> getObservableList() {
        Connection connection = DatabaseConnection.getConnection();

        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Result<?> result = context.
                select(TRAVEL.TRAVEL_ID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME,
                        TRAVEL_TYPE.NAME, COUNTRY.NAME, HOTEL.NAME, NUTRITION.NAME, TRANSPORT.NAME, TRAVEL.ARRIVAL, TRAVEL.DEPARTURE)
                .from(TRAVEL, CUSTOMER, TRAVEL_TYPE, COUNTRY, HOTEL, NUTRITION, TRANSPORT)
                .where(TRAVEL.USER_ID.eq(new DatabaseConnection().user.getUserID()).
                        and(TRAVEL.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                        .and(TRAVEL.TYPE_ID.eq(TRAVEL_TYPE.TYPE_ID))
                        .and(TRAVEL.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID))
                        .and(TRAVEL.HOTEL_ID.eq(HOTEL.HOTEL_ID))
                        .and(TRAVEL.NUTRITION_ID.eq(NUTRITION.NUTRITION_ID))
                        .and(TRAVEL.TRANSPORT_ID.eq(TRANSPORT.TRANSPORT_ID)))
                .orderBy(TRAVEL.TRAVEL_ID)
                .fetch();

        List<Travel> list = new ArrayList<>();
        for (Record record: result) {

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
            list.add(travel);
        }

        ObservableList<Travel> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

}
