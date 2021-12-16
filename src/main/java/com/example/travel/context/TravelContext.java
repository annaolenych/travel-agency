package com.example.travel.context;

import com.example.travel.model.Travel;

public class TravelContext {

    private static final TravelContext context = new TravelContext();

    public static TravelContext getInstance() {
        return context;
    }

    private Travel travel;

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
