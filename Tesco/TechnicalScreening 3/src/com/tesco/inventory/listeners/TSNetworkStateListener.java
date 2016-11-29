/**
 * Broadcast receiver to listen network change
 */
package com.tesco.inventory.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tesco.inventory.beans.NetworkStatus;
import com.tesco.inventory.networkmanager.TSAppController;
import com.tesco.inventory.service.TSMainService;
import com.tesco.inventory.utils.TSUtility;

public class TSNetworkStateListener extends BroadcastReceiver {

	/**
	 * If network available, stoop network listener and start pending sync
	 */
	public void onReceive(Context context, Intent intent) {
		NetworkStatus status = TSUtility.checkNetworkStatus(context);
		if (status.isNetworkAvail()) {
			try {
				// stop network listener
				Intent stopNetworkListenerIntent = new Intent(
						TSAppController.getContext(), TSMainService.class);
				stopNetworkListenerIntent.putExtra(TSMainService.TASK_ID,
						TSMainService.STOP_NETWORK_LISTENER);
				TSAppController.getContext().startService(
						stopNetworkListenerIntent);
                //perform sync
				TSUtility.performSync();
			} catch (Exception ex) {
				Log.e("TSNetworkStateListener",
						"Error in Performing task in Network listener :"
								+ ex.getMessage());
			}
		}
	}
}
