/**
 * HTTP listener callback to UI
 */
package com.sixt.networkmanager.listeners;

public interface RequestCallback {

    void onHttpResponseReceived(int requestID, String response, int
            responseCode);

    void cancelRequest(boolean flag);
}
