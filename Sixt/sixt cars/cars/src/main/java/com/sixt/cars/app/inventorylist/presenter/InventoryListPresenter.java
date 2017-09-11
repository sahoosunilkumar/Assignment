package com.sixt.cars.app.inventorylist.presenter;

import com.sixt.cars.app.home.view.Updatable;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class InventoryListPresenter implements IInventoryListPresenter {
    Updatable view;
    private List<CarInfo> items = new ArrayList<>();

    public InventoryListPresenter(Updatable inventoryListView) {
        this.view = inventoryListView;
    }

    @Override
    public List<CarInfo> getItems() {
        return items;
    }

    @Override
    public void setItems(List<CarInfo> carInfoList) {
        items.clear();
        items.addAll(carInfoList);
    }
}
