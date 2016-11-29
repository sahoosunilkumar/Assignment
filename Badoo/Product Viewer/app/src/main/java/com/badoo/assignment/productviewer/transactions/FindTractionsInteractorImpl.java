package com.badoo.assignment.productviewer.transactions;

import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.controller.CurrencyDetailController;
import com.badoo.assignment.productviewer.controller.DeviceDetailController;

public class FindTractionsInteractorImpl implements FindTransactionsInteractor {

    @Override
    public void findItems(int position, final OnFinishedListener listener) {
        GBPConverter converter = new GBPConverter(CurrencyDetailController.INSTANCE.getCurrencyMapping().getCurrencyMap());
        listener.onFinished(converter, DeviceDetailController.INSTANCE
                .getTransactionList().getList(position));
    }

}
