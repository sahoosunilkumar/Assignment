/**
 * Response info contains inventory list, request id, response code, sync
 * completed status
 */
package com.myntra.assignment.networkmanager.beans;

public class NetResponse {

    public static final String TAG = NetResponse.class.getName();

    private int requestID;
    private Object responseData;
    private int responseCode;
    /**
     * serverID = If add/update/delete status is success, server responds the
     * server id
     */
    private String serverID = "";

    public NetResponse() {
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
