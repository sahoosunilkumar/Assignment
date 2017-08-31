package com.sunilsahoo.game.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameDataResponse {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public GameDataResponse() {
    }

    /**
     * @param response
     * @param data
     * @param currency
     */
    public GameDataResponse(String response, String currency, List<Datum>
            data) {
        super();
        this.response = response;
        this.currency = currency;
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
