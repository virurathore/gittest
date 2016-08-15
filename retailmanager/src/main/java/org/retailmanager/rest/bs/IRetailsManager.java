package org.retailmanager.rest.bs;

import java.util.List;

import org.retailmanager.api.request.ShopRequestVO;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;

public interface IRetailsManager {
	public ShopInfo addShop(ShopRequestVO shopRequestVO);
	public List<ShopInfo> getShops(ShopGeoInfo shopGeoInfo);
}
