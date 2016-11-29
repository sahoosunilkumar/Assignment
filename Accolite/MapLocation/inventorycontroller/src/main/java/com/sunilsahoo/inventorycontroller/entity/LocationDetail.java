
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationDetail {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("largest_city")
    @Expose
    private String largestCity;
    @SerializedName("capital")
    @Expose
    private String capital;

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

    /**
     * 
     * @return
     *     The abbr
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * 
     * @param abbr
     *     The abbr
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * 
     * @return
     *     The area
     */
    public String getArea() {
        return area;
    }

    /**
     * 
     * @param area
     *     The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 
     * @return
     *     The largestCity
     */
    public String getLargestCity() {
        return largestCity;
    }

    /**
     * 
     * @param largestCity
     *     The largest_city
     */
    public void setLargestCity(String largestCity) {
        this.largestCity = largestCity;
    }

    /**
     * 
     * @return
     *     The capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * 
     * @param capital
     *     The capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

}
