package com.jio.assignment.search.repository;

import android.arch.paging.DataSource;
import android.arch.paging.TiledDataSource;

import com.jio.assignment.common.repository.State;
import com.jio.assignment.networkinterface.MovieApi;
import com.jio.assignment.networkinterface.MovieApiService;
import com.jio.assignment.search.model.RecentTweetResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SearchRepository extends TiledDataSource<Result> {
    private MovieApiService movieApiService;
    private ISearchRepositoryCallback callback;

    public SearchRepository(ISearchRepositoryCallback callback) {
        movieApiService = MovieApi.createMovieApiService();
        this.callback = callback;
    }

    @Override
    public int countItems() {
        return DataSource.COUNT_UNDEFINED;
    }

    @Override
    public List<Result> loadRange(int startPosition, int count) {
        this.callback.onStateChanged(State.IN_PROGRESS);
        int pageNumber = startPosition / count;
        pageNumber = pageNumber == 0 ? 1 : pageNumber;
        List<Result> results = new ArrayList();
        try {
            Response<RecentTweetResponse> response = movieApiService.getUser(pageNumber).execute();
            if (response.isSuccessful() && response.code() == 200) {
                results.addAll(response.body().getResults());
            }
            this.callback.onStateChanged(State.SUCCESS);
        } catch (IOException e) {
            this.callback.onStateChanged(State.FAILURE);
        }
        return results;
    }
}
