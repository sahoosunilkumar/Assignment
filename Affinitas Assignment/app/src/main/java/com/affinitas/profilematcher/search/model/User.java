package com.affinitas.profilematcher.search.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("display_name")
    private String displayName;
    @SerializedName("age")
    private Integer age;
    @SerializedName("job_title")

    private String jobTitle;
    @SerializedName("height_in_cm")
    private Integer heightInCm;
    @SerializedName("city")
    private City city;
    @SerializedName("main_photo")
    private String mainPhoto;
    @SerializedName("compatibility_score")
    private Double compatibilityScore;
    @SerializedName("contacts_exchanged")
    private Integer contactsExchanged;
    @SerializedName("favourite")
    private Boolean favourite;
    @SerializedName("religion")
    private String religion;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(Integer heightInCm) {
        this.heightInCm = heightInCm;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public Double getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(Double compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }

    public Integer getContactsExchanged() {
        return contactsExchanged;
    }

    public void setContactsExchanged(Integer contactsExchanged) {
        this.contactsExchanged = contactsExchanged;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

}
