package com.sunilsahoo.maplocation.deal;

import com.sunilsahoo.maplocation.model.Deal;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface DealsView {
    void refreshList(List<Deal> couponList);
    void showProgressBar();
    void hideProgressBar();
}
