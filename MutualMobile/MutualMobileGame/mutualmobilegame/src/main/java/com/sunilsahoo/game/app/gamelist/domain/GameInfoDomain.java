package com.sunilsahoo.game.app.gamelist.domain;


import com.sunilsahoo.game.app.home.domain.NetworkCallDomain;
import com.sunilsahoo.game.models.GameDataResponse;
import com.sunilsahoo.game.networking.NetworkError;
import com.sunilsahoo.game.networking.NetworkService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GameInfoDomain extends NetworkCallDomain {

    public GameInfoDomain(NetworkService networkService) {
        super(networkService);
    }

    @Override
    public String getUrl() {
        return "https://api.myjson.com/bins/11j2gr";
    }


    @Override
    public Subscription execute(final Callback callback) {

        return getNetworkService().getGameInfo(getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends
                        GameDataResponse>>() {
                    @Override
                    public Observable<? extends GameDataResponse> call
                            (Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GameDataResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(GameDataResponse gameDataResponse) {
                        callback.onSuccess(gameDataResponse);

                    }
                });
    }
}
