
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Measure {

    @SerializedName("wt_or_vol")
    @Expose
    private String wtOrVol;
    @SerializedName("size")
    @Expose
    private Double size;

    public String getWtOrVol() {
        return wtOrVol;
    }

    public void setWtOrVol(String wtOrVol) {
        this.wtOrVol = wtOrVol;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

}
