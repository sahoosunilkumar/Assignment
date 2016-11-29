package com.badoo.assignment.productviewer.product;

import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.model.TransactionMapping;

public class ProductPresenterImpl implements ProductPresenter,
        FindProductsInteractor
                .OnFinishedListener {

    private ProductView mainView;
    private FindProductsInteractor findItemsInteractor;

    public ProductPresenterImpl(ProductView mainView, FindProductsInteractor
            findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(mainView.getViewContext(), this);
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
    public void onFinished(TransactionMapping transactionList) {
        if (mainView != null) {
            mainView.setItems(transactionList);
            mainView.hideProgress();
        } else {
            mainView.showMessage(mainView.getViewContext().getResources()
                    .getString(R.string.error));
            mainView.hideProgress();
        }
    }

    public ProductView getMainView() {
        return mainView;
    }
}
