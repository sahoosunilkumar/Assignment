package com.artoo.eventmanager.utils;

import com.artoo.eventmanager.search.model.Location;

/**
 * Created by sunilkumarsahoo on 10/28/17.
 */

public class Utility {
    private static final String LATITUDE_SEPARATOR = "latitude : ";
    private static final String LONGITUDE_SEPARATOR = "longitude : ";
    private static final String ADDRESS_SEPARATOR = "address : ";
    private static final String VALUE_SEPARATOR = ",";

    private Utility() {

    }

    public static String convertToString(Location location) {
        return LATITUDE_SEPARATOR + location.getLatitude() + VALUE_SEPARATOR + LONGITUDE_SEPARATOR + location.getLongitude() + VALUE_SEPARATOR + ADDRESS_SEPARATOR + location.getAddress();
    }

    public static Location convertToLocation(String locationStr) {
        if (locationStr == null) {
            return null;
        }
        Location location = new Location();
        String[] dataArr = locationStr.split(VALUE_SEPARATOR);
        location.setLatitude(Double.parseDouble(dataArr[0]));
        location.setLongitude(Double.parseDouble(dataArr[1]));
        location.setAddress(dataArr[3]);
        return location;
    }
}
