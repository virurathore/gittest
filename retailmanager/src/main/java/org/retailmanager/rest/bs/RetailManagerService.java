package org.retailmanager.rest.bs;


import java.util.List;

import org.retailmanager.api.request.ShopRequestVO;
import org.retailmanager.geocode.restclient.IGoogleGeoCodeClient;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;
import org.retailmanager.rest.repository.IShopsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
//@Transactional(propagation = Propagation.REQUIRED,
//readOnly = false)
public class RetailManagerService implements IRetailsManager{
	private Logger logger = LoggerFactory.getLogger(RetailManagerService.class);

	@Autowired
	private IShopsRepository shopsRepository;

	@Autowired
	IGoogleGeoCodeClient googleGeoCodeClient;
	
	@Override
	public ShopInfo addShop(ShopRequestVO shopRequestVO) {
		logger.info("addShop called");
		ShopGeoInfo shopGeoInfo = googleGeoCodeClient.getShopGeoInfo(shopRequestVO.getShopAddress());
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setShopName(shopRequestVO.getShopName());
		shopInfo.setShopAddress(shopRequestVO.getShopAddress());
		shopInfo.setShopGeoInfo(shopGeoInfo);
		shopInfo = shopsRepository.save(shopInfo);
		return shopInfo;
	}

	@Override
	public List<ShopInfo> getShops(ShopGeoInfo shopGeoInfo) {
		logger.info("getShops called");
		String postalCode = googleGeoCodeClient.getPostalCode(shopGeoInfo);
		List<ShopInfo> shopsInfoList = shopsRepository.findAll(postalCode);
		return shopsInfoList;
	}

}
