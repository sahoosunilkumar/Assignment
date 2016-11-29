/**
 * Stores Request infor required to send request
 */
package com.tesco.inventory.beans;

public class NetRequest {

	public static final String TAG = NetRequest.class.getName();	
	private int requestID;
	private String httpOperationType;
	private String url;
	private String requestString;
	private long requestCreatedTime = System.currentTimeMillis();
	private byte requestRetryCounter = 0;
	private Inventory inventoryDetail = null;

	public NetRequest() {
	}	

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setHttpOperationType(String httpOperationType) {
		this.httpOperationType = httpOperationType;
	}

	public String getHttpOperationType() {
		return httpOperationType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setRequestString(String requestString) {
		this.requestString = requestString;
	}

	public String getRequestString() {
		return requestString;
	}


	public long getRequestCreatedTime() {
		return requestCreatedTime;
	}

	public void setRequestCreatedTime(long requestCreatedTime) {
		this.requestCreatedTime = requestCreatedTime;
	}

	public byte getRequestRetryCounter() {
		return requestRetryCounter;
	}

	public void setRequestRetryCounter(byte requestRetryCounter) {
		this.requestRetryCounter = requestRetryCounter;
	}

	public Inventory getInventoryDetail() {
		return inventoryDetail;
	}

	public void setInventoryDetail(Inventory inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}

}
