package com.sixt.cars.app.home.presenter;

import com.sixt.cars.app.home.view.HomeView;
import com.sixt.inventorycontroller.FetchCarsController;
import com.sixt.inventorycontroller.ITaskListener;
import com.sixt.inventorycontroller.entity.CarInfo;
import com.sixt.networkmanager.beans.NetResponse;

import java.util.ArrayList;
import java.util.Arrays;

public class HomePresenter implements IHomePresenter, ITaskListener {
    private final HomeView view;
    private FetchCarsController carsController;

    public HomePresenter(HomeView view) {
        this.view = view;
        this.carsController = new FetchCarsController();
    }

    @Override
    public void getCars() {
        view.showWait();
        carsController.getResults(this);


    }

    @Override
    public void onStop() {
    }

    @Override
    public void onSuccess(NetResponse response) {
        view.removeWait();
        CarInfo[] carInfoArr = response.getResponse();

        ArrayList<CarInfo> list = new ArrayList<>(Arrays.asList(carInfoArr));
        view.onSuccess(list);
    }

    @Override
    public void onError(String error, int code) {
        String currentError = error;
    }
}
