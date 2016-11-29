
package com.logitech.inventorycontoller.entity;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeviceList {

    @SerializedName("devices")
    private List<Device> devices = new ArrayList<Device>();

    /**
     * 
     * @return
     *     The devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * 
     * @param devices
     *     The devices
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
