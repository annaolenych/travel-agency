package com.example.travel.model;

public class Hotel {

    private Integer hotelID;
    private Integer countryID;
    private String name;
    private String details;

    public Hotel(Integer hotelID, Integer countryID, String name, String details) {
        this.hotelID = hotelID;
        this.countryID = countryID;
        this.name = name;
        this.details = details;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
