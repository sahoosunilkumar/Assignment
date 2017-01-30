package com.iec.locationfinder;

import com.sunilsahoo.inventorycontroller.entity.Result;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/21/16.
 */
public interface MapLocationView {
    void refresh(List<Result> results);

    void showToastMessage(String message);
}
