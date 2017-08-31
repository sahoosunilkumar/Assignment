package com.sunilsahoo.game.app.playerinfo.domain;


import com.sunilsahoo.game.app.home.domain.NetworkCallDomain;
import com.sunilsahoo.game.models.PlayerInfoResponse;
import com.sunilsahoo.game.networking.NetworkError;
import com.sunilsahoo.game.networking.NetworkService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PlayerInfoDomain extends NetworkCallDomain {

    public PlayerInfoDomain(NetworkService networkService) {
        super(networkService);
    }

    @Override
    public String getUrl() {
        return "https://api.myjson.com/bins/w660r";
    }


    @Override
    public Subscription execute(final Callback callback) {

        return getNetworkService().getPlayerInfo(getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends
                        PlayerInfoResponse>>() {
                    @Override
                    public Observable<? extends PlayerInfoResponse> call
                            (Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PlayerInfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(PlayerInfoResponse playerInfoResponse) {
                        callback.onSuccess(playerInfoResponse);

                    }
                });
    }
}
