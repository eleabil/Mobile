package com.example.firebaseauth;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private String title;
    private int year;
    private float rating;
    @SerializedName("description")
    private String text;

    Movie(String title, int year, float rating){
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
    }
}
