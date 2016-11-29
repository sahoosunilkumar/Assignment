package com.badoo.assignment.productviewer.transactions;

public interface TransactionPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
