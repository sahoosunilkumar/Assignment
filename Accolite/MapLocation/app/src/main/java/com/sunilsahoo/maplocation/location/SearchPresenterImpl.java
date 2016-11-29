package com.sunilsahoo.maplocation.location;

import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.SearchLocationController;
import com.sunilsahoo.inventorycontroller.entity.SearchLocation;
import com.sunilsahoo.inventorycontroller.entity.SearchLocationResponse;
import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.networkmanager.beans.NetResponse;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public class SearchPresenterImpl implements SearchPresenter, ITaskListener {
    private SearchView dealsView;
    private SearchLocationResponse locationResponse;
    SearchPresenterImpl(SearchView dealsView) {
        this.dealsView = dealsView;
    }

    @Override
    public void onSuccess(SearchLocationResponse locationResponse) {
        this.locationResponse = locationResponse;
        dealsView.hideProgressBar();
        if(this.locationResponse.getResult()==null || this.locationResponse.getResult().isEmpty()){
            onFailure(dealsView.getViewContext().getResources().getString(R.string.error));
        }
        dealsView.refreshList(this.locationResponse.getResult());

    }

    @Override
    public void onFailure(String message) {
        dealsView.hideProgressBar();
        dealsView.showToastMessage(message);
    }

    @Override
    public void onDestroy() {
        if(this.locationResponse != null){
            if(this.locationResponse.getResult() != null){
                this.locationResponse.getResult().clear();
            }
        }
        this.locationResponse = null;
    }

    @Override
    public void getItems(String query) {
        dealsView.showProgressBar();
        new SearchLocationController().getResults(query, this);
    }

    @Override
    public void onSuccess(NetResponse response) {
        onSuccess(((SearchLocation) response.getResponse()).getRestResponse());
    }

    @Override
    public void onError(String error, int code) {
       onFailure(error);
    }
}
