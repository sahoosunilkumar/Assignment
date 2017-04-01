
package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promotion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("savings_text")
    @Expose
    private String savingsText;
    @SerializedName("current_product_group_id")
    @Expose
    private Integer currentProductGroupId;
    @SerializedName("promo_label")
    @Expose
    private String promoLabel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSavingsText() {
        return savingsText;
    }

    public void setSavingsText(String savingsText) {
        this.savingsText = savingsText;
    }

    public Integer getCurrentProductGroupId() {
        return currentProductGroupId;
    }

    public void setCurrentProductGroupId(Integer currentProductGroupId) {
        this.currentProductGroupId = currentProductGroupId;
    }

    public String getPromoLabel() {
        return promoLabel;
    }

    public void setPromoLabel(String promoLabel) {
        this.promoLabel = promoLabel;
    }

}
