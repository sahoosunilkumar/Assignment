package com.sunilsahoo.maplocation.maplocator;

import com.sunilsahoo.inventorycontroller.entity.IPSearchResponse;
import com.sunilsahoo.inventorycontroller.entity.SearchLocationResponse;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface MapIPPresenter {
    void onSuccess(IPSearchResponse ipSearchResponse);

    void onFailure(String message);

    void getItems(String query);
}
