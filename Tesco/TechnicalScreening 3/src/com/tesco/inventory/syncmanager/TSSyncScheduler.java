package com.tesco.inventory.syncmanager;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.tesco.inventory.beans.Cart;
import com.tesco.inventory.beans.Inventory;
import com.tesco.inventory.beans.Item;
import com.tesco.inventory.beans.NetResponse;
import com.tesco.inventory.databasemanager.TSDBOperation;
import com.tesco.inventory.databasemanager.TSDBProviderMetaData;
import com.tesco.inventory.databasemanager.TSDBProviderMetaData.Sales;
import com.tesco.inventory.databasemanager.TSDBProviderMetaData.SalesMapping;
import com.tesco.inventory.listeners.TSHttpListener;
import com.tesco.inventory.networkmanager.TSAppController;
import com.tesco.inventory.service.TSMainService;
import com.tesco.inventory.utils.TSConstants;
import com.tesco.inventory.utils.TSConstants.Limits;
import com.tesco.inventory.utils.TSConstants.RequestCodes;
import com.tesco.inventory.utils.TSConstants.SyncType;
import com.tesco.inventory.utils.TSUtility;

public class TSSyncScheduler implements TSHttpListener{
	private static final String TAG = TSSyncScheduler.class.getName();
	private Runnable syncTaskRunner = null;
	private ScheduledFuture schedulerTaskHandler = null;
	private ScheduledExecutorService scheduler = null;
	private final boolean INTERRUPT_ONGOING_SYNC = true;
	private Context context;
	private ArrayList<Short> syncActionList = null;
	private volatile boolean isSyncOn = false;

	private static class TSSyncInstanceHolder {
		private static final TSSyncScheduler INSTANCE = new TSSyncScheduler();
	}
	private TSSyncScheduler(){
		Log.i(TAG, "Initialize SyncScheduler");
		scheduler = Executors.newScheduledThreadPool(1);
		syncTaskRunner = new Runnable() {
			public void run() {
				Log.i(TAG, "Scheduling is going on");
				if (TSUtility.checkNetworkStatus(context).isNetworkAvail()) {
					startSync(context, SyncType.COMPLETE);
				} else {
					// start network listener
					Intent startServiceIntent = new Intent(
							TSAppController.getContext(),
							TSMainService.class);
					startServiceIntent.putExtra(TSMainService.TASK_ID,
							TSMainService.START_NETWORK_LISTENER);
					TSAppController.getContext().startService(
							startServiceIntent);
				}
			}
		};
	}	
			
	
	
	/**
	 * Starts scheduler
	 * @param initialDelay
	 * @param interval
	 * @param schedulerTimeunit
	 */
	public void startScheduler(int initialDelay, int interval, String schedulerTimeunit, Context context) {				
		this.context = context;
		schedulerTaskHandler = scheduler.scheduleWithFixedDelay(syncTaskRunner,
				initialDelay, interval, getTimeUnit(schedulerTimeunit));
	}
	/**
	 * cancels all task if stopScheduler parameter is false
	 * cancels all task and stops scheduler if stopScheduler is true
	 * @param stopScheduler if true, stops scheduler
	 */
	public void stopScheduler(boolean stopScheduler) {
		// cancel the ongoing sync task
		if (schedulerTaskHandler != null)
			schedulerTaskHandler.cancel(INTERRUPT_ONGOING_SYNC);
		if (stopScheduler) {
			// stop the scheduler
			if (scheduler != null && !scheduler.isShutdown())
				scheduler.shutdownNow();
		}
	}
	/**
	 * returns time unit
	 * @param schedulerTimeunit
	 * @return
	 */
	private TimeUnit getTimeUnit(String schedulerTimeunit){
		if(schedulerTimeunit.equalsIgnoreCase("SECOND") || schedulerTimeunit.equalsIgnoreCase("SECONDS")){
			return TimeUnit.SECONDS;
		} else if(schedulerTimeunit.equalsIgnoreCase("MINUTE") || schedulerTimeunit.equalsIgnoreCase("MINUTES")){
			return TimeUnit.MINUTES;
		} else if(schedulerTimeunit.equalsIgnoreCase("HOUR") || schedulerTimeunit.equalsIgnoreCase("HOURS")){
			return TimeUnit.HOURS;
		}else{
			return TimeUnit.DAYS;
		} 	
	}
	
