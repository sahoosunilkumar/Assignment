package com.sunilsahoo.inventorycontoller;

import android.net.Uri;

import com.sunilsahoo.inventorycontoller.entity.WeatherInfo;
import com.sunilsahoo.inventorycontoller.utils.ControllerConstants;

public final class DeviceDetailController extends Controller{
    private static final String YQL_WEATHER_ENDPOINT_AUTHORITY = "query.yahooapis.com";
    private static final String YQL_WEATHER_ENDPOINT_PATH = "/v1/public/yql";
    private static final String TAG = DeviceDetailController.class.getName();


    public DeviceDetailController() {
        super();
    }

    /**
     * sends getdevice request to server
     * @param taskListener
     */
    public void getWeatherInfo(String placeName, ITaskListener taskListener){
        //set entity type to parse the data to DeviceList object
        super.setEntity(WeatherInfo.class);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority(YQL_WEATHER_ENDPOINT_AUTHORITY);
        builder.path(YQL_WEATHER_ENDPOINT_PATH);
        builder.appendQueryParameter("q", "select * from weather.forecast where woeid in" +
                "(select woeid from geo.places(1) where text=\"" +
                placeName +
                "\")");
        builder.appendQueryParameter("format","json");
        super.execute(ControllerConstants.RequestCodes.GET_DEVICE_LSIT, builder.build().toString(), ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);
    }

}
