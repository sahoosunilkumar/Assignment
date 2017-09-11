package com.sixt.cars.app.maplocator;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;

/**
 * Created by sunilkumarsahoo on 8/28/17.
 */

public interface InventoryMapView {
    void showMarkersFor(ArrayList<CarInfo> carInfoList);
}