	/**
	 * Returns instance of sync scheduler
	 * @return
	 */
	public static TSSyncScheduler getInstance() {
		return TSSyncInstanceHolder.INSTANCE;
	}
	
	/**
	 * If sync status is off then it start sync 
	 * if sync type is pending sync, reuse older sync list. otherwise create sync list
	 * sync list must include the list of actions needs to be executed for sync
	 * @param syncType
	 */
	public void startSync(Context context, byte syncType) {
		Log.d(TAG, "Sync Status :"+isSyncOn+" Sync Type :"+syncType);
		if(this.context == null){
			this.context = context;
		}
		if (!isSyncOn) {
			isSyncOn = true;
				if (syncActionList == null) {
					syncActionList = new ArrayList<Short>();
				} else {
					syncActionList.clear();
				}

				switch (syncType) {
				case SyncType.SALES:
					syncActionList.add(RequestCodes.ADD_CART);
					break;
				default:
					Log.d(TAG, "unhandled sync type");
					break;
				}
			NetResponse response = new NetResponse();
			performSyncOperation(context, response);
		}
	}
	/**
	 * Cancel sync
	 */
	private void cancelSync(){
		TSAppController.getInstance().cancelRequest();
		isSyncOn = false;		
	}
	
	/**
	 * Performs sync operation
	 * 1- Sends request to the server
	 * 2- performs db operation depending on action type. eg insert item and discount data, delete sales data
	 * 3- If sync is complete for a particular action remove it from list
	 * 4- Notify sync complete if list is empty
	 * @param response
	 */
	private void performSyncOperation(final Context context, final NetResponse response) {
		boolean removeFromSync = true;
		try{
		if (response.getResponseData() != null) {
			if ((response.getResponseCode() == TSConstants.ResponseCodes.SUCCESS)) {
				
					if (TSUtility.isNotEmpty(response.getServerID())) {
						String transactionNos[] = response.getServerID().split(
								TSConstants.MATRIX_PARAMS_KEY_PAIR_SEPARATOR);
						Cart cart = new Cart();
						for (String transactionNo : transactionNos) {
							// delete sales history as per depending on transaction no
							try {
								cart.setTransactionNumber(transactionNo);
								TSDBOperation.performDBOperation(cart, context,
										TSConstants.DBOperations.DELETE);
							} catch (Exception e) {
								Log.e("CartCheckoutScreen",
										"Cant delete transaction details from DB "
												+ response.getServerID());
							}
						}
					}

					if (response.getResponseData().size() < Limits.MAX_NO_OF_CARTS_PER_REQUEST) {
						if ((response.getRequestID() == TSConstants.RequestCodes.ADD_CART)) {
							if (getSalesCount() > 0) {
								removeFromSync = false;
							}
						}
						if (removeFromSync) {
							syncActionList.remove(new Short((short) response
									.getRequestID()));
						}
					}
			} else if (response.getResponseCode() == TSConstants.ResponseCodes.NO_NETWORK_AVAIL) {
				isSyncOn = false;
			}

		}

		if (!syncActionList.isEmpty()) {
			if (!response.isSyncCompleted()) {
				if (syncActionList.get(0) == TSConstants.RequestCodes.ADD_CART) {
				    Inventory inventoryDtls = null;
			        int salesCount = getSalesCount();
		            if (salesCount > 0) {
				    inventoryDtls = getCartdetails(response);
		            }
					// if no data in sales table, set sync complete
			        if(salesCount<=1){
			        response.setSyncCompleted(true);
			        }else{
			            response.setSyncCompleted(false);
			        }
			        if (inventoryDtls != null) {
			            TSAppController.getInstance().sendWebServiceRequest(
			                    context, this, syncActionList.get(0),
			                    inventoryDtls);
			        }
			}else{
			    //unhandled inventory type
			    Log.w(TAG, "unhandled inventory type");
			}

			if (response.isSyncCompleted()) {
				syncActionList.remove(0);
				response.setSyncCompleted(false);
				response.setResponseData(null);

			}
		}

		if (syncActionList.isEmpty()) {
			isSyncOn = false;
			Intent startServiceIntent = new Intent(context,
					com.tesco.inventory.service.TSMainService.class);
			startServiceIntent.putExtra(TSMainService.TASK_ID,
					TSMainService.STOP_NETWORK_LISTENER);
			context.startService(
					startServiceIntent);
			Log.i(TAG, "SYNC COMPLETED :::");
			
		}
		}
		}catch(Exception ex){
			Log.e(TAG, "Error while performing sync :"+ex.getMessage());
		}
	}
	
	
	
