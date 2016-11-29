package com.sunilsahoo.maplocation.maplocator;

import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.SearchIPController;
import com.sunilsahoo.inventorycontroller.SearchLocationController;
import com.sunilsahoo.inventorycontroller.entity.IPRestResponse;
import com.sunilsahoo.inventorycontroller.entity.IPSearchResponse;
import com.sunilsahoo.inventorycontroller.entity.SearchLocation;
import com.sunilsahoo.inventorycontroller.entity.SearchLocationResponse;
import com.sunilsahoo.networkmanager.beans.NetResponse;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public class MapIPPresenterImpl implements MapIPPresenter, ITaskListener {
    private MapLocationView dealsView;
    private IPSearchResponse ipSearchResponse;
    MapIPPresenterImpl(MapLocationView dealsView) {
        this.dealsView = dealsView;
    }

    @Override
    public void onSuccess(IPSearchResponse ipSearchResponse) {
        this.ipSearchResponse = ipSearchResponse;
        dealsView.showMap((this.ipSearchResponse.getRestResponse()).getResult());

    }

    @Override
    public void onFailure(String message) {
        dealsView.showToastMessage(message);
    }

    @Override
    public void getItems(String query) {
        new SearchIPController().getResults(query,this);
    }

    @Override
    public void onSuccess(NetResponse response) {
        onSuccess((IPSearchResponse)response.getResponse());
    }

    @Override
    public void onError(String error, int code) {
       onFailure(error);
    }
}
