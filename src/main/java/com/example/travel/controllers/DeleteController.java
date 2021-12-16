package com.example.travel.controllers;

import com.example.travel.dao.TravelDAO;
import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.Travel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.jensd.fx.glyphs.GlyphIcon;

import static com.example.travel.database.schema.tables.Country.COUNTRY;
import static com.example.travel.database.schema.tables.Customer.CUSTOMER;
import static com.example.travel.database.schema.tables.Hotel.HOTEL;
import static com.example.travel.database.schema.tables.Nutrition.NUTRITION;
import static com.example.travel.database.schema.tables.Transport.TRANSPORT;
import static com.example.travel.database.schema.tables.Travel.TRAVEL;
import static com.example.travel.database.schema.tables.TravelType.TRAVEL_TYPE;

public class DeleteController implements Initializable {

    @FXML
    private TableView<Travel> travelTableView;

    @FXML
    private TableColumn<Travel, Integer> idColumn;

    @FXML
    private TableColumn<Travel, String> firstnameColumn, lastnameColumn, traveltypeColumn;

    @FXML
    private TableColumn<Travel, String> countryColumn, hotelColumn;

    @FXML
    private TableColumn<Travel, LocalDate> arrivalColumn, departureColumn;

    @FXML
    private TableColumn<Travel, String> nutritionColumn, transportColumn;

    @FXML
    private TableColumn<Travel, String> deleteCol;

    private ObservableList<Travel> travelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumns();
        loadData();
    }

    public void initColumns() {
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        traveltypeColumn.setCellValueFactory(new PropertyValueFactory<>("travelType"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        nutritionColumn.setCellValueFactory(new PropertyValueFactory<>("nutrition"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        
    }

    public void loadData() {
        travelObservableList = TravelDAO.getObservableList();
        travelTableView.setItems(travelObservableList);
    }

    public void refresh() {
        travelObservableList.clear();
        loadData();
    }

}


