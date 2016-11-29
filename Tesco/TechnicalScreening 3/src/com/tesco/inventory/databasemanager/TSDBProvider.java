package com.tesco.inventory.databasemanager;

import java.io.File;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.tesco.inventory.databasemanager.TSDBProviderMetaData.Items;
import com.tesco.inventory.databasemanager.TSDBProviderMetaData.Sales;
import com.tesco.inventory.databasemanager.TSDBProviderMetaData.SalesMapping;

public class TSDBProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher;
    private static final int SALE_TYPE_LIST = 1;
    private static final int SALE_TYPE_ONE = 2;
    private static final int SALE_MAPPING_TYPE_LIST = 3;
    private static final int SALE_MAPPING_TYPE_ONE = 4;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TSDBProviderMetaData.AUTHORITY, "sales",
                SALE_TYPE_LIST);
        sUriMatcher.addURI(TSDBProviderMetaData.AUTHORITY, "sales/#",
                SALE_TYPE_ONE);
        sUriMatcher.addURI(TSDBProviderMetaData.AUTHORITY, "salesmapping",
                SALE_MAPPING_TYPE_LIST);
        sUriMatcher.addURI(TSDBProviderMetaData.AUTHORITY, "salesmapping/#",
                SALE_MAPPING_TYPE_ONE);
    }

    private static final HashMap<String, String> sSalesProjectionMap;
    static {

        sSalesProjectionMap = new HashMap<String, String>();
        sSalesProjectionMap.put(Sales.KEY_ROWID, Sales.KEY_ROWID);
        sSalesProjectionMap.put(Sales.TRANSACTION_NO, Sales.TRANSACTION_NO);
        sSalesProjectionMap.put(Sales.PAYMENT_MODE, Sales.PAYMENT_MODE);
        sSalesProjectionMap.put(Sales.TOTAL_PRICE, Sales.TOTAL_PRICE);
        sSalesProjectionMap.put(Sales.CUSTOMER_NAME, Sales.CUSTOMER_NAME);
        sSalesProjectionMap.put(Sales.DEVICE_TIME_STAMP,
                Sales.DEVICE_TIME_STAMP);
        sSalesProjectionMap.put(Sales.CREATED_BY, Sales.CREATED_BY);
    }

    private static final HashMap<String, String> sSalesmappingProjectionMap;
    static {

        sSalesmappingProjectionMap = new HashMap<String, String>();
        sSalesmappingProjectionMap.put(SalesMapping.KEY_ROWID,
                SalesMapping.KEY_ROWID);
        sSalesmappingProjectionMap.put(SalesMapping.TRANSACTION_NO,
                SalesMapping.TRANSACTION_NO);
        sSalesmappingProjectionMap.put(SalesMapping.ITEM_ID,
                SalesMapping.ITEM_ID);
    }

    private static class TSDBHelper extends SQLiteOpenHelper {

        public TSDBHelper(Context c) {
            super(c, TSDBProviderMetaData.DATABASE_NAME, null,
                    TSDBProviderMetaData.DATABSE_VERSION);
        }

        private static final String SQL_QUERY_CREATE_ITEMS = "CREATE TABLE " +

        Items.TABLE_NAME + " ( " + Items.KEY_ROWID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Items.ITEM_ID
                + " TEXT NOT NULL, " + Items.ITEM_NAME + " TEXT NOT NULL, "
                + Items.ITEM_PRICE + " TEXT NOT NULL, "
                + Items.DEVICE_CREATION_TIME + " INTEGER ,"
                + Items.SERVER_CREATION_TIME + " INTEGER ," + Items.CREATED_BY
                + " TEXT );";

        private static final String SQL_QUERY_CREATE_SALES = "CREATE TABLE "
                + Sales.TABLE_NAME + " ( " + Sales.KEY_ROWID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Sales.TRANSACTION_NO
                + " TEXT NOT NULL ,"
                + Sales.PAYMENT_MODE + " INTEGER (1) DEFAULT 0 ,"
                + Sales.TOTAL_PRICE + " TEXT NOT NULL ," + Sales.CUSTOMER_NAME
                + " TEXT ," + Sales.DEVICE_TIME_STAMP
                + " INTEGER NOT NULL," + Sales.CREATED_BY + " TEXT )";
        
        private static final String SQL_QUERY_CREATE_SALES_MAPPING = "CREATE TABLE "
                + SalesMapping.TABLE_NAME
                + " ( "
                + SalesMapping.KEY_ROWID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SalesMapping.TRANSACTION_NO
                + " TEXT NOT NULL ,"
                + SalesMapping.ITEM_ID
                + " TEXT ) ";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_QUERY_CREATE_ITEMS);
            db.execSQL(SQL_QUERY_CREATE_SALES);
            db.execSQL(SQL_QUERY_CREATE_SALES_MAPPING);
        }

        private static final String SQL_QUERY_DROP_ITEMS = "DROP TABLE IF EXISTS "
                + Items.TABLE_NAME + ";";

        private static final String SQL_QUERY_DROP_SALES = "DROP TABLE IF EXISTS "
                + Sales.TABLE_NAME + ";";

        private static final String SQL_QUERY_DROP_Sales_MAPPING = "DROP TABLE IF EXISTS "
                + SalesMapping.TABLE_NAME + ";";

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            db.execSQL(SQL_QUERY_DROP_ITEMS);
            db.execSQL(SQL_QUERY_DROP_SALES);
            db.execSQL(SQL_QUERY_DROP_Sales_MAPPING);
            onCreate(db);
        }

    }

    private TSDBHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new TSDBHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int count = 0;
        String rowId;
        switch (sUriMatcher.match(uri)) {

        case SALE_TYPE_LIST:
            count = db.delete(Sales.TABLE_NAME, where, whereArgs);
            break;

        case SALE_TYPE_ONE:
            rowId = uri.getPathSegments().get(1);
            count = db.delete(
                    Sales.TABLE_NAME,
                    Sales.KEY_ROWID
                            + " = "
                            + rowId
                            + (!TextUtils.isEmpty(where) ? " AND (" + where
                                    + ")" : ""), whereArgs);
            break;

        case SALE_MAPPING_TYPE_LIST:
            count = db.delete(SalesMapping.TABLE_NAME, where, whereArgs);
            break;

        case SALE_MAPPING_TYPE_ONE:
            rowId = uri.getPathSegments().get(1);
            count = db.delete(
                    SalesMapping.TABLE_NAME,
                    SalesMapping.KEY_ROWID
                            + " = "
                            + rowId
                            + (!TextUtils.isEmpty(where) ? " AND (" + where
                                    + ")" : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {

        switch (sUriMatcher.match(uri)) {
        case SALE_TYPE_LIST:
            return Sales.CONTENT_ITEM_TYPE;
        case SALE_TYPE_ONE:
            return Sales.CONTENT_ITEM_TYPE;
        case SALE_MAPPING_TYPE_LIST:
            return SalesMapping.CONTENT_ITEM_TYPE;
        case SALE_MAPPING_TYPE_ONE:
            return SalesMapping.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = getWritableDatabase();
        // Log.i(TAG, "MAtch URI ::::" +
        // sUriMatcher.match(uri)+"~~~path~~~"+db.getPath());
        switch (sUriMatcher.match(uri)) {

        case SALE_TYPE_LIST:

            long rowSalesId = db.insert(Sales.TABLE_NAME, null, values);
            if (rowSalesId > 0) {
                Uri articleUri = ContentUris.withAppendedId(Sales.CONTENT_URI,
                        rowSalesId);

                getContext().getContentResolver()
                        .notifyChange(articleUri, null);
                return articleUri;
            }
        case SALE_MAPPING_TYPE_LIST:

            long rowMappingId = db
                    .insert(SalesMapping.TABLE_NAME, null, values);
            if (rowMappingId > 0) {
                Uri articleUri = ContentUris.withAppendedId(
                        SalesMapping.CONTENT_URI, rowMappingId);

                getContext().getContentResolver()
                        .notifyChange(articleUri, null);
                return articleUri;
            }

        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);

        }
        // close(db);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {

        case SALE_TYPE_LIST:
            builder.setTables(Sales.TABLE_NAME);
            builder.setProjectionMap(sSalesProjectionMap);
            break;

        case SALE_TYPE_ONE:
            builder.setTables(Sales.TABLE_NAME);
            builder.setProjectionMap(sSalesProjectionMap);
            builder.appendWhere(selection);
            break;

        case SALE_MAPPING_TYPE_LIST:
            builder.setTables(SalesMapping.TABLE_NAME);
            builder.setProjectionMap(sSalesmappingProjectionMap);
            break;

        case SALE_MAPPING_TYPE_ONE:
            builder.setTables(SalesMapping.TABLE_NAME);
            builder.setProjectionMap(sSalesmappingProjectionMap);
            builder.appendWhere(selection);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = getReadableDatabase();
        Cursor queryCursor = builder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        // close(db);
        return queryCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where,
            String[] whereArgs) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int count = 0;

        switch (sUriMatcher.match(uri)) {

        case SALE_TYPE_LIST:
            count = db.update(Sales.TABLE_NAME, values, where, whereArgs);
            break;

        case SALE_TYPE_ONE:
            String rowIdSales = uri.getPathSegments().get(1);
            count = db.update(Sales.TABLE_NAME, values, "_id" + " = "
                    + rowIdSales
                    + (!TextUtils.isEmpty(where) ? " AND (" + ")" : ""),
                    whereArgs);
            break;

        case SALE_MAPPING_TYPE_LIST:
            count = db.update(Sales.TABLE_NAME, values, where, whereArgs);
            break;

        case SALE_MAPPING_TYPE_ONE:
            String rowIdSalesMapping = uri.getPathSegments().get(1);
            count = db.update(Sales.TABLE_NAME, values, "_id" + " = "
                    + rowIdSalesMapping
                    + (!TextUtils.isEmpty(where) ? " AND (" + ")" : ""),
                    whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        // close(db);
        return count;
    }

    private SQLiteDatabase getWritableDatabase() {
        File sqliteDBFile = null;
        SQLiteDatabase sqliteDB = null;
        try {
            sqliteDB = mDbHelper.getWritableDatabase();
            sqliteDBFile = new File(sqliteDB.getPath());
            if (!sqliteDBFile.exists()) {
                onCreate();
                sqliteDB = mDbHelper.getWritableDatabase();
            }
        } finally {
            sqliteDBFile = null;
        }
        return sqliteDB;
    }

    private SQLiteDatabase getReadableDatabase() {
        File sqliteDBFile = null;
        SQLiteDatabase sqliteDB = null;
        try {
            sqliteDB = mDbHelper.getReadableDatabase();
            sqliteDBFile = new File(sqliteDB.getPath());
            if (!sqliteDBFile.exists()) {
                onCreate();
                sqliteDB = mDbHelper.getWritableDatabase();
            }
        } finally {
            sqliteDBFile = null;
        }
        return sqliteDB;
    }

}
