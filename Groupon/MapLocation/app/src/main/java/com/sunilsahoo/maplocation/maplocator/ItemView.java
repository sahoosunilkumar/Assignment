package com.sunilsahoo.maplocation.maplocator;

import com.sunilsahoo.maplocation.model.Deal;
import com.sunilsahoo.maplocation.model.Item;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public interface ItemView {
    void refreshList(List<Item> itemList);
}
