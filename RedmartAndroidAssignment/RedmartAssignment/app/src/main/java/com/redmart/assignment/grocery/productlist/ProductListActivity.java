package com.redmart.assignment.grocery.productlist;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.redmart.assignment.grocery.R;
import com.redmart.assignment.grocery.BR;
import com.redmart.assignment.grocery.common.BaseActivity;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.redmart.assignment.grocery.common.RecyclerTouchListener;
import com.redmart.assignment.grocery.databinding.ActivityMainBinding;

public class ProductListActivity extends BaseActivity {
    private ActivityMainBinding activityMainBinding;

    @BindingAdapter("bind:list")
    public static void bindList(RecyclerView view, ProductListViewModel
            viewModel) {
        view.setAdapter(new ProductAdapter(viewModel.getFavoriteList(), viewModel));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ((ActivityMainBinding) getBinding());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), getResources().getInteger(R.integer.no_of_columns));
        activityMainBinding.recyclerView.setLayoutManager(mLayoutManager);

        activityMainBinding.recyclerView.addOnItemTouchListener(new
                RecyclerTouchListener
                (getApplicationContext(), activityMainBinding.recyclerView,
                        new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                activityMainBinding
                                        .getFavoriteViewModel
                                                ().onItemClicked(position);
                            }

                            @Override
                            public void onLongClick(View view, int position) {
//                                ProductListResponse productListResponse = activityMainBinding.getFavoriteViewModel().getFavoriteList().get(position);
//                                launchRepositoryViewer(productListResponse);
                                //TODO
                            }
                        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        ProductListViewModel viewModel = getViewModel(BR.favoriteViewModel);
        if(TextUtils.isEmpty(viewModel.getTitle())) {
            viewModel.setTitle(getResources().getString(R.string.title_repository_list));
        }
    }


    @Override
    public ViewDataHolder onBindViewDataHolder() {
        com.sunilsahoo.viewmodelbinding.common.ViewModel[] arr = new com.sunilsahoo
                .viewmodelbinding.common.ViewModel[1];
        arr[0] = new ProductListViewModel(BR.favoriteViewModel, this);
        return new ViewDataHolder(R.layout.activity_main, arr);
    }

}
