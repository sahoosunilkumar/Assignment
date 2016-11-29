package com.myntra.assignment.controller;

import com.myntra.assignment.controller.entity.ImageList;
import com.myntra.assignment.controller.utils.ControllerConstants;

public final class ImageListController extends Controller{
    private static final String GET_DEVICE_LIST_URL =
            "https://api.flickr.com/services/feeds/photos_public.gne?&format=json&per_page=50&nojsoncallback=1";
    private static final String TAG = ImageListController.class.getName();


    public ImageListController() {
        super();
    }

    /**
     * sends getdevice request to server
     * @param taskListener
     */
    public void getImageList(ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(ImageList.class);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, GET_DEVICE_LIST_URL, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
