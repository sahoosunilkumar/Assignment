package com.sunilsahoo.maplocation.coupon;

import com.sunilsahoo.maplocation.model.Coupon;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface CouponView {
    void refreshList(List<Coupon> couponList);
    void showProgressBar();
    void hideProgressBar();
}
