package com.redmart.assignment.grocery.productdetail;

import android.os.Bundle;
import android.widget.ImageView;

import com.redmart.assignment.grocery.BR;
import com.redmart.assignment.grocery.R;
import com.redmart.assignment.grocery.common.BaseActivity;
import com.redmart.assignment.grocery.common.Constants;
import com.redmart.assignment.grocery.databinding.ProductDetailBinding;
import com.squareup.picasso.Picasso;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.synnapps.carouselview.ImageListener;

public class ProductDetailActivity extends BaseActivity implements IProductDetailView, ImageListener{
    private ProductDetailBinding productDetailBinding;
    private ProductDetailViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailBinding = ((ProductDetailBinding) getBinding());
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel = getViewModel(BR.productDetailViewModel);
        productDetailBinding.carouselView.setImageListener(this);
        viewModel.getItems(getIntent().getIntExtra("productId",0));
    }


    @Override
    public ViewDataHolder onBindViewDataHolder() {
        com.sunilsahoo.viewmodelbinding.common.ViewModel[] arr = new com.sunilsahoo
                .viewmodelbinding.common.ViewModel[1];
        arr[0] = new ProductDetailViewModel(BR.productDetailViewModel, this);
        return new ViewDataHolder(R.layout.product_detail, arr);
    }

    @Override
    public void onProductDetailReceived() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                productDetailBinding.carouselView.setPageCount(viewModel.productDetail.get().getProduct().getImages().size());
            }
        });
    }


    @Override
    public void setImageForPosition(int position, ImageView imageView) {
            Picasso
                    .with(imageView.getContext())
                    .load(Constants.IMAGE_BASE_URL+viewModel.productDetail.get().getProduct().getImages().get(position)
                            .getName())
                    .fit()
                    .into(imageView);
    }
}
