package org.retailmanager.api.request;

import org.retailmanager.rest.modal.ShopAddress;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
public class ShopRequestVO {
//	@JsonProperty("shopName")
	private String shopName;

//	@JsonProperty("shopAddress")
	private ShopAddress shopAddress;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	public void print() {
		System.out.println("shopName : " + shopName);
	}
}
