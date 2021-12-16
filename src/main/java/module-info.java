module com.example.travel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.jooq;
    requires fontawesomefx;


    opens com.example.travel to javafx.fxml;
    exports com.example.travel;
    exports com.example.travel.controllers;
    exports com.example.travel.database.schema;
    exports com.example.travel.database.config;
    exports com.example.travel.database.schema.tables.records;

    opens com.example.travel.controllers to javafx.fxml, com.jfoenix.fontawesomefx;
    opens com.example.travel.database.schema to org.jooq;
    opens com.example.travel.model to javafx.base;
    exports com.example.travel.database.schema.tables to org.jooq;
}