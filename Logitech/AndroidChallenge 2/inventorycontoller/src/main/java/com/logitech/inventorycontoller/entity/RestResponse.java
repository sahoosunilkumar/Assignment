/**
 * Responsibility: response object type
 */
package com.logitech.inventorycontoller.entity;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public class RestResponse {
    private int requestID;
    private int responseCode;
    private Object response;
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
