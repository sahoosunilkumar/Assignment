package com.landmarkshopping.assignment.model;

import java.io.Serializable;

/**
 * Created by sunilkumarsahoo on 9/17/16.
 */
public class Product implements Entity, Serializable {
    private String name;
    private String title;
    private String imagePath;
    private String badge;
    private String price;
    private String delPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelPrice() {
        return delPrice;
    }

    public void setDelPrice(String delPrice) {
        this.delPrice = delPrice;
    }


    @Override
    public String toString() {
        return "name : " + name + " title : " + title + " imagePath : " +
                imagePath + " badge : " + badge + " price : " + price;
    }
}
