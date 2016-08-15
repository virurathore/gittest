package org.retailmanager.geocode.restclient;

import org.retailmanager.rest.modal.ShopAddress;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.springframework.stereotype.Component;

// This used for google geo code api rest client interface which used by retail manager service.

public interface IGoogleGeoCodeClient {
	/**
	 * @param shopAddress
	 * @return ShopGeoInfo
	 * This return geo code based on postal code 
	 */
	public ShopGeoInfo getShopGeoInfo(ShopAddress shopAddress);
	/**
	 * @param shopGeoInfo
	 * @return String 
	 * This return postal code of given geo code
	 */
	public String getPostalCode(ShopGeoInfo shopGeoInfo);
}
