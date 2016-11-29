package com.tesco.inventory.networkmanager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.tesco.inventory.beans.Inventory;
import com.tesco.inventory.beans.NetResponse;
import com.tesco.inventory.utils.TSConstants;
import com.tesco.inventory.utils.TSConstants.RequestCodes;
import com.tesco.inventory.utils.TSUtility;


public final class TSResponseProcessor {

	/**
	 * Description: Parses inventory data and add to the list
	 * 
	 * @param response
	 * @param itemList
	 * @throws Exception
	 */
	public void parseHttpResponse(NetResponse responseObj, String response) {
		ArrayList<Inventory> responseDataInList = null;
		Object json = null;
		JSONArray jsonArray = null;
		try {

			responseDataInList = new ArrayList<Inventory>();
			json = new JSONTokener(response).nextValue();
			if (json instanceof JSONArray) {
				jsonArray = (JSONArray) json;
				StringBuffer serverIDS = new StringBuffer();
				for (int i = 0; i < jsonArray.length(); i++) {
					// GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
					if(jsonArray.get(i) instanceof JSONObject){
					parseJSONObjectToInventory(responseObj.getRequestID(), responseDataInList,
							jsonArray.getJSONObject(i));
					}else{
						if(i == 0){
							serverIDS.append(String.valueOf(jsonArray.get(i)));
						}else{
							serverIDS.append(TSConstants.MATRIX_PARAMS_KEY_PAIR_SEPARATOR).append(String.valueOf(jsonArray.get(i)));
						}
					}
				}
				if(TSUtility.isNotEmpty(serverIDS.toString())){
					responseObj.setServerID(serverIDS.toString());
				}
			} else {
				if(json instanceof JSONObject){
				parseJSONObjectToInventory(responseObj.getRequestID(), responseDataInList,
						(JSONObject) json);
				}else{
					responseObj.setServerID(String.valueOf(json));
				}
			}

		} catch (Exception e1) {
			responseDataInList = null;
			json = null;
			jsonArray = null;
			e1.printStackTrace();
		}
		finally{
			if (responseDataInList != null) {
				responseObj.setResponseData(responseDataInList);
			}
		}
	}

	/**
	 * Create Inventory object from json object and adds to the inventory list
	 * 
	 * @param requestID
	 * @param responseDataInList
	 * @param jo
	 */
	private void parseJSONObjectToInventory(int requestID,
			ArrayList<Inventory> responseDataInList, JSONObject jo) {
		if ((requestID == RequestCodes.ADD_CART)){
			responseDataInList.add(parseSale(jo));
		}
	}

    private Inventory parseSale(JSONObject jo) {
        // TODO write parse algorithm to get sales detail
        return null;
    }

	

}
