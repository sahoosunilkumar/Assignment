package com.sunilsahoo.inventorycontroller;

import android.net.Uri;

import com.sunilsahoo.inventorycontroller.entity.ZipcodeResponse;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class SearchLocationController extends Controller{
    private static final String END_POINT = "http://yourmoneyisnowmymoney.com/api/zipcodes/?";
    private static final String TAG = SearchLocationController.class.getName();


    public SearchLocationController() {
        super();
    }

    /**
     * sends getlocations based on zipcode request to server
     * @param taskListener
     */
    public void getResults(String zipCode, ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(ZipcodeResponse.class);

        String uri = Uri.parse(END_POINT)
                .buildUpon()
                .appendQueryParameter("zipcode", zipCode)
                .build().toString();
        super.execute(ControllerConstants.RequestCodes.SEARCH_ZIPCODE, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
