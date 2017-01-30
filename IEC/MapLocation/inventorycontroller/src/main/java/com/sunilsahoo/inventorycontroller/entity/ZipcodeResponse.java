
package com.sunilsahoo.inventorycontroller.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZipcodeResponse {

    @SerializedName("status_code")
    @Expose
    private long statusCode;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("response_charset")
    @Expose
    private String responseCharset;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getResponseCharset() {
        return responseCharset;
    }

    public void setResponseCharset(String responseCharset) {
        this.responseCharset = responseCharset;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
