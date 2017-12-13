package com.swiggy.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Variants implements Serializable {

    private final static long serialVersionUID = -6205850018818146376L;
    @SerializedName("variant_groups")
    @Expose
    private List<VariantGroup> variantGroups = null;
    @SerializedName("exclude_list")
    @Expose
    private List<List<ExcludeList>> excludeList = null;

    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public void setVariantGroups(List<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public List<List<ExcludeList>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<List<ExcludeList>> excludeList) {
        this.excludeList = excludeList;
    }

}