	@Override
	public void onHttpResponseReceived(NetResponse response) {
		performSyncOperation(context, response);		
	}
	@Override
	public void cancelRequest(boolean flag) {
		// TODO Auto-generated method stub
		cancelSync();	
	}

	private int getSalesCount() {
		Cursor cursor = null;
		int salesCount = 0;
		try {
			Uri uri = TSDBProviderMetaData.Sales.CONTENT_URI;
			cursor = TSDBOperation.searchForID(uri, context, null,
					new String[] { "count(*) AS count" }, null);
			cursor.moveToFirst();
			salesCount = cursor.getInt(0);
		} catch (Exception ex) {
			Log.e(TAG, "Error in getting sales count :" + ex.getMessage());
		} finally {
			if (cursor != null) {
				try {
					cursor.close();
				} catch (Exception ex) {
				}
			}
			cursor = null;
		}
		return salesCount;
	}
	
	
	private Inventory getCartdetails(NetResponse response){
	 // perform db operation to get sales details
        Inventory inventoryDtls = null;
        String sortOrder = Sales.KEY_ROWID + " ASC "+" limit "+TSConstants.Limits.MAX_NO_OF_CARTS_PER_REQUEST;
        Uri uri = TSDBProviderMetaData.Sales.CONTENT_URI;

                Cursor cursor = TSDBOperation.searchForID(uri,
                        context, null,
                        new String[] { "count(*) AS count" }, null);
                cursor.moveToFirst();
                cursor = TSDBOperation.searchForID(uri, context,
                        null, null, sortOrder);
                Cart cartDetails = null;
                if ((cursor != null) && cursor.moveToFirst()) {
                    cartDetails = new Cart();
                    cartDetails
                            .setTransactionNumber(cursor.getString(cursor
                                    .getColumnIndexOrThrow(TSDBProviderMetaData.Sales.TRANSACTION_NO)));
                    //TODO set other details for the cart after retrieving form database

                }

                // set cart item details like price, name etc matching with transaction number
                if (cartDetails != null) {
                    uri = SalesMapping.CONTENT_URI;
                    String whereClause = SalesMapping.TRANSACTION_NO
                            + "= '"
                            + (cartDetails).getTransactionNumber()
                            + "'";
                    cursor = TSDBOperation.searchForID(uri,
                            context, whereClause, null, null);

                    ArrayList<Item> mappedItems = new ArrayList<Item>();
                    Item item = new Item();
                    if ((cursor != null)) {
                        while (cursor.moveToNext()) {
                            item.setID(cursor.getString(cursor
                                    .getColumnIndexOrThrow(TSDBProviderMetaData.SalesMapping.ITEM_ID)));
                        }
                        //set item, price, name etc
                        mappedItems.add(item);
                    }

                    cartDetails.setCartItems(mappedItems);
                }
                inventoryDtls = cartDetails;
        return inventoryDtls;
	}

}
