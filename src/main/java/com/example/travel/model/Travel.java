package com.example.travel.model;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class Travel {

    private Integer travelID;

    private String firstname;
    private String lastname;

    private String travelType;
    private String country;
    private String hotel;

    private String nutrition;
    private String transport;

    private LocalDate arrival;
    private LocalDate departure;

    private Button delete;
    private Button update;

    public Travel(Integer travelID, String firstname, String lastname, String travelType, String country, String hotel, String nutrition, String transport, LocalDate arrival, LocalDate departure) {
        this.travelID = travelID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.travelType = travelType;
        this.country = country;
        this.hotel = hotel;
        this.nutrition = nutrition;
        this.transport = transport;
        this.arrival = arrival;
        this.departure = departure;

        this.delete = new Button("delete");
        this.update = new Button("update");

//        delete.setStyle(
//                " -fx-cursor: hand ;"
//                        + "-glyph-size:28px;"
//                        + "-fx-fill:#ff1744;"
//        );
//        update.setStyle(
//                " -fx-cursor: hand ;"
//                        + "-glyph-size:28px;"
//                        + "-fx-fill:#00E676;"
//        );
    }

    public Integer getTravelID() {
        return travelID;
    }

    public void setTravelID(Integer travelID) {
        this.travelID = travelID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }
}
