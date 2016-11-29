package com.tesco.inventory.utils;


public class TSConstants {

	public static final byte INDEX_OF_FIRST_ELEMENT = 0;
	public static final byte EOF = -1;
	public static final byte FALSE = 0;
	public static final byte TRUE = 1;
	public static final byte DEFAULT_VALUE_OF_INT = 0;
	public static String URL_SEPARATOR = "/";
	public static final String MATRIX_PARAMS_KEY_PAIR_SEPARATOR = ";";
	public static final String MATRIX_PARAMS_KEY_VALUE_SEPARATOR = "=";
	public static final String ACTION_CONNECTIVITY_LISTENER = "android.net.conn.CONNECTIVITY_CHANGE";

	/**
	 * 
	 * Description : All types of request codes which will be used for Http
	 * request and request which will be used in application
	 */
	public static final class RequestCodes {
		public static final short ADD_CART 				 = 171;
		public static final short REFRESH                = 172;

	}

	/**
	 * 
	 * Description : Stores all types of response codes
	 */
	public static final class ResponseCodes {
		public static final int UNAUTHORIZED            = 401;
		public static final int NO_NETWORK_AVAIL 		= 499;
		public static final int SUCCESS 				= 200;
	}
	
	/**
     * 
     * Description : Contains different types of payment mode
     */
    public static final class PaymentModes {
        public static final byte CASH = 0;
        public static final byte CREDIT_CARD = 1;
        public static final byte GCASH = 2;
    }

	/**
	 * 
	 * Description : This class contains all types of Http methods which will be
	 * used in our application
	 */
	public static final class HttpMethods {
		public static final String HTTP_GET 		= "GET";
		public static final String HTTP_POST 		= "POST";
		public static final String HTTP_PUT 		= "PUT";
		public static final String HTTP_DELETE 	= "DELETE";
	}

	/**
	 * 
	 * Description	: Contains Http Urls
	 */
	public static final class HttpUrls {
		public static final String SALES 			= "http://tesco/cataloguemgmt/orders/";
	}

	/**
	 * 
	 * Description : Contains all types of Database operation
	 */
	public static final class DBOperations {
		public static final byte INSERT 	= 1;
		public static final byte UPDATE 	= 2;
		public static final byte DELETE 	= 3;
		public static final byte UNKNOWN 	= 4;
	}

	/**
	 * 
	 * Description : Contains all types of sync
	 */
	public static final class SyncType {
		public static final byte SALES 				= 1;
		public static final byte COMPLETE 			= 2;
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
		/**
		 * Maximum limit for retry
		 */
		public static final int MAX_RETRY_COUNT = 3;
		//Maximum no of carts/item
        public static final int MAX_NO_OF_CARTS_PER_REQUEST = 10;
        //Maximum no of items/cart
        public static final int MAX_NO_OF_ITEMS_PER_CART = 50;
	}

}
