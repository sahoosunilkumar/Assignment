package com.redmart.assignment.grocery.productlist;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.redmart.assignment.grocery.productdetail.ProductDetailActivity;
import com.sunilsahoo.inventorycontroller.Controller;
import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.ProductListController;
import com.sunilsahoo.inventorycontroller.entity.Product;
import com.sunilsahoo.inventorycontroller.entity.ProductListResponse;
import com.sunilsahoo.networkmanager.beans.NetResponse;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;
import com.redmart.assignment.grocery.BR;
import com.redmart.assignment.grocery.common.MessageHelper;

import java.util.List;


public class ProductListViewModel extends ViewModel {

    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private String title = "";
    private ObservableList<Product> favoriteList = new ObservableArrayList<>();
    private ITaskListener callback ;
    private MessageHelper messageHelper;

    private int currentPageNumber;
    private final Controller controller = new ProductListController();

    public ProductListViewModel(int variableId, MessageHelper messageHelper) {
        super(variableId);
        this.messageHelper = messageHelper;
        this.callback = new
                GetItemsInteractorCallback();
        this.addOnPropertyChangedCallback(new PropertyChangeCallback());
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public ObservableList<Product> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Product> favoriteList) {
        this.favoriteList.addAll(favoriteList);
    }


    public void onItemClicked(int position) {
        Intent intent = new Intent(getView().getActivity(), ProductDetailActivity.class);
        intent.putExtra("productId", favoriteList.get(position).getId());
        getView().getActivity().startActivity(intent);
    }

    public void getItems() {
        showProgress.set(true);
        controller.getResults(callback, String.valueOf(currentPageNumber++));
    }


    public void onFailure(String message) {
        showProgress.set(false);
        messageHelper.showMessage(message);
    }

    class GetItemsInteractorCallback implements ITaskListener {


        @Override
        public void onSuccess(NetResponse response) {
            ProductListResponse detail = (ProductListResponse) response.getResponse();
            setFavoriteList(detail.getProducts());
            showProgress.set(false);
        }

        @Override
        public void onError(String error, int code) {
            onFailure(error);
        }
    }

    class PropertyChangeCallback extends OnPropertyChangedCallback {

        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (i == BR.title) {
                getItems();
            }
        }
    }
}

