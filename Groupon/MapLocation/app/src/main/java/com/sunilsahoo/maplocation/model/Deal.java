package com.sunilsahoo.maplocation.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sunilkumarsahoo on 10/10/16.
 */
public class Deal extends Item{
    private String name;
    private String tag;
    private String value;
    private String endDate;

    public Deal(String id, String title, String tag, String value, String description, String endDate, LatLng latlong){
        this.tag = tag;
        this.value = value;
        this.endDate = endDate;
        setLatLong(latlong);
        setId(id);
        setDescription(description);
        setTitle(title);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }



    public String getEndDate() {
        return endDate;
    }

}
