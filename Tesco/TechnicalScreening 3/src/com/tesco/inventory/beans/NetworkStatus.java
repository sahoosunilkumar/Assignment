/**
 * Network status
 */
package com.tesco.inventory.beans;

public class NetworkStatus {
	private boolean isNetworkAvail = false;
	private boolean isWifiAvail = false;
	
	private static class NetworkStatusInstanceHolder {
		private static final NetworkStatus INSTANCE = new NetworkStatus();
	}
	private NetworkStatus() {
	}
	public boolean isNetworkAvail() {
		return isNetworkAvail;
	}

	public void setNetworkAvail(boolean isNetworkAvail) {
		this.isNetworkAvail = isNetworkAvail;
	}

	public boolean isWifiAvail() {
		return isWifiAvail;
	}

	public void setWifiAvail(boolean isWifiAvail) {
		this.isWifiAvail = isWifiAvail;
	}
	/**
	 * Creates instance if not exists
	 * @return
	 */
	public static NetworkStatus getInstance() {
		return NetworkStatusInstanceHolder.INSTANCE;
	}
	/**
	 * Sets network status both for wifi connectivity and network availability
	 * @param context
	 */
	
	

}
