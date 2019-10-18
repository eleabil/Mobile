package com.example.firebaseauth;

public class Panel {

    private String panelType;
    private String power;
    private String capacity;
    private String usagePeriod;
    private String address;
    private String photoUrl;

    public Panel(String panelType, String power, String capacity, String usagePeriod, String address, String photoUrl) {
        this.panelType = panelType;
        this.power = power;
        this.capacity = capacity;
        this.usagePeriod = usagePeriod;
        this.address = address;
        this.photoUrl = photoUrl;
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

    String getPhotoUrl() {
        return photoUrl;
    }
}