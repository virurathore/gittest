package org.retailmanager.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.retailmanager.api.request.ShopRequestVO;
import org.retailmanager.api.response.ShopsResponseVO;
import org.retailmanager.rest.bs.IRetailsManager;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetailsManagerController implements IRetailManagerController {
	private Logger logger = LoggerFactory.getLogger(RetailsManagerController.class);
	@Autowired
	private IRetailsManager retailManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.retailmanager.api.IRetailManagerController#addShop(org.retailmanager.
	 * api.request.ShopBean)
	 */
	@Override
	public ResponseEntity<ShopsResponseVO> addShop(@RequestBody ShopRequestVO shopRequestVO) {
		logger.info("addShop called : " + shopRequestVO);
		shopRequestVO.print();
		ShopInfo shopInfo = retailManager.addShop(shopRequestVO);
		ShopsResponseVO shops = mapShops(shopInfo);
		return new ResponseEntity<ShopsResponseVO>(shops, HttpStatus.CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.retailmanager.api.IRetailManagerController#getShops(java.lang.Float,
	 * java.lang.Float)
	 */
	@Override
	public ResponseEntity<Collection<ShopsResponseVO>> getShops(Float customerLongitude, Float customerLatitude) {
		logger.info("getShops called");
		ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
		shopGeoInfo.setShopLatitude(customerLatitude);
		shopGeoInfo.setShopLongitude(customerLongitude);
		Collection<ShopInfo> shops = retailManager.getShops(shopGeoInfo);
		List<ShopsResponseVO> shopsList = null;
		if (shops != null) {
			for (ShopInfo shopInfo : shops) {
				if (shopsList == null)
					shopsList = new ArrayList<ShopsResponseVO>();
				shopsList.add(mapShops(shopInfo));
			}
		}
		if (shopsList != null) {
			return new ResponseEntity<Collection<ShopsResponseVO>>(shopsList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Collection<ShopsResponseVO>>(shopsList, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * @param shopInfo
	 * @return Shops This function do mapping of internal DTO to external
	 *         response object For production app this can be handle by some
	 *         kind of mapper based on xml configuration.
	 */
	private ShopsResponseVO mapShops(ShopInfo shopInfo) {
		ShopsResponseVO shop = new ShopsResponseVO();
		shop.setShopAddress(shopInfo.getShopAddress());
		shop.setShopsName(shopInfo.getShopName());
		shop.setShopLatitude(shopInfo.getShopGeoInfo().getShopLatitude());
		shop.setShopLongitude(shopInfo.getShopGeoInfo().getShopLongitude());
		return shop;
	}

}
