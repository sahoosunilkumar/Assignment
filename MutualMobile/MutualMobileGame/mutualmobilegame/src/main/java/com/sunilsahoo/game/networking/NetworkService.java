package com.sunilsahoo.game.networking;


import com.sunilsahoo.game.models.GameDataResponse;
import com.sunilsahoo.game.models.PlayerInfoResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkService {

    @GET("{fullUrl}")
    Observable<GameDataResponse> getGameInfo(@Path(value = "fullUrl", encoded
            = true) String fullUrl);

    @GET("{fullUrl}")
    Observable<PlayerInfoResponse> getPlayerInfo(@Path(value = "fullUrl",
            encoded = true) String fullUrl);

}
