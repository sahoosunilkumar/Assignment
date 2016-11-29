package com.badoo.assignment.productviewer.product;

public interface ProductPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
