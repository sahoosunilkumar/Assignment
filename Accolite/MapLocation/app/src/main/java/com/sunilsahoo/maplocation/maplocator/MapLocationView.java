package com.sunilsahoo.maplocation.maplocator;

import com.sunilsahoo.inventorycontroller.entity.IPDetail;
import com.sunilsahoo.inventorycontroller.entity.LocationDetail;
import com.sunilsahoo.inventorycontroller.entity.MapLocation;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface MapLocationView {
    void showMap(IPDetail ipDetail);
    void showToastMessage(String message);
}
