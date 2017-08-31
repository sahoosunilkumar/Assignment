package com.sunilsahoo.game.app.playerinfo.view;


import com.sunilsahoo.game.models.PlayerInfoResponse;

public interface PlayerInfoView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onSuccess(PlayerInfoResponse playerInfoResponse);

    void removeLastLogin();

    void showLastLogin();

}
