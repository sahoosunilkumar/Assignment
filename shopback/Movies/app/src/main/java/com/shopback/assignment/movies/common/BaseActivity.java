package com.shopback.assignment.movies.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.sunilsahoo.inventorycontroller.ConfigurationController;
import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.entity.Configuration;
import com.sunilsahoo.networkmanager.beans.NetResponse;
import com.sunilsahoo.viewmodelbinding.ViewModelActivity;

/**
 * parent activity of all activities
 */
public abstract class BaseActivity extends ViewModelActivity implements
        MessageHelper {
    private static Configuration sConfiguration;

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, message, Toast
                        .LENGTH_LONG)
                        .show();
            }
        });
    }

    public MessageHelper getMessageHelper() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(sConfiguration == null){
            new ConfigurationController().getResults(new ITaskListener() {
                @Override
                public void onSuccess(NetResponse response) {
                    sConfiguration = (Configuration) response.getResponse();
                }

                @Override
                public void onError(String error, int code) {

                }
            }, null);
        }
        super.onCreate(savedInstanceState);
    }

    public static Configuration getConfiguration(){
        return sConfiguration;
    }
}
