package com.sixt.inventorycontroller;

import com.sixt.networkmanager.beans.NetResponse;

public interface ITaskListener {
    void onSuccess(NetResponse response);

    void onError(String error, int code);
}
