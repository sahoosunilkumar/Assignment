package com.badoo.assignment.productviewer.transactions;

import android.content.Context;

import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.model.Transaction;

import java.util.List;

public interface TransactionView {

    void showProgress();

    void hideProgress();

    void setItems(GBPConverter converter, List<Transaction> transactionList);

    void showMessage(String message);

    Context getViewContext();

    void updateTotal();

    void onSetTitle();

    void onResetTitle();
}
