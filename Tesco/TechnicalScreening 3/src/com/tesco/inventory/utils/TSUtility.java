package com.tesco.inventory.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tesco.inventory.beans.NetworkStatus;
import com.tesco.inventory.networkmanager.TSAppController;
import com.tesco.inventory.service.TSMainService;

public class TSUtility {
	
	/**
	 * Checks whether a string is empty or not
	 * @param str
	 * @return true if any data present
	 */
	public static boolean isNotEmpty(String str) {
		try {
			return str.trim().length()>0;
		} catch (Exception ex) {
			return false;
		}
	}
	/**
     * Sets network status and returns network status object
     * @param context
     * @return
     */
    public static NetworkStatus checkNetworkStatus(Context context) {
        synchronized (NetworkStatus.getInstance()) {
            ConnectivityManager conMan = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMan.getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    NetworkStatus.getInstance().setNetworkAvail(true);
                    NetworkStatus.getInstance().setWifiAvail(true);
                } else {
                    NetworkStatus
                            .getInstance()
                            .setNetworkAvail(
                                    (netInfo.getState() == NetworkInfo.State.CONNECTED || netInfo
                                            .getState() == NetworkInfo.State.CONNECTING));
                }
            } else {
                NetworkStatus.getInstance().setNetworkAvail(false);
                NetworkStatus.getInstance().setWifiAvail(false);
            }

            return NetworkStatus.getInstance();

        }
    }
    public static int getIntValue(int resId){
        return TSAppController.getContext().getResources().getInteger(resId);
    }
    
    public static void performSync(){
        Intent startRetrySyncIntent = new Intent(
                TSAppController.getContext(), TSMainService.class);
        startRetrySyncIntent.putExtra(TSMainService.TASK_ID,
                TSMainService.START_SYNC);
        TSAppController.getContext().startService(
                startRetrySyncIntent);
    }
}
