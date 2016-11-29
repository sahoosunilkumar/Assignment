package com.tesco.inventory.networkmanager;


public class TSJSONParams {
	public static final String ID = "ID";
	public static final String HTTP_METHOD = "METHOD";
	public static final String LIMIT = "limit";
	public static final String FROM_DATE = "from";
	public static final String TO_DATE = "to";
	public static final String OFFSET = "offset";

	public static final class Sales {
		public static final String TRANSACTION_ID 			= "transactionId";
		public static final String TOTAL_PRICE 				= "totalPrice";
		public static final String PAYMENT_MODE 			= "mop";
		public static final String CREATED_BY 				= "createdBy";
		public static final String DEVICE_CREATION_TIME 	= "createdAt";
		public static final String MAPPING_ITEMS_OBJECT_NAME= "items";
		public static final String ITEM_ID 					= "id";
		public static final String ITEM_NAME 				= "name";
		public static final String ITEM_PRICE 				= "price";
		public static final String ITEM_QUANTITY 			= "quantity";
		public static final String CUSTOMER_NAME			= "customerName";
	}

}
