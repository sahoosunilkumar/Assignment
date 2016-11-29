
package com.myntra.assignment.controller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("m")
    @Expose
    private String m;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Media() {
    }

    /**
     * 
     * @param m
     */
    public Media(String m) {
        this.m = m;
    }

    /**
     * 
     * @return
     *     The m
     */
    public String getM() {
        return m;
    }

    /**
     * 
     * @param m
     *     The m
     */
    public void setM(String m) {
        this.m = m;
    }

}
