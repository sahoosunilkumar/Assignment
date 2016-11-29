/**
 * Network status
 */
package com.logitech.networkmanager.beans;

public class NetworkStatus {
    private boolean isNetworkAvail = false;
    private boolean isWifiAvail = false;

    private NetworkStatus() {
    }

    /**
     * Creates instance if not exists
     *
     * @return
     */
    public static NetworkStatus getInstance() {
        return NetworkStatusInstanceHolder.INSTANCE;
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

    private static class NetworkStatusInstanceHolder {
        private static final NetworkStatus INSTANCE = new NetworkStatus();
    }
    /**
     * Sets network status both for wifi connectivity and network availability
     * @param context
     */


}
