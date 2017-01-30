package com.iec.locationfinder;

import com.sunilsahoo.inventorycontroller.entity.ZipcodeResponse;

/**
 * Created by sunilkumarsahoo on 12/21/16.
 */
public interface MapIPPresenter {
    void onSuccess(ZipcodeResponse ipSearchResponse);

    void onFailure(String message);

    void getItems(String query);
}
