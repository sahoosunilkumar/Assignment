package com.shopback.assignment.movies.dashboard;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.shopback.assignment.movies.R;
import com.shopback.assignment.movies.BR;
import com.shopback.assignment.movies.common.BaseActivity;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.shopback.assignment.movies.common.RecyclerTouchListener;
import com.shopback.assignment.movies.databinding.ActivityMainBinding;

/**
 * Displays all movies in list format
 */
public class ListActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;

    @BindingAdapter("bind:list")
    public static void bindList(RecyclerView view, MoviesViewModel
            viewModel) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view
                .getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new MovieAdapter(viewModel.getFavoriteList(), viewModel));
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
                                //TODO do nothing
                            }
                        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        MoviesViewModel viewModel = getViewModel(BR.favoriteViewModel);
        if(TextUtils.isEmpty(viewModel.getTitle())) {
            viewModel.setTitle(getResources().getString(R.string.title_movie_list));
        }
    }


    @Override
    public ViewDataHolder onBindViewDataHolder() {
        com.sunilsahoo.viewmodelbinding.common.ViewModel[] arr = new com.sunilsahoo
                .viewmodelbinding.common.ViewModel[1];
        arr[0] = new MoviesViewModel(BR.favoriteViewModel, this);
        return new ViewDataHolder(R.layout.activity_main, arr);
    }
}
