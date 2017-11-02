package com.affinitas.profilematcher.search.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    private String name;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lon")
    private Double lon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return name;
    }
}
