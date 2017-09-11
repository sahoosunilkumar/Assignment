package com.sixt.cars.app.home.view;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;

public interface Updatable {
    void update(ArrayList<CarInfo> carInfoList);
}