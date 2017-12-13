package com.swiggy.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FetchVariantsResponse implements Serializable {

    private final static long serialVersionUID = -7864355560682090317L;
    @SerializedName("variants")
    @Expose
    private Variants variants;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

}
