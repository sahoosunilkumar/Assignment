package com.xing.repositories.networkinterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {

    private static final String BASE_URL = "https://api.github.com/";

    public static NetworkApiService createNetworkApiService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        return builder.build().create(NetworkApiService.class);
    }
}
