package com.swiggy.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variation implements Serializable {

    private final static long serialVersionUID = 8554430632745786686L;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("default")
    @Expose
    private int _default;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("inStock")
    @Expose
    private int inStock;
    @SerializedName("isVeg")
    @Expose
    private int isVeg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDefault() {
        return _default;
    }

    public void setDefault(int _default) {
        this._default = _default;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(int isVeg) {
        this.isVeg = isVeg;
    }

}
