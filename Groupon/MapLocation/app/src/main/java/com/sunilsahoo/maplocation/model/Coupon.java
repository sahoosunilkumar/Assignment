package com.sunilsahoo.maplocation.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sunilkumarsahoo on 10/10/16.
 */
public class Coupon extends Item{
    private String tag;
    private String value;
    private String couponCode;
    private String endDate;

    public Coupon(String id, String title, String tag, String value, String description, String couponCode, String endDate, LatLng latlong){
        this.tag = tag;
        this.value = value;
        this.couponCode = couponCode;
        this.endDate = endDate;
        setLatLong(latlong);
        setId(id);
        setDescription(description);
        setTitle(title);
    }


    public String getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }



    public String getCouponCode() {
        return couponCode;
    }


    public String getEndDate() {
        return endDate;
    }

}
