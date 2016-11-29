/**
 * Stores Request infor required to send request
 */
package com.sunilsahoo.networkmanager.beans;

import com.sunilsahoo.networkmanager.listeners.RequestCallback;

public class NetRequest {

    private int requestID;
    private String httpOperationType;
    private String url;
    private String requestString;
    private RequestCallback callback;

    public NetRequest() {
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getHttpOperationType() {
        return httpOperationType;
    }

    public void setHttpOperationType(String httpOperationType) {
        this.httpOperationType = httpOperationType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestString() {
        return requestString;
    }

    public void setRequestString(String requestString) {
        this.requestString = requestString;
    }

    public RequestCallback getCallback() {
        return callback;
    }

    public void setCallback(RequestCallback callback) {
        this.callback = callback;
    }
}
