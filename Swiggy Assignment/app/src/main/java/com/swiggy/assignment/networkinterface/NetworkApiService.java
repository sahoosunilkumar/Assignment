package com.swiggy.assignment.networkinterface;

import com.swiggy.assignment.search.model.FetchVariantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApiService {


    @GET("/bins/3b0u2")
    Call<FetchVariantsResponse> getVariants();
}
