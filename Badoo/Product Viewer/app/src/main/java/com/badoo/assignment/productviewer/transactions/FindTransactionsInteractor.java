package com.badoo.assignment.productviewer.transactions;

import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.model.Transaction;

import java.util.List;

public interface FindTransactionsInteractor {

    void findItems(int position, OnFinishedListener listener);

    interface OnFinishedListener {
        void onFinished(GBPConverter converter, List<Transaction> list);
    }
}
