package com.logitech.inventorycontoller;

import com.logitech.inventorycontoller.entity.DeviceList;
import com.logitech.inventorycontoller.utils.ControllerConstants;

public final class DeviceDetailController extends Controller{
    private static final String GET_DEVICE_LIST_URL =
            "https://s3.amazonaws.com/harmony-recruit/devices.json";
    private static final String TAG = DeviceDetailController.class.getName();


    public DeviceDetailController() {
        super();
    }

    /**
     * sends getdevice request to server
     * @param taskListener
     */
    public void getDeviceList(ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(DeviceList.class);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, GET_DEVICE_LIST_URL, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
