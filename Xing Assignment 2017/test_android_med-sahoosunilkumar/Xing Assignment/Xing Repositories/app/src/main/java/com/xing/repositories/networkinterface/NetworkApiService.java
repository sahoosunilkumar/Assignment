package com.xing.repositories.networkinterface;

import com.xing.repositories.browse.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApiService {


    @GET("users/xing/repos?")
    Call<List<Repository>> getUser(@Query("page") int pageNumber, @Query("per_page") int count);
}
