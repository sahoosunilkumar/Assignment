/**
 * Includes all types of Database Operation Implementation
 */
package com.tesco.inventory.databasemanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.tesco.inventory.beans.Cart;
import com.tesco.inventory.beans.Inventory;
import com.tesco.inventory.utils.TSConstants;
import com.tesco.inventory.utils.TSConstants.DBOperations;
import com.tesco.inventory.utils.TSUtility;

public class TSDBOperation {
    private static final String TAG = TSDBOperation.class.getName();

    public static void performDBOperation(Inventory inventory, Context context,
            byte operationType) throws Exception {
        synchronized (inventory) {
            switch (operationType) {
            case DBOperations.INSERT:
                if (inventory instanceof Cart) {
                    // insert Sale
                    insertSale((Cart) inventory, context);
                }
                break;
            case DBOperations.DELETE:
                // delete Sale
                deleteSale((Cart) inventory, context);
            default:
                Log.d(TAG, "Unhandled action");
            }

        }
    }

    /**
     * inserts the sales detail
     * 
     * @param item
     * @param context
     * @throws Exception
     */
    private static void insertSale(Cart sale, Context context) throws Exception {
        if (sale != null) {
            ContentValues initialValues = new ContentValues();
            initialValues.put(TSDBProviderMetaData.Sales.TRANSACTION_NO,
                    sale.getTransactionNumber());
            initialValues.put(TSDBProviderMetaData.Sales.PAYMENT_MODE,
                    sale.getPaymentMode());
            initialValues.put(TSDBProviderMetaData.Sales.TOTAL_PRICE,
                    sale.getTotalPrice());
            initialValues.put(TSDBProviderMetaData.Sales.CUSTOMER_NAME,
                    sale.getCustomerName());
            initialValues.put(TSDBProviderMetaData.Sales.DEVICE_TIME_STAMP,
                    sale.getDevicetimeStamp());
            initialValues.put(TSDBProviderMetaData.Sales.CREATED_BY,
                    sale.getCreatedBy());

            context.getContentResolver().insert(
                    TSDBProviderMetaData.Sales.CONTENT_URI, initialValues);
            // insert sales mapping
            insertSalesMapping(sale, context);

        }
    }

    /**
     * Deletes a sale- if row id present then it will delete on basis of row id
     * otherwise deletion will occur depending on category id basis
     * 
     * @param item
     * @param context
     */
    private static void deleteSale(Cart sale, Context context) throws Exception {
        if (sale.getRowID() != TSConstants.EOF) {
            Uri uri = ContentUris.withAppendedId(
                    TSDBProviderMetaData.Sales.CONTENT_URI, sale.getRowID());
            context.getContentResolver().delete(uri, null, null);
        } else {
            String whereClause = TSDBProviderMetaData.Sales.TRANSACTION_NO
                    + "= '" + sale.getTransactionNumber() + "'";
            context.getContentResolver()
                    .delete(TSDBProviderMetaData.Sales.CONTENT_URI,
                            whereClause, null);
        }
        // delete sales mapping
        deleteSalesMapping(sale, context);
    }

    /**
     * inserts sales mapping
     * 
     * @param discount
     * @param context
     */
    private static void insertSalesMapping(Cart sale, Context context)
            throws Exception {
        List<ContentValues> contentValueList = new ArrayList<ContentValues>();
        ContentValues[] contentValueArray = null;
        if (TSUtility.isNotEmpty(sale.getTransactionNumber())
                && (sale.getCartItems() != null)
                && !sale.getCartItems().isEmpty()) {
            for (int i = 0; i < sale.getCartItems().size(); i++) {
                ContentValues initialValues = new ContentValues();
                initialValues.put(
                        TSDBProviderMetaData.SalesMapping.TRANSACTION_NO,
                        sale.getTransactionNumber());
                initialValues.put(TSDBProviderMetaData.SalesMapping.ITEM_ID,
                        sale.getCartItems().get(i).getID());

                contentValueList.add(initialValues);
            }
            contentValueArray = new ContentValues[contentValueList.size()];
            contentValueList.toArray(contentValueArray);
            context.getContentResolver().bulkInsert(
                    TSDBProviderMetaData.SalesMapping.CONTENT_URI,
                    contentValueArray);
        } else {
            throw new Exception(
                    "To insert, both Discount ID and mapping ids should not be empty");
        }
    }

    /**
     * Deletes sales mapping- if row id present then it will delete on basis of
     * row id otherwise deletion will occur depending on transaction number
     * basis
     * 
     * @param item
     * @param context
     */
    private static int deleteSalesMapping(Cart sale, Context context)
            throws Exception {
        if (TSUtility.isNotEmpty(sale.getTransactionNumber())) {
            String whereClause = TSDBProviderMetaData.SalesMapping.TRANSACTION_NO
                    + "= '" + sale.getTransactionNumber() + "'";
            return context.getContentResolver().delete(
                    TSDBProviderMetaData.SalesMapping.CONTENT_URI,
                    whereClause, null);
        } else {
            throw new Exception("To delete Discount ID canot be empty");
        }
    }

    /**
     * returns the cursor of matching records
     * 
     * @param uri
     * @param context
     * @param whereClause
     * @param cloumns
     * @return
     */
    public static Cursor searchForID(Uri uri, Context context,
            String whereClause, String[] cloumns, String sortOrder) {

        return context.getContentResolver().query(uri, cloumns, whereClause,
                null, sortOrder);
    }

}
