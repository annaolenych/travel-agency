package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.CountryRecord;
import com.example.travel.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.example.travel.database.schema.tables.Country.COUNTRY;

public class CountryDAO {

    public static ObservableList<Country> getObservableList() {

        Connection connection = new DatabaseConnection().getConnection();

        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Result<CountryRecord> result = context.selectFrom(COUNTRY)
                .fetch();

        List<Country> list = new ArrayList<>();
        for (CountryRecord record : result) {
            list.add(new Country(record.getValue(COUNTRY.COUNTRY_ID), record.getValue(COUNTRY.NAME)));
        }

        ObservableList<Country> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

}
