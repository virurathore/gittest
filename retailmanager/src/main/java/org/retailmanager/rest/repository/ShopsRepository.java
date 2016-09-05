package org.retailmanager.rest.repository;

import org.retailmanager.rest.modal.ShopAddress;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Viru.
 * 
 *         we have used in-memory hash-map to store data, this can be converted to ORM DB or some
 *         in-memory DB if used DB remove looping to find based on postal code.
 *
 */

@Repository
public class ShopsRepository implements IShopsRepository {

  private static BigInteger nextId;
  private static Map<BigInteger, ShopInfo> shopInfoMap;
  private static final Logger logger = LoggerFactory.getLogger(ShopsRepository.class);

  static {
    ShopInfo shopInfo = new ShopInfo();
    shopInfo.setShopName("ags cinema");
    ShopAddress shopAddress = new ShopAddress();
    shopAddress.setNumber(1600);
    shopAddress.setPostCode("603103");
    shopInfo.setShopAddress(shopAddress);
    ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
    shopGeoInfo.setShopLatitude(80.1986649);
    shopGeoInfo.setShopLongitude(12.7949478);
    shopInfo.setShopGeoInfo(shopGeoInfo);
    saveShopInfo(shopInfo);
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
  public ShopInfo save(ShopInfo shopInfo) {
    logger.info("save called");
    return ShopsRepository.saveShopInfo(shopInfo);
  }

  @Override
  public List<ShopInfo> findAll(String postalCode) {
    logger.info("find all called : " + postalCode);
    if (shopInfoMap == null) {
      logger.info("shop list is empty");
      return null;
    }
    List<ShopInfo> shopList = null;
    for (ShopInfo shopInfo : shopInfoMap.values()) {
      ShopAddress shopAddress = shopInfo.getShopAddress();
      if (shopAddress != null && postalCode.equalsIgnoreCase(shopAddress.getPostCode())) {
        if (shopList == null) {
          shopList = new ArrayList<ShopInfo>();
        }
        shopList.add(shopInfo);
      }
    }
    return shopList;
  }

}
