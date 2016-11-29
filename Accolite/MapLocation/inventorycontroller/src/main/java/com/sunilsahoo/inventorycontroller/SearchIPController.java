package com.sunilsahoo.inventorycontroller;

import android.net.Uri;

import com.sunilsahoo.inventorycontroller.entity.IPSearchResponse;
import com.sunilsahoo.inventorycontroller.entity.SearchLocation;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class SearchIPController extends Controller{
    private static final String END_POINT = "http://geo.groupkt.com/ip/%1s/json";
    private static final String TAG = SearchIPController.class.getName();


    public SearchIPController() {
        super();
    }

    /**
     * sends getdevice request to server
     * @param taskListener
     */
    public void getResults(String ip, ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(IPSearchResponse.class);

        String uri = String.format(END_POINT, ip);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
