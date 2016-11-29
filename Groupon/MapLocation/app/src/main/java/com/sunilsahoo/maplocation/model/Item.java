package com.sunilsahoo.maplocation.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sunilkumarsahoo on 10/10/16.
 */
public abstract class Item {

    private String id;

    private LatLng latLng;

    private String description;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public LatLng getLatLong() {
        return latLng;
    }

    public void setLatLong(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
