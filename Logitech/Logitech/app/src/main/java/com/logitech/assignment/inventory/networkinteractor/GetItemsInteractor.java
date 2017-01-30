package com.logitech.assignment.inventory.networkinteractor;

import com.logitech.assignment.inventory.favorite.Item;

import java.util.List;

public interface GetItemsInteractor {

    void findItems(Callback listener);

    interface Callback {
        void onFinished(List<Item> items, String message);
    }
}
