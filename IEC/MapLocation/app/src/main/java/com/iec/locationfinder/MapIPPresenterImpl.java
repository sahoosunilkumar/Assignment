package com.iec.locationfinder;

import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.SearchLocationController;
import com.sunilsahoo.inventorycontroller.entity.ZipcodeResponse;
import com.sunilsahoo.networkmanager.beans.NetResponse;

/**
 * Created by sunilkumarsahoo on 12/21/16.
 */
public class MapIPPresenterImpl implements MapIPPresenter, ITaskListener {
    private MapLocationView dealsView;
    private ZipcodeResponse ipSearchResponse;

    MapIPPresenterImpl(MapLocationView dealsView) {
        this.dealsView = dealsView;
    }

    @Override
    public void onSuccess(ZipcodeResponse ipSearchResponse) {
        this.ipSearchResponse = ipSearchResponse;
        dealsView.refresh(this.ipSearchResponse.getResults());
    }

    @Override
    public void onFailure(String message) {
        dealsView.showToastMessage(message);
    }

    @Override
    public void getItems(String query) {
        new SearchLocationController().getResults(query, this);
    }

    @Override
    public void onSuccess(NetResponse response) {
        onSuccess((ZipcodeResponse) response.getResponse());
    }

    @Override
    public void onError(String error, int code) {
        onFailure(error);
    }
}
