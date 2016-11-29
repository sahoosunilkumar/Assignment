
package com.logitech.inventorycontoller.entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Device implements Parcelable{

    @SerializedName("deviceType")
    private String deviceType;
    @SerializedName("model")
    private String model;
    @SerializedName("name")
    private String name;

    public Device() {
    }

    public Device(Parcel in) {
        deviceType = in.readString();
        model = in.readString();
        name = in.readString();
    }

    /**
     * 
     * @return
     *     The deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 
     * @param deviceType
     *     The deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceType);
        dest.writeString(model);
        dest.writeString(name);

    }

    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>()
    {
        public Device createFromParcel(Parcel in)
        {
            return new Device(in);
        }
        public Device[] newArray(int size)
        {
            return new Device[size];
        }
    };

}
