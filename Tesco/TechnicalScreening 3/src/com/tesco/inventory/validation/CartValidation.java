package com.tesco.inventory.validation;

import java.util.ArrayList;

import com.tesco.inventory.beans.Item;
import com.tesco.inventory.utils.TSConstants;

public class CartValidation {
	/**
	 * validate minimum and maximum number of items allowed for a cart
	 * @param numberOfItemsInCart
	 * @return
	 */
	public static boolean validateCartItemsCount(ArrayList<Item> itemList) {
		if ((itemList != null) && (itemList.size() <= TSConstants.Limits.MAX_NO_OF_ITEMS_PER_CART)
				&& (itemList.size() > 0)) {
			return true;
		} else {
			return false;
		}
	}
}
