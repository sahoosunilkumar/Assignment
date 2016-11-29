/**
 * HTTP listener callback to UI
 */
package com.tesco.inventory.listeners;

import com.tesco.inventory.beans.NetResponse;

public interface TSHttpListener {

	public void onHttpResponseReceived(NetResponse response);
	public void cancelRequest(boolean flag);
}
