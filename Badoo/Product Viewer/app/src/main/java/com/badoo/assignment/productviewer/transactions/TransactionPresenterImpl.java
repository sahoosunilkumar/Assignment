package com.badoo.assignment.productviewer.transactions;

import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.model.Transaction;

import java.util.List;

public class TransactionPresenterImpl implements TransactionPresenter,
        FindTransactionsInteractor
                .OnFinishedListener {

    private TransactionView mainView;
    private FindTransactionsInteractor findItemsInteractor;
    private int position;

    public TransactionPresenterImpl(TransactionView mainView,
                                    FindTransactionsInteractor
                                            findItemsInteractor, int position) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
        this.position = position;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(position, this);
    }

    @Override
    public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked",
                    position + 1));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(GBPConverter converter, List<Transaction> transactionList) {
        if (mainView != null) {
            mainView.setItems(converter, transactionList);
            mainView.updateTotal();
            mainView.hideProgress();
        } else {
            mainView.showMessage(mainView.getViewContext().getResources()
                    .getString(R.string.error));
            mainView.hideProgress();
        }
    }

    public TransactionView getMainView() {
        return mainView;
    }
}
