package com.sunilsahoo.inventorycontroller;

import android.net.Uri;

import com.sunilsahoo.inventorycontroller.entity.SearchLocation;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class SearchLocationController extends Controller{
    private static final String END_POINT = "http://services.groupkt.com/state/search/IND?";
    private static final String TAG = SearchLocationController.class.getName();


    public SearchLocationController() {
        super();
    }

    /**
     * sends getdevice request to server
     * @param taskListener
     */
    public void getResults(String placeName, ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(SearchLocation.class);

        String uri = Uri.parse(END_POINT)
                .buildUpon()
                .appendQueryParameter("text", placeName)
                .build().toString();
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
