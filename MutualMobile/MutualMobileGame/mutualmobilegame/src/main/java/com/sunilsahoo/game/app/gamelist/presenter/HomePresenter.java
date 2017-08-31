package com.sunilsahoo.game.app.gamelist.presenter;

import com.sunilsahoo.game.app.gamelist.domain.GameInfoDomain;
import com.sunilsahoo.game.app.gamelist.view.GameListView;
import com.sunilsahoo.game.app.home.domain.NetworkCallDomain;
import com.sunilsahoo.game.models.GameDataResponse;
import com.sunilsahoo.game.networking.NetworkError;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter implements IHomePresenter {
    private final GameInfoDomain service;
    private final GameListView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(GameInfoDomain service, GameListView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void getGameInfo() {
        view.showWait();

        Subscription subscription = service.execute(new NetworkCallDomain
                .Callback<GameDataResponse>() {
            @Override
            public void onSuccess(GameDataResponse gameDataResponse) {
                view.removeWait();
                view.onSuccess(gameDataResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    @Override
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
