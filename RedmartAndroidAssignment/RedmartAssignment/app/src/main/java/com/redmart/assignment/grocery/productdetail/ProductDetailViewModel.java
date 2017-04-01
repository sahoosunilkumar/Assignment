package com.redmart.assignment.grocery.productdetail;

import android.databinding.ObservableField;

import com.redmart.assignment.grocery.common.MessageHelper;
import com.sunilsahoo.inventorycontroller.Controller;
import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.ProductDetailController;
import com.sunilsahoo.inventorycontroller.entity.ProductDetailResponse;
import com.sunilsahoo.networkmanager.beans.NetResponse;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

/**
 * Created by sunilkumarsahoo on 3/30/17.
 */

public class ProductDetailViewModel extends ViewModel{
    public ObservableField<ProductDetailResponse> productDetail = new ObservableField<>();
    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private ITaskListener callback ;
    private MessageHelper messageHelper;
    private final Controller controller = new ProductDetailController();

    public ProductDetailViewModel(int variableId, MessageHelper messageHelper) {
        super(variableId);
        this.messageHelper = messageHelper;
        this.callback = new GetItemsInteractorCallback();
    }

    public void getItems(int productId) {
        showProgress.set(true);
        controller.getResults(callback, productId);
    }


    public void onFailure(String message) {
        showProgress.set(false);
        messageHelper.showMessage(message);
    }

    class GetItemsInteractorCallback implements ITaskListener {


        @Override
        public void onSuccess(NetResponse response) {
            productDetail.set((ProductDetailResponse) response.getResponse());
            showProgress.set(false);
            ((IProductDetailView)getView()).onProductDetailReceived();
        }

        @Override
        public void onError(String error, int code) {
            onFailure(error);
        }
    }
}
