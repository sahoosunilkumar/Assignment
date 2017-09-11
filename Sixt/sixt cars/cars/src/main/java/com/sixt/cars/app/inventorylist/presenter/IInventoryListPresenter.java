package com.sixt.cars.app.inventorylist.presenter;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.List;

public interface IInventoryListPresenter {
    List<CarInfo> getItems();

    void setItems(List<CarInfo> carInfoList);
}
