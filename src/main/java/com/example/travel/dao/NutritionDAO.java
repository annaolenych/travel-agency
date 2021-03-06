package com.example.travel.dao;

import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.database.schema.tables.records.NutritionRecord;
import com.example.travel.model.Nutrition;
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

import static com.example.travel.database.schema.tables.Nutrition.NUTRITION;

public class NutritionDAO {

    public static ObservableList<Nutrition> getObservableList() {

        ObservableList<Nutrition> observableList= null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            Result<NutritionRecord> result = context.selectFrom(NUTRITION)
                    .fetch();

            List<Nutrition> list = new ArrayList<>();
            for (NutritionRecord record : result) {
                list.add(new Nutrition(record.getValue(NUTRITION.NUTRITION_ID), record.getValue(NUTRITION.NAME)));
            }

            observableList = FXCollections.observableArrayList(list);

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return observableList;

    }

}
