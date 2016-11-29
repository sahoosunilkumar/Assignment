package com.sunilsahoo.maplocation.location;

import android.content.Context;

import com.sunilsahoo.inventorycontroller.entity.LocationDetail;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface SearchView {
    void refreshList(List<LocationDetail> couponList);

    void showProgressBar();

    void hideProgressBar();

    void showToastMessage(String message);

    Context getViewContext();
}
