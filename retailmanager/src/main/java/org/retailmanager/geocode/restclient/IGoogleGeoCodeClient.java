package org.retailmanager.geocode.restclient;

import org.retailmanager.api.request.ShopRequest;
import org.retailmanager.rest.modal.ShopGeoInfo;

/**
 * @author Viru.
 *
 *         This used for google geo code api rest client interface which used by retail manager
 *         service.
 */
public interface IGoogleGeoCodeClient {
  /**
   * @param shopAddress.
   * @return ShopGeoInfo This return geo code based on postal code.
   */
  public ShopGeoInfo getShopGeoInfo(ShopRequest shopRequest);

  /**
   * @param shopGeoInfo.
   * @return String This return postal code of given geo code.
   */
  public String getPostalCode(ShopGeoInfo shopGeoInfo);
}
