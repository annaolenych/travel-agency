package com.example.travel.model;

import java.sql.Date;
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
}
