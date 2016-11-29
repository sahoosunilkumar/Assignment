/**
 * Responsibility: abstract class for
 * 1) send request to server
 * 2) parse response depending on entity type
 * 3) send callback
 */
package com.myntra.assignment.controller;

import android.util.Log;

import com.myntra.assignment.controller.entity.RestResponse;
import com.myntra.assignment.controller.utils.ControllerConstants;
import com.myntra.assignment.networkmanager.RequestManager;
import com.myntra.assignment.networkmanager.beans.NetRequest;
import com.myntra.assignment.networkmanager.listeners.RequestCallback;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
abstract public class Controller implements RequestCallback {
    private static final String TAG = Controller.class.getName();
    private Controller controller = null;
    private NetRequest request = null;
    private ITaskListener taskListener = null;
    private Class<?> entity;


    public Controller() {
        controller = this;
    }

    public void execute(int requestID, String url, String httpMethod, String
            requestString, ITaskListener taskListener) {
        if (request == null) {
            this.taskListener = taskListener;
            request = new NetRequest();
            request.setRequestID(requestID);
            request.setUrl(url);
            request.setHttpOperationType(httpMethod);
            if (request.getHttpOperationType().equals(ControllerConstants
                    .HttpMethods.HTTP_PUT)
                    || request.getHttpOperationType().equals(
                    ControllerConstants.HttpMethods.HTTP_POST)) {
                request.setRequestString(requestString);
            }
            request.setCallback(this);
            execute(request);
        } else {
            Log.i(TAG, "Other Request is in process");
        }
    }

    /**
     * Forwards the request to a thread.
     *
     * @param
     */
    private void execute(NetRequest request) {
        RequestManager.INSTANCE.execute(request);
    }

    @Override
    public void onHttpResponseReceived(int requestID, String response, int
            responseCode) {
        if (taskListener == null) {
            Log.d(TAG, "cant notify listener is null");
            return;
        }
        if (responseCode == ControllerConstants.ResponseCodes.SUCCESS) {
            RestResponse responseObj = new RestResponse();
            responseObj.setResponse(prepareResponse(response));
            responseObj.setRequestID(requestID);
            responseObj.setResponseCode(responseCode);
            taskListener.onSuccess(responseObj);
        } else {
            taskListener.onError(response, responseCode);
        }
    }

    @Override
    public void cancelRequest(boolean flag) {
        Log.i(TAG, "cancel request");
        //TODO implementation pending
    }


    /**
     * set returned entity type
     * @param entity
     */
    public void setEntity(Class<?> entity) {
        this.entity = entity;
    }

    /**
     * parse the response depending on entity type
     * @param response
     * @return
     */
    private Object prepareResponse(String response) {
        if (entity == null) {
            return response;
        }
        return Parser.INSTANCE.parse(response, entity);
    }

}
