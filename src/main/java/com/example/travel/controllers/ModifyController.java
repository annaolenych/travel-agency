package com.example.travel.controllers;

import com.example.travel.Application;
import com.example.travel.context.TravelContext;
import com.example.travel.dao.TravelDAO;
import com.example.travel.model.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyController implements Initializable {

    public static String parameters;

    @FXML
    private TableView<Travel> travelTableView;

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

    @FXML
    private Button refreshButton;

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
                        Button updateButton = new Button("update");

                        updateButton.setOnMouseClicked((MouseEvent event) -> {
                            Travel travel = travelTableView.getItems().get(getIndex());
                            FXMLLoader loader = new FXMLLoader();
                            TravelContext.getInstance().setTravel(travel);

                            loader.setLocation(Application.class.getResource("view/editTravel.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                                e.getCause();
                            }

                            EditTravelController editTravelController = loader.getController();
                            editTravelController.setTextFields(travel);

                            Parent root = loader.getRoot();
                            Stage registerStage = new Stage();
                            Scene scene = new Scene(root, 913, 436);
                            scene.getStylesheets().add(Application.class.getResource("style/style.css").toExternalForm());
                            registerStage.setScene(scene);
                            registerStage.show();

                        });

                        HBox managebtn = new HBox(updateButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(updateButton, new Insets(2, 2, 0, 3));

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

    public void refreshImageViewOnAction() {
        travelObservableList.clear();
        loadData();
    }
}
