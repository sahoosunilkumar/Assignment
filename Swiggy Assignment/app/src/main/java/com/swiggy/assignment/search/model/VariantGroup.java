package com.swiggy.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VariantGroup implements Serializable {

    private final static long serialVersionUID = 4239685305863134075L;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("variations")
    @Expose
    private List<Variation> variations = null;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public void setVariations(List<Variation> variations) {
        this.variations = variations;
    }

}
