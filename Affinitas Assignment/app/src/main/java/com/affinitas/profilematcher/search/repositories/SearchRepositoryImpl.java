package com.affinitas.profilematcher.search.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.affinitas.profilematcher.filter.rules.FilterManager;
import com.affinitas.profilematcher.networkmanager.api.GithubApiService;
import com.affinitas.profilematcher.search.model.ApiResponse;
import com.affinitas.profilematcher.search.model.RetrieveMatches;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRepositoryImpl implements SearchRepository {

    private static final String BASE_URL = "https://raw.githubusercontent.com/affinitas/coding_exercises_options/master/";
    private static final String END_POINT = "filtering_matches/database/matches.json";
    private GithubApiService mApiService;

    public SearchRepositoryImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApiService = retrofit.create(GithubApiService.class);
    }

    public LiveData<ApiResponse> getUsers(FilterOptions filterOptions) {
        FilterManager.getInstance().setFilterOptions(filterOptions);
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
        Call<RetrieveMatches> call = mApiService.getIssues(BASE_URL + END_POINT);
        call.enqueue(new Callback<RetrieveMatches>() {
            @Override
            public void onResponse(Call<RetrieveMatches> call, Response<RetrieveMatches> response) {
                response.body().setUsers(FilterManager.getInstance().apply(response.body().getUsers()));
                liveData.setValue(new ApiResponse(response.body()));
            }

            @Override
            public void onFailure(Call<RetrieveMatches> call, Throwable t) {
                liveData.setValue(new ApiResponse(t));
            }
        });
        return liveData;
    }

}
