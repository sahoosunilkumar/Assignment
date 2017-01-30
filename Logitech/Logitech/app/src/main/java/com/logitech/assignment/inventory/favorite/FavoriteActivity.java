package com.logitech.assignment.inventory.favorite;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.logitech.assignment.inventory.R;
import com.logitech.assignment.inventory.common.BaseActivity;
import com.logitech.assignment.inventory.databinding.ActivityMainBinding;
import com.tesco.viewmodelbinding.BR;
import com.tesco.viewmodelbinding.common.ViewDataHolder;

public class FavoriteActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;

    @BindingAdapter("bind:list")
    public static void bindList(RecyclerView view, FavoriteViewModel
            viewModel) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view
                .getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ItemAdapter(viewModel.getFavoriteList()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getApplicationContext());
        activityMainBinding = ((ActivityMainBinding) getBinding());
        activityMainBinding.recyclerView.setLayoutManager(mLayoutManager);
        activityMainBinding.recyclerView.setItemAnimator(new
                DefaultItemAnimator());

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

                            }
                        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        FavoriteViewModel viewModel = getViewModel(BR.favoriteViewModel);
        viewModel.setTitle(getResources().getString(R.string.title_favorite));
    }


    @Override
    public ViewDataHolder onBindViewDataHolder() {
        com.tesco.viewmodelbinding.common.ViewModel[] arr = new com.tesco
                .viewmodelbinding.common.ViewModel[1];
        arr[0] = new FavoriteViewModel(BR.favoriteViewModel, this);
        return new ViewDataHolder(R.layout.activity_main, arr);
    }
}
