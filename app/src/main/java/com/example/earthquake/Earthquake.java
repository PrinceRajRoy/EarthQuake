package com.example.earthquake;

public class Earthquake {

    private String url;
    private Double magnitude;
    private String place;
    private long date;

    public Earthquake(Double magnitude, String place, long date, String url){
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
        this.url = url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public long getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getUrl() {
        return url;
    }
}
