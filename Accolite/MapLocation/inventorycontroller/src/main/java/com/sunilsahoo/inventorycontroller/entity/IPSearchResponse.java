
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPSearchResponse {

    @SerializedName("RestResponse")
    @Expose
    private IPRestResponse restResponse;

    /**
     * 
     * @return
     *     The restResponse
     */
    public IPRestResponse getRestResponse() {
        return restResponse;
    }

    /**
     * 
     * @param restResponse
     *     The RestResponse
     */
    public void setRestResponse(IPRestResponse restResponse) {
        this.restResponse = restResponse;
    }

}
