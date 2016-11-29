package com.sunilsahoo.maplocation.location;

import com.sunilsahoo.inventorycontroller.entity.SearchLocationResponse;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface SearchPresenter {
    void onSuccess(SearchLocationResponse locationResponse);

    void onFailure(String message);

    void onDestroy();

    void getItems(String query);
}
