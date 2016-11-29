/**
 * Responsibility: response object type
 */
package com.badoo.assignment.productviewer.controller;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public class Response {
    private int requestID;
    private int responseCode;
    private Object response;

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

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
