package com.example.travel.controllers;

import com.example.travel.dao.TravelDAO;
import com.example.travel.model.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class SearchController implements Initializable {

    @FXML
    private ImageView refreshImageView;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<Travel> travelTableView;

    @FXML
    private TableColumn<Travel, Integer> idColumn;

    @FXML
    private TableColumn<Travel, String> firstnameColumn;

    @FXML
    private TableColumn<Travel, String> lastnameColumn;

    @FXML
    private TableColumn<Travel, String> traveltypeColumn;

    @FXML
    private TableColumn<Travel, String> countryColumn;

    @FXML
    private TableColumn<Travel, String> hotelColumn;

    @FXML
    private TableColumn<Travel, LocalDate> arrivalColumn;

    @FXML
    private TableColumn<Travel, LocalDate> departureColumn;

    @FXML
    private TableColumn<Travel, String> nutritionColumn;

    @FXML
    private TableColumn<Travel, String> transportColumn;

    private ObservableList<Travel> travelObservableList = FXCollections.observableArrayList();
    private FilteredList<Travel> filteredList;
    private  SortedList<Travel> sortedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTravelTableView();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("travelID"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        traveltypeColumn.setCellValueFactory(new PropertyValueFactory<>("travelType"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        nutritionColumn.setCellValueFactory(new PropertyValueFactory<>("nutrition"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));

        travelTableView.setItems(travelObservableList);

        filteredList = new FilteredList<>(travelObservableList, b -> true);

        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(travel -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null)
                    return true;

                String searchKeyword = newValue.toLowerCase();

                if (travel.getFirstname().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getLastname().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getTravelType().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getCountry().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getHotel().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getNutrition().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getTransport().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getArrival().toString().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (travel.getDeparture().toString().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }

                return false;
            });
        });

        sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(travelTableView.comparatorProperty());
        travelTableView.setItems(sortedList);
    }

    public void initTravelTableView() {
        travelObservableList = TravelDAO.getObservableList();
        travelTableView.setItems(travelObservableList);
        filteredList = new FilteredList<>(travelObservableList, b -> true);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(travelTableView.comparatorProperty());
        travelTableView.setItems(sortedList);
    }

    public void refreshImageViewOnAction(ActionEvent event) {
        travelObservableList.clear();
        initTravelTableView();
    }

}
