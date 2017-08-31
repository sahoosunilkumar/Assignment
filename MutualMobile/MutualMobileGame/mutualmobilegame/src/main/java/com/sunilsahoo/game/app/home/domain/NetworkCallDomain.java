package com.sunilsahoo.game.app.home.domain;

import com.sunilsahoo.game.networking.NetworkError;
import com.sunilsahoo.game.networking.NetworkService;

import rx.Subscription;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public abstract class NetworkCallDomain {
    private final NetworkService networkService;

    public NetworkCallDomain(NetworkService networkService) {
        this.networkService = networkService;
    }

    public abstract String getUrl();

    public abstract Subscription execute(Callback callback);

    public NetworkService getNetworkService() {
        return networkService;
    }

    public interface Callback<T> {
        void onSuccess(T response);

        void onError(NetworkError networkError);
    }
}

