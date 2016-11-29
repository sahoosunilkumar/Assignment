
package com.sunilsahoo.inventorycontoller.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Results {

    @SerializedName("channel")
    @Expose
    private Channel channel;

    /**
     * 
     * @return
     *     The channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * 
     * @param channel
     *     The channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
