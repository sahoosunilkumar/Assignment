package com.badoo.assignment.productviewer.product;

import android.content.Context;

import com.badoo.assignment.productviewer.model.TransactionMapping;

public interface FindProductsInteractor {

    void findItems(Context context, OnFinishedListener listener);

    interface OnFinishedListener {
        void onFinished(TransactionMapping items);
    }
}
