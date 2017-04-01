package com.sunilsahoo.inventorycontroller.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunilkumarsahoo on 3/30/17.
 */
public class ProductDetailResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}