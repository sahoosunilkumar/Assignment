package com.logitech.networkmanager.utils;


public class NetworkConstants {

    public static final byte EOF = -1;
    public static final byte DEFAULT_VALUE_OF_INT = 0;
    public static final String ACTION_CONNECTIVITY_LISTENER = "android.net" +
			".conn.CONNECTIVITY_CHANGE";
    public static String URL_SEPARATOR = "/";

    /**
     * Description : Stores all types of response codes
     */
    public static final class ResponseCodes {
        public static final int SUCCESS = 200;
    }

    /**
     * Description	: Stores maximum and minimum threshold value for all tasks
     */
    public static final class Limits {
        /**
         * No of byte that a device will receive at one chunk
         */
        public static final int CHUNK_SIZE = 1024;
        /**
         * Http retry interval in milliseconds = 30 seconds
         */
        public static final int RETRY_INTERVAL = 30 * 1000;
        /**
         * Http connection time out in milli seconds ie 5 seconds less than
         * retry interval
         */
        public static final int TIME_OUT = RETRY_INTERVAL - (5 * 1000);
    }

}
