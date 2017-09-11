package com.sixt.inventorycontroller;

import com.sixt.inventorycontroller.entity.CarInfo;
import com.sixt.inventorycontroller.utils.ControllerConstants;

public final class FetchCarsController extends Controller{
    private static final String END_POINT = "http://www.codetalk.de/cars.json";
    private static final String TAG = FetchCarsController.class.getSimpleName();


    public FetchCarsController() {
        super();
    }

    /**
     * sends getlocations based on zipcode request to server
     * @param taskListener
     */
    public void getResults(ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(CarInfo[].class);



        super.execute(ControllerConstants.RequestCodes.FETCH_CARS, END_POINT, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
