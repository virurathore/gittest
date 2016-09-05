package org.retailmanager.api;

import org.retailmanager.api.request.ShopRequest;
import org.retailmanager.api.response.ShopsResponse;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class RetailsManagerController implements IRetailManagerController {
  private static final Logger logger = LoggerFactory.getLogger(RetailsManagerController.class);
  @Autowired
  private IRetailsManager retailManager;

  /*
   * (non-Javadoc)
   * 
   * @see org.retailmanager.api.IRetailManagerController#addShop(org.retailmanager.
   * api.request.ShopBean)
   */
  @Override
  public ResponseEntity<ShopsResponse> addShop(@RequestBody ShopRequest shopRequest) {
    logger.info("addShop called : " + shopRequest);
    shopRequest.print();
    ShopInfo shopInfo = retailManager.addShop(shopRequest);
    ShopsResponse shops = mapShops(shopInfo);
    return new ResponseEntity<ShopsResponse>(shops, HttpStatus.CREATED);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.retailmanager.api.IRetailManagerController#getShops(java.lang.Float, java.lang.Float)
   */
  @Override
  public ResponseEntity<Collection<ShopsResponse>> getShops(Float customerLongitude,
      Float customerLatitude) {
    logger.info("getShops called");
    ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
    shopGeoInfo.setShopLatitude(customerLatitude);
    shopGeoInfo.setShopLongitude(customerLongitude);
    Collection<ShopInfo> shops = retailManager.getShops(shopGeoInfo);
    List<ShopsResponse> shopsList = null;
    if (shops != null) {
      for (ShopInfo shopInfo : shops) {
        if (shopsList == null) {
          shopsList = new ArrayList<ShopsResponse>();
        }
        shopsList.add(mapShops(shopInfo));
      }
    }
    if (shopsList != null) {
      return new ResponseEntity<Collection<ShopsResponse>>(shopsList, HttpStatus.OK);
    } else {
      return new ResponseEntity<Collection<ShopsResponse>>(shopsList, HttpStatus.NO_CONTENT);
    }
  }

  /**
   * @param shopInfo This is shopinfo which mapped to response object.
   * @return Shops This function do mapping of internal DTO to external response object For
   *         production app this can be handle by some kind of mapper based on xml configuration.
   */
  private ShopsResponse mapShops(ShopInfo shopInfo) {
    ShopsResponse shop = new ShopsResponse();
    shop.setShopAddress(shopInfo.getShopAddress());
    shop.setShopsName(shopInfo.getShopName());
    shop.setShopLatitude(shopInfo.getShopGeoInfo().getShopLatitude());
    shop.setShopLongitude(shopInfo.getShopGeoInfo().getShopLongitude());
    return shop;
  }

}
