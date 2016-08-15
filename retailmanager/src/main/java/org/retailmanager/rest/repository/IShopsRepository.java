package org.retailmanager.rest.repository;

import java.util.List;

import org.retailmanager.rest.modal.ShopInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Viru
 * This can handle database to store and retreive data
 */

public interface IShopsRepository {

	/**
	 * @param shopInfo
	 * @return
	 */
	public ShopInfo save(ShopInfo shopInfo);

	/**
	 * @param postalCode 
	 * @return
	 */
	public List<ShopInfo> findAll(String postalCode);

}
