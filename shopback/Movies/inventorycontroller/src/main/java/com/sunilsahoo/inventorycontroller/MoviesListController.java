package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.inventorycontroller.entity.MetaInfo;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class MoviesListController extends Controller{
    private static final String END_POINT = "http://api.themoviedb.org/3/discover/movie?api_key=328c283cd27bd1877d9080ccb1604c91&primary_release_date.lte=2016-12-31&sort_by=release_date.desc&page=%1s";


    private static final String TAG = MoviesListController.class.getName();


    public MoviesListController() {
        super();
    }

    @Override
    public void getResults(ITaskListener taskListener, Object...
            additionalInfo) {
        super.setEntity(MetaInfo.class);

        String uri = String.format(END_POINT, additionalInfo[0]);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);

    }
}
