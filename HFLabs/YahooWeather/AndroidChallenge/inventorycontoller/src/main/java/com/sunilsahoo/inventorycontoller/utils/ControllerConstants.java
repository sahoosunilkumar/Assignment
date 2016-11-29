/**
 * Responsibility: Defines all constants used in controller
 */
package com.sunilsahoo.inventorycontoller.utils;


public class ControllerConstants {

    /**
     * Description : All types of request codes which will be used for Http
     * request and request which will be used in application
     */
    public interface RequestCodes {
        short GET_DEVICE_LSIT = 171;

    }

    /**
     * Description : Stores all types of response codes
     */
    public static final class ResponseCodes {
        public static final int SUCCESS = 200;
    }


    /**
     * Description : This class contains all types of Http methods which
     * will be
     * used in our application
     */
    public interface HttpMethods {
        String HTTP_GET = "GET";
        String HTTP_POST = "POST";
        String HTTP_PUT = "PUT";
        String HTTP_DELETE = "DELETE";
    }

}
