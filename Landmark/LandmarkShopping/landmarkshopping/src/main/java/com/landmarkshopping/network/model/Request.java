package com.landmarkshopping.network.model;

import com.landmarkshopping.network.ICallback;

/**
 * Responsibility : Request object for HTML request
 * Created by sunilkumarsahoo on 9/17/16.
 */
public class Request {
    private int requestId;
    private String url;
    private ICallback callback;
    private boolean cancel;

    public Request(int requestId, String url, ICallback callback) {
        this.requestId = requestId;
        this.url = url;
        this.callback = callback;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public ICallback getCallback() {
        return callback;
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
