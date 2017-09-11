package com.sixt.cars.app.home.view;


import android.support.v4.app.Fragment;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onSuccess(ArrayList<CarInfo> carListResponse);

    List<Fragment> getFragments(ArrayList<CarInfo> carInfoList);

}
