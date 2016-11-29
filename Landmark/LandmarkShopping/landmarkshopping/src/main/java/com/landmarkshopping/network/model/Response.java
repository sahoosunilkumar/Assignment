package com.landmarkshopping.network.model;

/**
 * Responsibility : Response detail for HTML request
 * Created by sunilkumarsahoo on 9/17/16.
 */
public class Response {
    private Object data;
    private int requestId;
    private int responseCode;
    private String info;

    public Response(int requestId) {
        this.requestId = requestId;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
