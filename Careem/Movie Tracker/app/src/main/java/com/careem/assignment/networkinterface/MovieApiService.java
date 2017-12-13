package com.careem.assignment.networkinterface;

import com.careem.assignment.search.model.FetchMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    String API_KEY = "258c00846c0f3f64abd86ec6beab0f1d";


    @GET("/3/movie/upcoming?api_key="+API_KEY)
    Call<FetchMovieResponse> getUser(@Query("page") int pageNumber);
}
