package com.sunilsahoo.game.app.playerinfo.presenter;

import com.sunilsahoo.game.app.home.domain.NetworkCallDomain;
import com.sunilsahoo.game.app.playerinfo.domain.PlayerInfoDomain;
import com.sunilsahoo.game.app.playerinfo.view.PlayerInfoView;
import com.sunilsahoo.game.models.PlayerInfoResponse;
import com.sunilsahoo.game.networking.NetworkError;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class PlayerInfoPresenter implements IPlayerInfoPresenter {
    private final PlayerInfoDomain service;
    private final PlayerInfoView view;
    private CompositeSubscription subscriptions;

    public PlayerInfoPresenter(PlayerInfoDomain service, PlayerInfoView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void getGameInfo() {
        view.showWait();

        Subscription subscription = service.execute(new NetworkCallDomain
                .Callback<PlayerInfoResponse>() {
            @Override
            public void onSuccess(PlayerInfoResponse gameDataResponse) {
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
