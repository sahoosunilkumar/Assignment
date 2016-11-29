/**
 * Response info contains inventory list, request id, response code, sync completed status
 */
package com.tesco.inventory.beans;

import java.util.ArrayList;


public class NetResponse {

	public static final String TAG = NetResponse.class.getName();

	private int requestID;
	private ArrayList<Inventory> responseData;
	private int responseCode;
	private boolean isSyncCompleted = false;
	/**
	 * serverID = If add/update/delete status is success, server responds the
	 * server id
	 */
	private String serverID = "";

	public NetResponse() {
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getRequestID() {
		return requestID;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public ArrayList<Inventory> getResponseData() {
		return responseData;
	}

	public void setResponseData(ArrayList<Inventory> responseData) {
		this.responseData = responseData;
	}

	public boolean isSyncCompleted() {
		return isSyncCompleted;
	}

	public void setSyncCompleted(boolean isSyncCompleted) {
		this.isSyncCompleted = isSyncCompleted;
	}

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
}
