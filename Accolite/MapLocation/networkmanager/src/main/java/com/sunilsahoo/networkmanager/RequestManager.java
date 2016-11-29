package com.sunilsahoo.networkmanager;

import android.util.Log;

import com.sunilsahoo.networkmanager.beans.NetRequest;
import com.sunilsahoo.networkmanager.listeners.RequestCallback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunilkumarsahoo on 7/4/16.
 */
public class RequestManager {
    private HttpHandler httpHandler;
    private boolean cancelRequest;
    private RequestCallback currentScreen;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public final static RequestManager INSTANCE = new RequestManager();

    public void execute(NetRequest request) {
        if (request != null) {
            try {
                httpHandler = new HttpHandler(request);
                executor.execute(httpHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Cancel the request being sent.
     */
    public synchronized void cancel() {
        Log.e("Cancel request", "");
        cancelRequest = true;
        if (httpHandler != null) {
            httpHandler.cancel(true);
        }

        if (currentScreen != null) {
            currentScreen.cancelRequest(false);
        }
        // Update the UI in full view screen

        currentScreen = null;
        httpHandler = null;

    }

}
