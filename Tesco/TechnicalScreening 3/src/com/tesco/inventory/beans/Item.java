/**
 * Contains item infor like name, price, id, quantity etc
 * 
 */
package com.tesco.inventory.beans;

import com.tesco.inventory.utils.TSConstants;

public class Item implements Inventory{	
	private static final long serialVersionUID = 5524525616062369402L;
	private String id = null;
	private String name = null;
	private String createdBy = null;
	private String price = "0.0";	
	private long rowID = TSConstants.EOF;
	private int quantity = 1;
	private byte deleteStatus = TSConstants.FALSE;
	

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public byte getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(byte deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public long getRowID() {
		return rowID;
	}

	public void setRowID(long rowID) {
		this.rowID = rowID;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
