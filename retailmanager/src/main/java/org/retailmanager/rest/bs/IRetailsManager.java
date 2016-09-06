package org.retailmanager.rest.bs;

import org.retailmanager.api.request.ShopRequest;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.retailmanager.rest.modal.ShopInfo;

import java.util.Collection;
import java.util.List;


public interface IRetailsManager {
  public ShopInfo addShop(ShopRequest shopRequest);

  public List<ShopInfo> getShops(ShopGeoInfo shopGeoInfo);

  public Collection<ShopInfo> getAll();
}
