package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.networkmanager.beans.NetResponse;

public interface ITaskListener {
    void onSuccess(NetResponse response);

    void onError(String error, int code);
}
