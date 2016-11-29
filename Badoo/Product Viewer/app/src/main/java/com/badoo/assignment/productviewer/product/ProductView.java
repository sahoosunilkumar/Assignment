package com.badoo.assignment.productviewer.product;

import android.content.Context;

import com.badoo.assignment.productviewer.model.TransactionMapping;

public interface ProductView {

    void showProgress();

    void hideProgress();

    void setItems(TransactionMapping transactionList);

    void showMessage(String message);

    Context getViewContext();

    void onItemSelected(int position, String title);

    void onSetTitle();
}
