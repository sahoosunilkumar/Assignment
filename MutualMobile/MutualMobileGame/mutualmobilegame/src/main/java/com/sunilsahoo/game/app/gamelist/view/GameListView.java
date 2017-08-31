package com.sunilsahoo.game.app.gamelist.view;


import com.sunilsahoo.game.models.GameDataResponse;

public interface GameListView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onSuccess(GameDataResponse cityListResponse);

}
