package org.retailmanager.api.response;

import org.retailmanager.rest.modal.ShopAddress;
//@Entity
public class ShopsResponseVO {
	private String shopName;
	private ShopAddress shopAddress;
	private double shopLongitude;
	private double shopLatitude;
	/**
	 * @return the shopsName
	 */
	public String getShopsName() {
		return shopName;
	}
	/**
	 * @param shopsName the shopsName to set
	 */
	public void setShopsName(String shopsName) {
		this.shopName = shopsName;
	}
	/**
	 * @return the shopAddress
	 */
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	/**
	 * @param shopAddress the shopAddress to set
	 */
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	/**
	 * @return the shopLongitude
	 */
	public double getShopLongitude() {
		return shopLongitude;
	}
	/**
	 * @param shopLongitude the shopLongitude to set
	 */
	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	/**
	 * @return the shopLatitude
	 */
	public double getShopLatitude() {
		return shopLatitude;
	}
	/**
	 * @param d the shopLatitude to set
	 */
	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
}
