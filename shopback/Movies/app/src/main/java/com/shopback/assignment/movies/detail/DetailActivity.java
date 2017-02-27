package com.shopback.assignment.movies.detail;

import com.shopback.assignment.movies.BR;
import com.shopback.assignment.movies.utilities.ImageHelper;
import com.shopback.assignment.movies.R;
import com.shopback.assignment.movies.common.BaseActivity;
import com.shopback.assignment.movies.databinding.MovieDetailBinding;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onResume() {
        super.onResume();
        MovieDetailViewModel viewModel = getViewModel(BR.moviedetailviewmodel);
        String movieIdStr = getIntent().getExtras().getString("ID");
        viewModel.getMovieDetail(movieIdStr);
    }

    @Override
    public ViewDataHolder onBindViewDataHolder() {
        com.sunilsahoo.viewmodelbinding.common.ViewModel[] arr = new com
                .sunilsahoo
                .viewmodelbinding.common.ViewModel[1];
        arr[0] = new MovieDetailViewModel(BR.moviedetailviewmodel, this);
        return new ViewDataHolder(R.layout.movie_detail, arr);
    }

    public void setImage(final String path){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageHelper.setImageFromUrl(getActivity(), ImageHelper.getBackdropPath(path), ((MovieDetailBinding) getBinding()).icon);
            }
        });
    }
}
