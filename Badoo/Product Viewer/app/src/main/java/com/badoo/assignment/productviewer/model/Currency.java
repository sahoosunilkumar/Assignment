package com.badoo.assignment.productviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("to")
    @Expose
    private String to;

    /**
     * @return The from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from The from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return The rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate The rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return The to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to The to
     */
    public void setTo(String to) {
        this.to = to;
    }

}
