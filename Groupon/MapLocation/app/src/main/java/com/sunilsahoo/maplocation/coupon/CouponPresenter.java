package com.sunilsahoo.maplocation.coupon;

import com.sunilsahoo.maplocation.model.Coupon;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface CouponPresenter {
    void onSuccess(List<Coupon> couponList);
    void onDestroy();
    void getItems();
}
