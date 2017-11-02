package com.affinitas.profilematcher.common.view;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/29/17.
 */

public interface IAdapter<T> {
    void addItems(List<T> itemList);

    void clearItems();

    T getItem(int position);
}
