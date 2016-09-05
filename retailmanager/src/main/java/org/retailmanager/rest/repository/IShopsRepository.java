package org.retailmanager.rest.repository;

import org.retailmanager.rest.modal.ShopInfo;

import java.util.List;



/**
 * @author Viru.
 * 
 *         This can handle database to store and retrieve data.
 */

public interface IShopsRepository {

  /**
   * @param shopInfo.
   * @return shopinfo after saving data.
   */
  public ShopInfo save(ShopInfo shopInfo);

  /**
   * @param postalCode.
   * @return list of shopinfo.
   */
  public List<ShopInfo> findAll(String postalCode);

}
