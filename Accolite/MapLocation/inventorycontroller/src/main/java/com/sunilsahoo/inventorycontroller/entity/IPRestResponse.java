
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPRestResponse {

    @SerializedName("result")
    @Expose
    private IPDetail result;

    /**
     * 
     * @return
     *     The result
     */
    public IPDetail getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(IPDetail result) {
        this.result = result;
    }

}
