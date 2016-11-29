package com.badoo.assignment.productviewer.product;

import android.content.Context;

import com.badoo.assignment.productviewer.controller.CurrencyDetailController;
import com.badoo.assignment.productviewer.controller.DeviceDetailController;
import com.badoo.assignment.productviewer.controller.ITaskListener;
import com.badoo.assignment.productviewer.controller.Response;
import com.badoo.assignment.productviewer.model.TransactionMapping;

public class FindProductsInteractorImpl implements FindProductsInteractor,
        ITaskListener {
    private Context context;
    private OnFinishedListener listener;

    @Override
    public void findItems(Context context, final OnFinishedListener listener) {
        this.context = context;
        this.listener = listener;

        CurrencyDetailController.INSTANCE.getCurrencyList(context, "" + 1,
                null);
        DeviceDetailController.INSTANCE.getTransactionList(context, "" + 1,
                this);
    }

    @Override
    public void onSuccess(Response response) {
        listener.onFinished((TransactionMapping) response.getResponse());
    }

    @Override
    public void onError(String error, int code) {
        listener.onFinished(null);
    }
}
