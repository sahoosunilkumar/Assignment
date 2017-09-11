/**
 * Response info contains inventory list, request id, response code, sync
 * completed status
 */
package com.sixt.networkmanager.beans;

import java.util.ArrayList;
import java.util.List;

public class NetResponse {

    public static final String TAG = NetResponse.class.getName();

    private int requestID;
    private List<Object> values = new ArrayList<>();
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

    public <T extends Object> T getResponse() {
        if(values.isEmpty()){
            return null;
        }
        return (T) values.get(0);
    }

    public <T extends Object>  void setResponse( T response) {
        values.clear();
        values.add(response);
    }
}
