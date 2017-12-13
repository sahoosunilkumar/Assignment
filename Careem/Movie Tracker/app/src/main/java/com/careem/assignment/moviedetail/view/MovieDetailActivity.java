package com.careem.assignment.moviedetail.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.careem.assignment.R;
import com.careem.assignment.common.utils.ImageLoader;
import com.careem.assignment.databinding.ActivityMovieDetailBinding;
import com.careem.assignment.moviedetail.viewmodel.MovieDetailViewModel;
import com.careem.assignment.search.model.Result;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_INFO = "extra_info";
    private MovieDetailViewModel viewModel;

    public static void launch(Context context, Result result) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_INFO, result);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        setData();
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setMovie(viewModel.getMovieDetail());
        ImageLoader.loadImage(binding.icon, binding.getMovie());
    }

    private void setData() {
        Result result = getIntent().getExtras().getParcelable(EXTRA_INFO);
        if (result != null) {
            viewModel.setMovieDetail(result);
        }
    }

}
