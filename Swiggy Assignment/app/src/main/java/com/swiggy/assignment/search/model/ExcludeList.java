package com.swiggy.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExcludeList implements Serializable {

    private final static long serialVersionUID = 150733304277225065L;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("variation_id")
    @Expose
    private String variationId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

}
