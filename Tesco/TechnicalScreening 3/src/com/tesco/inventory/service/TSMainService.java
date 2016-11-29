package com.tesco.inventory.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import com.tesco.inventory.listeners.TSNetworkStateListener;
import com.tesco.inventory.syncmanager.TSSyncScheduler;
import com.tesco.inventory.utils.TSConstants;

public class TSMainService extends Service {
	private static final String TAG = TSMainService.class.getName();
	// task id which will be passed to service
	public static final String BUNDLE_INFO = "bundle";
	public static final String TASK_ID = "TaskID";
	public static final String SYNC_TYPE_STR = "SyncType";
	public static final String MESSAGE_TYPE = "MessageType";
	public static final String MESSAGE = "Message";

	public static final int SYNC_INTERVAL_CHANGED = 5;
	public static final int START_SYNC = 3;
	public static final int STOP_SYNC = 4;
	public static final int STOP_SCHEDULER = 2;
	public static final int START_SCHEDULER = 1;
	public static final int START_NETWORK_LISTENER = 10;
	public static final int STOP_NETWORK_LISTENER = 11;

	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
	private TSNetworkStateListener networkListener = null;
	private static Context context;

	// Handler that receives messages from the thread
	private static final class ServiceHandler extends Handler {
		public ServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			Log.d(TAG, "To do task in service::" + msg.what);
			switch (msg.what) {
			case START_SCHEDULER:
				TSSyncScheduler.getInstance().startScheduler(0, 5, "hour",
						context);
				break;
			case STOP_SCHEDULER:
				// stops scheduler for next task
				TSSyncScheduler.getInstance().stopScheduler(false);
				break;
			case START_SYNC:
				TSSyncScheduler.getInstance().startSync(context,
						(byte) msg.arg1);
				break;
			case START_NETWORK_LISTENER:
				((TSMainService) context).registerNetworkReceiver();
				break;
			case STOP_NETWORK_LISTENER:
				((TSMainService) context).unRegisterNetworkReceiver();
				break;

			}
		}
	}

	@Override
	public void onCreate() {
		context = this;
		HandlerThread thread = new HandlerThread("TSMainServiceThread",
				Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();

		// Get the HandlerThread's Looper and use it for our Handler
		mServiceLooper = thread.getLooper();
		mServiceHandler = new ServiceHandler(mServiceLooper);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try{
		Message msg = mServiceHandler.obtainMessage();
		msg.what = intent.getIntExtra(TASK_ID, TSConstants.EOF);
		msg.arg1 = intent.getByteExtra(SYNC_TYPE_STR, TSConstants.EOF);
		msg.obj = intent.getBundleExtra(BUNDLE_INFO);
		mServiceHandler.sendMessage(msg);
		}catch(Exception ex){
			Log.e(TAG, "Error in starting service :");
		}
		// If we get killed, after returning from here, restart
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// We don't provide binding, so return null
		return null;
	}

	@Override
	public void onDestroy() {
		stopTasks();
		Log.d(TAG, "Service Stopped");

	}

	
	/**
	 * Description: Clean up all tasks
	 */
	private void stopTasks() {
		// un register network connection listener
		unRegisterNetworkReceiver();
		// stop scheduler
		TSSyncScheduler.getInstance().stopScheduler(true);
	}

	private void registerNetworkReceiver() {
		Log.i(TAG, "Start Network listener :");
		if (networkListener == null) {
			networkListener = new TSNetworkStateListener();
		}
		IntentFilter filter = new IntentFilter(
				TSConstants.ACTION_CONNECTIVITY_LISTENER);
		registerReceiver(networkListener, filter);

	}

	private void unRegisterNetworkReceiver() {
		if (networkListener != null) {
			unregisterReceiver(networkListener);
		}
		networkListener = null;
	}
}