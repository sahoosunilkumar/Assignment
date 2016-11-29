package com.landmarkshopping.network;

import com.landmarkshopping.network.model.Response;

/**
 * callback of HTML response
 */
public interface ICallback {
    void onSuccess(Response response);

    void onError(String error, int code);
}
