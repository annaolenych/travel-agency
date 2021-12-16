package com.example.travel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainWindowController {

    @FXML
    private Button searchButton, bookButton, modifyButton, deleteButton;

    @FXML
    private Pane searchPane, bookPane, modifyPane, deletePane;

    public void handleButtonAction(ActionEvent event) {

       if (event.getSource() == searchButton) {
            searchPane.toFront();
       } else if (event.getSource() == bookButton) {
            bookPane.toFront();
       } else if (event.getSource() == modifyButton) {
            modifyPane.toFront();
       } else if (event.getSource() == deleteButton) {
            deletePane.toFront();
       }

    }

}
