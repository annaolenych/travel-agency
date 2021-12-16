package com.example.travel.controllers;

import com.example.travel.dao.TravelDAO;
import com.example.travel.database.config.DatabaseConnection;
import com.example.travel.model.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static com.example.travel.database.schema.tables.Travel.TRAVEL;

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

        Callback<TableColumn<Travel, String>, TableCell<Travel, String>> cellFoctory = (TableColumn<Travel, String> param) -> {
            // make cell containing buttons
            final TableCell<Travel, String> cell = new TableCell<Travel, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button deleteIcon = new Button("delete");

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Travel travel = travelTableView.getSelectionModel().getSelectedItem();

                            Connection connection = DatabaseConnection.getConnection();
                            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

                            context.deleteFrom(TRAVEL)
                                    .where(TRAVEL.TRAVEL_ID.eq(travel.getTravelID()))
                                    .execute();
                            refresh();
                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        deleteCol.setCellFactory(cellFoctory);
        travelTableView.setItems(travelObservableList);
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


