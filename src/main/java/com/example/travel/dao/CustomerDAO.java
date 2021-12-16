package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.CustomerRecord;
import com.example.travel.database.schema.tables.records.HotelRecord;
import com.example.travel.model.Customer;
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

import static com.example.travel.database.schema.tables.Customer.CUSTOMER;

public class CustomerDAO {

    public static ObservableList<Customer> getObservableList(Integer userID) {

        ObservableList<Customer> observableList = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            List<Customer> hotelList = new ArrayList<>();
            Result<CustomerRecord> result = context.selectFrom(CUSTOMER)
                    .where(CUSTOMER.USER_ID.eq(userID))
                    .fetch();

            for (CustomerRecord record : result) {
                hotelList.add(new Customer(record.getValue(CUSTOMER.CUSTOMER_ID), record.getValue(CUSTOMER.USER_ID),
                        record.getValue(CUSTOMER.FIRSTNAME), record.getValue(CUSTOMER.LASTNAME),
                        record.getValue(CUSTOMER.EMAIL), record.getValue(CUSTOMER.PASSPORTCODE)));
            }

            observableList = FXCollections.observableArrayList(hotelList);

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return observableList;
    }

}
