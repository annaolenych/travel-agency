package com.example.travel.model;

public class Country {

    private Integer countryID;
    private String name;

    public Country(Integer countryID, String name) {
        this.countryID = countryID;
        this.name = name;
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

    @Override
    public String toString() {
        return this.name;
    }
}
