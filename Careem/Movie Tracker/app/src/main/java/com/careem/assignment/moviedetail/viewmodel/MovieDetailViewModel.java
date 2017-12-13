package com.careem.assignment.moviedetail.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.careem.assignment.search.model.Result;

public class MovieDetailViewModel extends ViewModel {

    public Result movieDetail;

    public MovieDetailViewModel() {
    }

    public Result getMovieDetail() {
        return this.movieDetail;
    }

    public void setMovieDetail(Result movieDetail) {
        this.movieDetail = movieDetail;
    }
}
