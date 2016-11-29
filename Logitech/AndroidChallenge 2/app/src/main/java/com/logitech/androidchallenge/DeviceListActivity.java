package com.logitech.androidchallenge;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.logitech.androidchallenge.adapter.DeviceListAdapter;
import com.logitech.androidchallenge.view.DividerItemDecoration;
import com.logitech.inventorycontoller.DeviceDetailController;
import com.logitech.inventorycontoller.ITaskListener;
import com.logitech.inventorycontoller.entity.RestResponse;
import com.logitech.inventorycontoller.entity.Device;
import com.logitech.inventorycontoller.entity.DeviceList;

import java.util.ArrayList;

public class DeviceListActivity extends AppCompatActivity implements
        ITaskListener {


    private static final String TAG = DeviceListActivity.class.getName();

    private RecyclerView mRecyclerView;
    private DeviceListAdapter mAdapter;
    private ArrayList<Device> deviceList;

    private interface ActionType {
        int UPDATE_LIST = 1;
        int SHOW_ERROR = 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        restore(savedInstanceState);
        init();
        getDevices(savedInstanceState);


    }

    /**
     * Initializes view
     */
    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        mAdapter = new DeviceListAdapter(deviceList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * restores bundle value
     *
     * @param savedInstanceState
     */
    private void restore(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            deviceList = new ArrayList<>();
        } else {
            deviceList = savedInstanceState.getParcelableArrayList
                    ("deviceList");
        }
    }

    private final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case ActionType.UPDATE_LIST:
                    mAdapter.notifyDataSetChanged();
                    break;
                case ActionType.SHOW_ERROR:
                    Toast.makeText(DeviceListActivity.this, getString(R
                            .string.no_device_found), Toast.LENGTH_SHORT)
                            .show();
                    break;
                default:
                    Log.d(TAG, "unhandled action type");
            }


        }
    };

    /**
     * retrieves device list from server
     *
     * @param savedInstanceState
     */
    private void getDevices(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            new DeviceDetailController().getDeviceList(this);
        }
    }

    @Override
    public void onSuccess(RestResponse response) {
        Log.d(TAG, response.getRequestID() + " response status " + response
                .getResponseCode() + " response " +
                response.getResponse());
        deviceList.addAll(((DeviceList) response.getResponse()).getDevices());
        handler.sendEmptyMessage(ActionType.UPDATE_LIST);
    }

    @Override
    public void onError(String error, int code) {
        Log.d(TAG, " error " + error + " error code  " +
                code);
        handler.sendEmptyMessage(ActionType.SHOW_ERROR);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("deviceList", deviceList);
    }
}
