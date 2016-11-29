/**
 * Contains sales related info like item details and sale id
 * 
 */
package com.tesco.inventory.beans;

import java.util.ArrayList;

import com.tesco.inventory.utils.TSConstants;

public class Cart implements Inventory {	
	private static final long serialVersionUID = 7647602195202673459L;
	private byte paymentMode = TSConstants.PaymentModes.CASH;
	private long devicetimeStamp = System.currentTimeMillis();
	private long rowID = TSConstants.EOF;
	private String transactionNumber;
	private String totalPrice  = "0.0";
	private String customerName;
	private String createdBy;
	private ArrayList<Item> itemsListForCart = new ArrayList<Item>();

	public byte getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(byte paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getDevicetimeStamp() {
		return devicetimeStamp;
	}

	public void setDevicetimeStamp(long devicetimeStamp) {
		this.devicetimeStamp = devicetimeStamp;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPriceAfterDiscount) {
		this.totalPrice = totalPriceAfterDiscount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ArrayList<Item> getCartItems() {
		return itemsListForCart;
	}

	public void setCartItems(ArrayList<Item> itemsListForCart) {
		this.itemsListForCart = itemsListForCart;
	}

	public long getRowID() {
		return rowID;
	}

	public void setRowID(long rowID) {
		this.rowID = rowID;
	}

}
