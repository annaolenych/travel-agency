package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.TravelTypeRecord;
import com.example.travel.model.TravelType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.example.travel.database.schema.tables.TravelType.TRAVEL_TYPE;

public class TravelTypeDAO {

    public static ObservableList<TravelType> getObservableList() {

        Connection connection = new DatabaseConnection().getConnection();

        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Result<TravelTypeRecord> result = context.selectFrom(TRAVEL_TYPE)
                .fetch();

        List<TravelType> list = new ArrayList<>();
        for (TravelTypeRecord record : result) {
            list.add(new TravelType(record.getValue(TRAVEL_TYPE.TYPE_ID), record.getValue(TRAVEL_TYPE.NAME)));
        }

        ObservableList<TravelType> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

}
