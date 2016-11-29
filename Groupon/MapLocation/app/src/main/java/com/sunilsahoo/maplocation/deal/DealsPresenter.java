package com.sunilsahoo.maplocation.deal;

import com.sunilsahoo.maplocation.model.Deal;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface DealsPresenter {
    void onSuccess(List<Deal> dealList);
    void onDestroy();
    void getItems();
}
