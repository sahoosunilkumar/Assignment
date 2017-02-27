package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.inventorycontroller.entity.Configuration;
import com.sunilsahoo.inventorycontroller.entity.MetaInfo;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class ConfigurationController extends Controller{
    private static final String END_POINT = "https://api.themoviedb.org/3/configuration?api_key=328c283cd27bd1877d9080ccb1604c91";


    private static final String TAG = ConfigurationController.class.getName();


    public ConfigurationController() {
        super();
    }

    @Override
    public void getResults(ITaskListener taskListener, Object...
            additionalInfo) {
        super.setEntity(Configuration.class);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, END_POINT, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);

    }
}
