package com.sixt.cars.app.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sixt.cars.app.home.view.Updatable;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;

/**
 * Created by sunilkumarsahoo on 8/28/17.
 */

public abstract class BaseFragment extends Fragment implements Updatable {

    private static final String KEY_CAR_INFO = "key_car_info";
    private ArrayList<CarInfo> carInfoList;

    public static <T extends BaseFragment> T createInstance(Class<T> type,
                                                            ArrayList<CarInfo> carInfoList) throws IllegalAccessException, java.lang.InstantiationException {
        T obj = type.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_CAR_INFO, carInfoList);
        obj.setArguments(bundle);
        return obj;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleArguments(getArguments());
        handleSavedInstanceState(savedInstanceState);
        update(carInfoList);
    }

    /**
     * retain saved arguments from arguments
     *
     * @param arguments bundle from which required information needs to be
     *                  extracted
     */
    private void handleArguments(Bundle arguments) {
        if (arguments != null) {
            carInfoList = arguments.getParcelableArrayList(KEY_CAR_INFO);
        }
    }

    /**
     * retain saved information from savedInstanceState
     *
     * @param savedInstanceState bundle from which saved information needs to
     *                           be extracted
     */
    private void handleSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            carInfoList = savedInstanceState.getParcelableArrayList
                    (KEY_CAR_INFO);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_CAR_INFO, carInfoList);
        super.onSaveInstanceState(outState);
    }
}
