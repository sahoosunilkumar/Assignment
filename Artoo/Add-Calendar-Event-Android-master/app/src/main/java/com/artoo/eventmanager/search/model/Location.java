package com.artoo.eventmanager.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.artoo.eventmanager.utils.Utility;

/**
 * Created by sunilkumarsahoo on 10/28/17.
 */
public class Location implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private double latitude;
    private double longitude;
    private String address;

    public Location() {

    }

    protected Location(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(address);
    }

    @Override
    public String toString() {
        return Utility.convertToString(this);
    }
}