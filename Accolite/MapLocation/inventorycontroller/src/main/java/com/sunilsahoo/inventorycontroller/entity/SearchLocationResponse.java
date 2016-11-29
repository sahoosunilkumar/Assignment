
package com.sunilsahoo.inventorycontroller.entity;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchLocationResponse {

    @SerializedName("messages")
    @Expose
    private List<String> messages = new ArrayList<String>();
    @SerializedName("result")
    @Expose
    private List<LocationDetail> result = new ArrayList<LocationDetail>();

    /**
     * 
     * @return
     *     The messages
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    /**
     * 
     * @return
     *     The result
     */
    public List<LocationDetail> getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(List<LocationDetail> result) {
        this.result = result;
    }

}
