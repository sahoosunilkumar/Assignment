package com.sunilsahoo.inventorycontoller;

import com.sunilsahoo.inventorycontoller.entity.RestResponse;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public interface ITaskListener {
    void onSuccess(RestResponse response);

    void onError(String error, int code);
}
