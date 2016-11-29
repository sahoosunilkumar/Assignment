package com.tesco.inventory.networkmanager;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.tesco.inventory.beans.Cart;
import com.tesco.inventory.beans.Inventory;
import com.tesco.inventory.beans.Item;
import com.tesco.inventory.beans.NetRequest;
import com.tesco.inventory.utils.TSConstants;

public final class TSRequestProcessor {
	private static final String TAG = TSRequestProcessor.class.getName();

	/**
	 * This method is used to create JSON request.
	 * 
	 * @param requestID
	 *            - Request id to create the Request.
	 * @param params
	 *            - Parameters required to create the request.
	 * @return
	 */
	public String createJSONRequest(NetRequest requestObj, int requestID, Inventory inventory) {
		String request = null;
		switch (requestID) {
		case TSConstants.RequestCodes.ADD_CART:
			request = createCartDetailRequest((Cart) inventory);
			break;
		default:
		    Log.d(TAG, "Unhandled request type ");
			break;
		}

		return request;
	}

	/**
	 * create json string request for add/update cart
	 * @param discount
	 * @return
	 */
	private String createCartDetailRequest(Cart sale) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			double totalDiscount = 0;
			try {
				totalDiscount = Float.parseFloat(sale.getTotalPrice());
				totalDiscount = totalDiscount
						- Double.parseDouble(sale.getTotalPrice());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			object.put(TSJSONParams.Sales.TRANSACTION_ID,
					sale.getTransactionNumber());
			object.put(TSJSONParams.Sales.TOTAL_PRICE, sale.getTotalPrice());
			object.put(TSJSONParams.Sales.PAYMENT_MODE, sale.getPaymentMode());
			object.put(TSJSONParams.Sales.CREATED_BY, sale.getCreatedBy());
			object.put(TSJSONParams.Sales.DEVICE_CREATION_TIME,
					sale.getDevicetimeStamp());
			object.put(TSJSONParams.Sales.CUSTOMER_NAME,
					sale.getCustomerName());
			JSONArray itemList = new JSONArray();
			Item item = null;
			if ((sale.getCartItems() != null)
					&& (!sale.getCartItems().isEmpty())) {
				for (int i = 0; i < sale.getCartItems().size(); i++) {
					item = sale.getCartItems().get(i);
					JSONObject itemObj = new JSONObject();

					itemObj.put(TSJSONParams.Sales.ITEM_ID, item.getID());
					itemObj.put(TSJSONParams.Sales.ITEM_NAME, item.getName());
					itemObj.put(TSJSONParams.Sales.ITEM_PRICE,
							item.getPrice());
					itemObj.put(TSJSONParams.Sales.ITEM_QUANTITY,
							item.getQuantity());

					itemList.put(itemObj);
				}
				object.put(TSJSONParams.Sales.MAPPING_ITEMS_OBJECT_NAME,
						itemList);
			}
			jsonArray.put(object);

		} catch (Exception ex) {
			Log.e(TAG, "Error in creating cart JSON request " + ex.getMessage());
		}
		Log.d(TAG, "JSON Request For adding cart :" + jsonArray.toString());
		return jsonArray.toString();
	}

}
