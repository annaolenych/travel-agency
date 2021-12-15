package com.example.travel.model;

public class TravelType {

    private Integer travelTypeID;
    private String name;

    public TravelType(Integer travelTypeID, String name) {
        this.travelTypeID = travelTypeID;
        this.name = name;
    }

    public Integer getTravelTypeID() {
        return travelTypeID;
    }

    public void setTravelTypeID(Integer travelTypeID) {
        this.travelTypeID = travelTypeID;
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
