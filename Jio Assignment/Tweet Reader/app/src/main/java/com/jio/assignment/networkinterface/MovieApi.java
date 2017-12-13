package com.jio.assignment.networkinterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApi {

    private static final String BASE_URL = "https://api.themoviedb.org";

    public static MovieApiService createMovieApiService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        return builder.build().create(MovieApiService.class);
    }
}
