package com.sunilsahoo.game.app.deps;


import com.sunilsahoo.game.app.gamelist.view.GameListFragment;
import com.sunilsahoo.game.app.playerinfo.view.PlayerInfoFragment;
import com.sunilsahoo.game.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(GameListFragment gameListFragment);

    void inject(PlayerInfoFragment playerInfoFragment);
}
