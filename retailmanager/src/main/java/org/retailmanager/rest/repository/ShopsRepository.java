package org.retailmanager.rest.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.retailmanager.rest.modal.ShopAddress;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Viru we have used in-memory hashmap to store data, this can be
 *         converted to ORM DB or some in-memory DB if used DB remove looping to
 *         find based on postal code.
 *
 */
@Repository
// @Component
public class ShopsRepository implements IShopsRepository {
	private Logger logger = LoggerFactory.getLogger(ShopsRepository.class);

	private static BigInteger nextId;
	private static Map<BigInteger, ShopInfo> shopInfoMap;

	@Override
	public ShopInfo save(ShopInfo shopInfo) {
		logger.info("save called");
		return ShopsRepository.saveShopInfo(shopInfo);
	}

	private static ShopInfo saveShopInfo(ShopInfo shopInfo) {
		if (shopInfoMap == null) {
			shopInfoMap = new HashMap<BigInteger, ShopInfo>();
			nextId = BigInteger.ONE;
		}
		shopInfo.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		shopInfoMap.put(nextId, shopInfo);
		return shopInfo;
	}

	@Override
	public List<ShopInfo> findAll(String postalCode) {
		logger.info("find all called");
		List<ShopInfo> shopList = null;
		if (shopInfoMap != null) {
			for (ShopInfo shopInfo : shopInfoMap.values()) {
				if (shopInfo.getShopAddress() != null
						&& postalCode.equalsIgnoreCase(shopInfo.getShopAddress().getPostCode())) {
					if (shopList == null) {
						shopList = new ArrayList<ShopInfo>();
					}
					shopList.add(shopInfo);
				}
			}
		}
		return shopList;
	}

	static {
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setShopName("testShop");
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber(1600);
		shopAddress.setPostCode("941432");
		shopInfo.setShopAddress(shopAddress);
		ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
		shopGeoInfo.setShopLatitude(40.714224);
		shopGeoInfo.setShopLongitude(-73.961452);
		shopInfo.setShopGeoInfo(shopGeoInfo);
		saveShopInfo(shopInfo);
	}
}
