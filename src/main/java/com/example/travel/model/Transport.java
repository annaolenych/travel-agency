package com.example.travel.model;

public class Transport {

    private Integer TransportID;
    private String name;

    public Transport(Integer transportID, String name) {
        TransportID = transportID;
        this.name = name;
    }

    public Integer getTransportID() {
        return TransportID;
    }

    public void setTransportID(Integer transportID) {
        TransportID = transportID;
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
