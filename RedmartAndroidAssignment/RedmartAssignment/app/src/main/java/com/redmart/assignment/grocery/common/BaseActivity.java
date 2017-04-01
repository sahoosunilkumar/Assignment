package com.redmart.assignment.grocery.common;

import android.widget.Toast;

import com.sunilsahoo.viewmodelbinding.ViewModelActivity;

/**
 * parent activity of all activities
 */
public abstract class BaseActivity extends ViewModelActivity implements
        MessageHelper {

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
}
