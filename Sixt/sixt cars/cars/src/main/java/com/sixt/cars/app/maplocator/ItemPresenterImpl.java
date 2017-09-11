package com.sixt.cars.app.maplocator;

import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;

public class ItemPresenterImpl implements ItemPresenter {
    boolean isMapReady = false;
    private InventoryMapView mapView;
    private ArrayList<CarInfo> itemList = new ArrayList<>();

    ItemPresenterImpl(InventoryMapView itemView) {
        this.mapView = itemView;
    }

    @Override
    public ArrayList<CarInfo> getItems() {
        return itemList;
    }

    @Override
    public void setItems(ArrayList<CarInfo> carInfoArrayList) {
        itemList.clear();
        itemList.addAll(carInfoArrayList);
        showMap();
    }

    @Override
    public void onMapReady() {
        isMapReady = true;
        showMap();
    }

    @Override
    public void showMap() {
        boolean hasEnoughAccess = isMapReady && !itemList.isEmpty();
        if (hasEnoughAccess) {
            mapView.showMarkersFor(itemList);
        }
    }
}
