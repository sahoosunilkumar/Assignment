
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchLocation {

    @SerializedName("RestResponse")
    @Expose
    private SearchLocationResponse restResponse;

    /**
     * 
     * @return
     *     The restResponse
     */
    public SearchLocationResponse getRestResponse() {
        return restResponse;
    }

    /**
     * 
     * @param restResponse
     *     The RestResponse
     */
    public void setRestResponse(SearchLocationResponse restResponse) {
        this.restResponse = restResponse;
    }

}
