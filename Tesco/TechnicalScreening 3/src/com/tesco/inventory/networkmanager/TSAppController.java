package com.tesco.inventory.networkmanager;

import android.content.Context;
import android.util.Log;

import com.tesco.inventory.beans.Inventory;
import com.tesco.inventory.beans.NetRequest;
import com.tesco.inventory.beans.NetResponse;
import com.tesco.inventory.listeners.TSHttpListener;
import com.tesco.inventory.utils.TSConstants;
import com.tesco.inventory.utils.TSConstants.HttpMethods;
import com.tesco.inventory.utils.TSConstants.HttpUrls;
import com.tesco.inventory.utils.TSUtility;



public final class TSAppController {
	private static final String TAG = TSAppController.class.getName();
	private TSHttpHandler httpHandler;
	private TSRequestProcessor reqHandler;
	private TSResponseProcessor resHandler;
	private static TSAppController controller = null;
	private boolean cancelRequest;
	private boolean requestStatus;
	private TSHttpListener currentScreen;
	private static Context appContext = null;
	private NetRequest request = null;
	private TSAppController() {
		reqHandler = new TSRequestProcessor();
		resHandler = new TSResponseProcessor();
	}

	/**
	 * Create the single instance of this controller and use throughout the
	 * application.
	 * 
	 * @return : AppController
	 */
	public static TSAppController getInstance() {
		if (controller == null)
			controller = new TSAppController();

		return controller;
	}
	
	public static Context getContext(){
		return appContext;
	}
	
	private void setContext(Context context){
		appContext = context;
	}

	/**
	 * Add NetworkListener to notify later.
	 * 
	 * @param requestID
	 *            : used as a key
	 * @param listener
	 *            : NetworkListener
	 */
	public synchronized void addHttpListener(int requestID,
			TSHttpListener listener) {
		currentScreen = listener;
	}

	/**
	 * Send web service request.
	 * 
	 * @param requestID
	 * @param params
	 */
	private void sendWebServiceRequest(final int requestID,
			Inventory inventoryDetails) {
		if (request == null) {
			cancelRequest = false;
			requestStatus = false;

			request = new NetRequest();
			request.setRequestID(requestID);
			request.setUrl(getURL(requestID, inventoryDetails));
			request.setHttpOperationType(getHttpMethod(requestID));
			if (request.getHttpOperationType().equals(HttpMethods.HTTP_PUT)
					|| request.getHttpOperationType().equals(
							HttpMethods.HTTP_POST)) {
				
				request.setRequestString(reqHandler.createJSONRequest(request, 
						requestID, inventoryDetails));
			}

			processRequestUsingThread(0);
		}else{
			Log.i(TAG, "Other Request is in process");
		}
	}
	/**
	 * Forwards the request to a thread.
	 * @param delay
	 */
	private void processRequestUsingThread(long delay) {
		if (request != null) {
			httpHandler = new TSHttpHandler(request, delay);
			httpHandler.start();			
		} else {
			Log.i(TAG, "REQUEST IS NULL :: CANT SEND ");
		}
	}
	/**
	 * Sets request sent time and counter
	 */
	public void setCreationTimeAndCounter() {
		if (request != null) {
			request.setRequestCreatedTime(System.currentTimeMillis());
			request.setRequestRetryCounter((byte) (request
					.getRequestRetryCounter() + 1));
		}
	}
	/**
	 * process inventory details and creates request object sends to server
	 * @param listener
	 * @param requestID
	 * @param inventoryDetails
	 */
	public synchronized void sendWebServiceRequest(Context context, TSHttpListener listener, final int requestID,
			Inventory inventoryDetails) {	
		setContext(context);
		addHttpListener(requestID, listener);		
		sendWebServiceRequest(requestID, inventoryDetails);
	}

	/**
	 * This method is used to notify to the listener about the response from the
	 * request.
	 * 
	 * @param requestID
	 *            : Request ID
	 * @param response
	 *            : Response received from the request.
	 * @param responseCode
	 *            : Response code.
	 */
	public synchronized void httpResponseReceived(int requestID,
			String response, int responseCode) {
		boolean notify = true;
		NetResponse responseObj = new NetResponse();
		responseObj.setRequestID(requestID);
		responseObj.setResponseCode(responseCode);
		try {
			if (!cancelRequest) {
				if (TSUtility.isNotEmpty(response)) {
					resHandler.parseHttpResponse(
							responseObj, response);
					
				}
			} else {
				responseObj.setResponseCode(TSConstants.EOF);
				request = null;
			}
		}

		// currentScreen = null;
		finally {
			cancelRequest = false;
			httpHandler = null;
		}
        //If not authorized, refresh session
		if (!cancelRequest && (responseCode == TSConstants.ResponseCodes.UNAUTHORIZED)
				|| (responseCode == TSConstants.ResponseCodes.NO_NETWORK_AVAIL)) {
		    //send request to refresh
		    TSAppController.getInstance().sendWebServiceRequest(
		            TSAppController.getContext(), currentScreen, TSConstants.RequestCodes.REFRESH,
                    null);
		    return;
		}
		//send cart request after getting success response for refresh request
		else if(responseObj.getRequestID() == TSConstants.RequestCodes.REFRESH){
		    TSUtility.performSync();
		    return;
		}

		if (notify && (currentScreen != null)) {
			request = null;
			currentScreen.onHttpResponseReceived(responseObj);
		}

	}

	/**
	 * Cancel the request being sent.
	 */
	public synchronized void cancelRequest() {
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

	public void cancelRequestOnly() {
		cancelRequest = true;
		if (httpHandler != null) {
			httpHandler.cancel(true);
		}
		currentScreen = null;
		httpHandler = null;
	}

	/**
	 * //Cancel the request being sent.
	 */
	public boolean getRequestStatus() {
		return requestStatus;
	}

	/**
	 * //Cancel the request being sent.
	 */
	public synchronized void setRequestStatus(boolean flag) {
		requestStatus = flag;
	}

	/**
	 * get URL for connection
	 * 
	 * @param requestID
	 * @param params
	 * @return
	 */
	private String getURL(int requestID, Inventory inventoryDetails) {
		String url = null;
		switch (requestID) {
		case TSConstants.RequestCodes.ADD_CART:
			url = HttpUrls.SALES;
			break;

		default:
			Log.e(TAG, "Unknown request with ID :::" + requestID);
			url = null;
		}
		return url;
	}

	/**
	 * Returns Http method needs to be called for the request id
	 * @param requestID
	 * @return
	 */
	private String getHttpMethod(int requestID) {
		switch (requestID) {
		case TSConstants.RequestCodes.ADD_CART:
			return HttpMethods.HTTP_POST;
		case TSConstants.RequestCodes.REFRESH:
            return HttpMethods.HTTP_POST;
			
		default:
			return HttpMethods.HTTP_GET;
		}
	}
	
}
