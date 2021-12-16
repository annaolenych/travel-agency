package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.TransportRecord;
import com.example.travel.model.Transport;
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

import static com.example.travel.database.schema.tables.Transport.TRANSPORT;

public class TransportDAO {

    public static ObservableList<Transport> getObservableList() {

        ObservableList<Transport> observableList = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            Result<TransportRecord> result = context.selectFrom(TRANSPORT)
                    .fetch();

            List<Transport> list = new ArrayList<>();
            for (TransportRecord record : result) {
                list.add(new Transport(record.getValue(TRANSPORT.TRANSPORT_ID), record.getValue(TRANSPORT.NAME)));
            }

            observableList = FXCollections.observableArrayList(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return observableList;
    }
}
