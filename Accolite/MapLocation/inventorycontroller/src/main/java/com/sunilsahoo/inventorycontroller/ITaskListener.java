package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.networkmanager.beans.NetResponse;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public interface ITaskListener {
    void onSuccess(NetResponse response);

    void onError(String error, int code);
}
