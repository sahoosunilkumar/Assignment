package com.swiggy.assignment.search.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.swiggy.assignment.common.repository.State;
import com.swiggy.assignment.networkinterface.NetworkApi;
import com.swiggy.assignment.networkinterface.NetworkApiService;
import com.swiggy.assignment.search.model.ApiResponse;
import com.swiggy.assignment.search.model.FetchVariantsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepositoryImpl implements SearchRepository {

    private NetworkApiService mApiService;
    private ISearchRepositoryCallback callback;

    public SearchRepositoryImpl(ISearchRepositoryCallback callback) {
        mApiService = NetworkApi.createNetworkApiService();
        this.callback = callback;
    }

    @Override
    public LiveData<ApiResponse> getVariants() {
        callback.onStateChanged(State.IN_PROGRESS);
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
        Call<FetchVariantsResponse> call = mApiService.getVariants();
        call.enqueue(new Callback<FetchVariantsResponse>() {
            @Override
            public void onResponse(Call<FetchVariantsResponse> call, Response<FetchVariantsResponse> response) {
                callback.onStateChanged(State.SUCCESS);
                liveData.setValue(new ApiResponse(response.body()));
            }

            @Override
            public void onFailure(Call<FetchVariantsResponse> call, Throwable t) {
                callback.onStateChanged(State.FAILURE);
                liveData.setValue(new ApiResponse(t));
            }
        });
        return liveData;
    }

}
