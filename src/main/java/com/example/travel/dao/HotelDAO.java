package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.HotelRecord;
import com.example.travel.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.travel.database.schema.tables.Hotel.HOTEL;

public class HotelDAO {

    public static ObservableList<Hotel> getObservableList(Integer countryID) {

        ObservableList<Hotel> observableList= null;

        try (Connection connection = DatabaseConnection.getConnection();) {

            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            List<Hotel> hotelList = new ArrayList<>();
            Result<HotelRecord> result = context.selectFrom(HOTEL)
                    .where(HOTEL.COUNTRY_NO.eq(countryID))
                    .fetch();
            for (HotelRecord record : result) {
                Hotel hotel = new Hotel(record.getValue(HOTEL.HOTEL_ID), record.getValue(HOTEL.COUNTRY_NO), record.getValue(HOTEL.NAME), record.getValue(HOTEL.DETAILS));
                hotelList.add(hotel);
            }

             observableList = FXCollections.observableArrayList(hotelList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return observableList;
    }

}
