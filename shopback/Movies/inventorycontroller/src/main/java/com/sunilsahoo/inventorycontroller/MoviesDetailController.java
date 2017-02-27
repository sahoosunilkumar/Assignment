package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.inventorycontroller.entity.MetaInfo;
import com.sunilsahoo.inventorycontroller.entity.MovieDetail;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class MoviesDetailController extends Controller{
    private static final String END_POINT = "http://api.themoviedb.org/3/movie/%1s?api_key=328c283cd27bd1877d9080ccb1604c91";



    public MoviesDetailController() {
        super();
    }

    @Override
    public void getResults(ITaskListener taskListener, Object...
            additionalInfo) {
        super.setEntity(MovieDetail.class);

        String uri = String.format(END_POINT, additionalInfo[0]);
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);

    }
}
