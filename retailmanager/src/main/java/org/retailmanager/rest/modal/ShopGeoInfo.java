package org.retailmanager.rest.modal;

//@Entity
public class ShopGeoInfo {
	private double shopLongitude;
	private double shopLatitude;
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
	 * @param lat the shopLatitude to set
	 */
	public void setShopLatitude(double lat) {
		this.shopLatitude = lat;
	}
	

}
