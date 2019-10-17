package com.example.firebaseauth;

import com.google.gson.annotations.SerializedName;

public class Panel {
    private String title;
    private int year;
    private float rating;
    @SerializedName("description")
    private String text;

    private String panelType;
    private String power;
    private String capacity;
    private String usagePeriod;
    private String address;

    public Panel(String panelType, String power, String capacity, String usagePeriod, String address) {
        this.panelType = panelType;
        this.power = power;
        this.capacity = capacity;
        this.usagePeriod = usagePeriod;
        this.address = address;
    }

    String getPanelType() {
        return panelType;
    }

    String getPower() {
        return power;
    }

    String getCapacity() {
        return capacity;
    }

    String getUsagePeriod() {
        return usagePeriod;
    }

    String getAddress() {
        return address;
    }


/*    Panel(String title, int year, float rating){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.text = text;
    }

    String getTitle() {
        return title;
    }

    int getYear() {
        return year;
    }

    public float getRating() {
        return rating;
    }

    String getText() {
        return text;
    }*/
}
