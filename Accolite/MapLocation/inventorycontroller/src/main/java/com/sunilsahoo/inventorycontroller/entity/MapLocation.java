
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapLocation {

    @SerializedName("countryIso2")
    @Expose
    private String countryIso2;
    @SerializedName("stateAbbr")
    @Expose
    private String stateAbbr;
    @SerializedName("postal")
    @Expose
    private String postal;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("ds")
    @Expose
    private String ds;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("ip")
    @Expose
    private String ip;

    /**
     * 
     * @return
     *     The countryIso2
     */
    public String getCountryIso2() {
        return countryIso2;
    }

    /**
     * 
     * @param countryIso2
     *     The countryIso2
     */
    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    /**
     * 
     * @return
     *     The stateAbbr
     */
    public String getStateAbbr() {
        return stateAbbr;
    }

    /**
     * 
     * @param stateAbbr
     *     The stateAbbr
     */
    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    /**
     * 
     * @return
     *     The postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * 
     * @param postal
     *     The postal
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * 
     * @return
     *     The continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * 
     * @param continent
     *     The continent
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The ds
     */
    public String getDs() {
        return ds;
    }

    /**
     * 
     * @param ds
     *     The ds
     */
    public void setDs(String ds) {
        this.ds = ds;
    }

    /**
     * 
     * @return
     *     The network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 
     * @param ip
     *     The ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}
