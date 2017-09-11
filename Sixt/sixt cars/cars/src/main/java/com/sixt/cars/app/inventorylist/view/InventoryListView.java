package com.sixt.cars.app.inventorylist.view;


import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.List;

public interface InventoryListView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onSuccess(List<CarInfo> cityListResponse);

}
