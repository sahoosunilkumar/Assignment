
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {

    @SerializedName("mfr_name")
    @Expose
    private String mfrName;
    @SerializedName("trending_frequency")
    @Expose
    private Integer trendingFrequency;
    @SerializedName("is_organic")
    @Expose
    private Integer isOrganic;
    @SerializedName("country_of_origin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("brand_uri")
    @Expose
    private String brandUri;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("japanese")
    @Expose
    private Integer japanese;
    @SerializedName("natural")
    @Expose
    private Integer natural;
    @SerializedName("vegan")
    @Expose
    private Integer vegan;
    @SerializedName("fsc")
    @Expose
    private Integer fsc;

    public String getMfrName() {
        return mfrName;
    }

    public void setMfrName(String mfrName) {
        this.mfrName = mfrName;
    }

    public Integer getTrendingFrequency() {
        return trendingFrequency;
    }

    public void setTrendingFrequency(Integer trendingFrequency) {
        this.trendingFrequency = trendingFrequency;
    }

    public Integer getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(Integer isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUri() {
        return brandUri;
    }

    public void setBrandUri(String brandUri) {
        this.brandUri = brandUri;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getJapanese() {
        return japanese;
    }

    public void setJapanese(Integer japanese) {
        this.japanese = japanese;
    }

    public Integer getNatural() {
        return natural;
    }

    public void setNatural(Integer natural) {
        this.natural = natural;
    }

    public Integer getVegan() {
        return vegan;
    }

    public void setVegan(Integer vegan) {
        this.vegan = vegan;
    }

    public Integer getFsc() {
        return fsc;
    }

    public void setFsc(Integer fsc) {
        this.fsc = fsc;
    }

}
