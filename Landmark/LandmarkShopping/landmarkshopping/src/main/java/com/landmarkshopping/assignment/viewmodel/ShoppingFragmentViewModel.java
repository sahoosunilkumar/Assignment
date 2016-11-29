package com.landmarkshopping.assignment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import com.landmarkshopping.assignment.Constants;
import com.landmarkshopping.assignment.model.Product;
import com.landmarkshopping.network.HTMLParserProxy;
import com.landmarkshopping.network.ICallback;
import com.landmarkshopping.network.model.Request;
import com.landmarkshopping.network.model.Response;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 9/19/16.
 */
public class ShoppingFragmentViewModel implements ViewModel {
    private static ObservableField<List<Product>> sItemList = new
            ObservableField<List<Product>>();
    private Context context;
    private DataListener dataListener;

    public ShoppingFragmentViewModel(Context context, DataListener
            dataListener) {

        this.context = context;
        this.dataListener = dataListener;
    }

    public static List<Product> getItemList() {
        return sItemList.get();
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Override
    public void destroy() {
        context = null;
        dataListener = null;
        sItemList.set(null);
    }

    public void doUpdateProductList() {
        Request request = new Request(1, Constants.BASE_URL, new ICallback() {
            @Override
            public void onSuccess(Response response) {
                sItemList.set((List) response.getData());

//                Log.d("ViewModel", "updated list : "+sItemList.get());
                dataListener.onProductsUpdated();
            }

            @Override
            public void onError(String error, int code) {

            }
        });
        HTMLParserProxy.getInstance().execute(request);
    }

    public interface DataListener {
        void onProductsUpdated();
    }
}
