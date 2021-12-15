package com.example.travel.model;

public class Nutrition {

    private Integer NutritionID;
    private String name;

    public Nutrition(Integer nutritionID, String name) {
        NutritionID = nutritionID;
        this.name = name;
    }

    public Integer getNutritionID() {
        return NutritionID;
    }

    public void setNutritionID(Integer nutritionID) {
        NutritionID = nutritionID;
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
