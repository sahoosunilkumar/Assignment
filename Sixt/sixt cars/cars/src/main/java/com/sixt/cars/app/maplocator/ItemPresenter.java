package com.sixt.cars.app.maplocator;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;

public interface ItemPresenter {
    ArrayList<CarInfo> getItems();

    void setItems(ArrayList<CarInfo> itemList);

    void onMapReady();

    void showMap();
}
