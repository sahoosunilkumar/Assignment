package com.tesco.inventory.databasemanager;

import android.net.Uri;

public class TSDBProviderMetaData {

	public static final String AUTHORITY = "com.tesco.inventory.TSDBProvider";
	public static final String DATABASE_NAME = "TS";
	public static final int DATABSE_VERSION = 1;

	public static final class Items implements ItemColumns {
		public static final String _ID = "_id";
		public static final String PATH = "items";
		public static final String TABLE_NAME = "items";
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + PATH);
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String DEFAULT_SORT_ORDER = ItemColumns.ITEM_NAME
				+ " ASC";
	}

	public static final class Sales implements SalesColumns {
		public static final String TABLE_NAME = "sales";
		public static final String PATH = "sales";
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + PATH);
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String DEFAULT_SORT_ORDER = KEY_ROWID
				+ " ASC";
	}

	public static final class SalesMapping implements SalesMappingColumns {
		public static final String TABLE_NAME = "salesmapping";
		public static final String PATH = "salesmapping";
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + PATH);
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
				+ AUTHORITY + "." + PATH;
		public static final String DEFAULT_SORT_ORDER = KEY_ROWID
				+ " ASC";
	}

	public interface ItemColumns {
		public static final String KEY_ROWID = "_id";
		public static final String ITEM_ID = "ItemID";
		public static final String ITEM_NAME = "ItemName";
		public static final String ITEM_PRICE = "ItemPrice";
		public static final String DEVICE_CREATION_TIME = "ItemDeviceTimeStamp";
		public static final String SERVER_CREATION_TIME = "ItemServerTimeStamp";
		public static final String CREATED_BY = "ItemCreatedBy";

	}
	
	public interface SalesColumns {
		public static final String KEY_ROWID = "_id";
		public static final String TRANSACTION_NO = "TxNo";
		public static final String PAYMENT_MODE = "PaymentMode";
		public static final String TOTAL_PRICE = "ToatalPrice";
		public static final String CUSTOMER_NAME = "CustomerName";
		public static final String DEVICE_TIME_STAMP = "DeviceTimeStamp";
		public static final String CREATED_BY = "CreatedBy";
	}

	public interface SalesMappingColumns {
		public static final String KEY_ROWID = "_id";
		public static final String TRANSACTION_NO = "TxNo";		
		public static final String ITEM_ID = "ItemID";
	}

}