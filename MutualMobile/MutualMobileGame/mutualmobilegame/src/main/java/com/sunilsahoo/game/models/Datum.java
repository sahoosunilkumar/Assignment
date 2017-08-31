package com.sunilsahoo.game.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jackpot")
    @Expose
    private long jackpot;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param name
     * @param jackpot
     * @param date
     */
    public Datum(String name, long jackpot, String date) {
        super();
        this.name = name;
        this.jackpot = jackpot;
        this.date = date;
    }

    public Datum(Parcel in) {
        // the order needs to be the same as in writeToParcel() method
        this.name = in.readString();
        this.jackpot = in.readLong();
        this.date = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getJackpot() {
        return jackpot;
    }

    public void setJackpot(long jackpot) {
        this.jackpot = jackpot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.jackpot);
        dest.writeString(this.date);
    }
}
