package org.retailmanager.api;

import java.util.Collection;

import org.retailmanager.api.request.ShopRequestVO;
import org.retailmanager.api.response.ShopsResponseVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/shops")
public interface IRetailManagerController {
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShopsResponseVO> addShop(@RequestBody ShopRequestVO shopRequestVO);
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ShopsResponseVO>> getShops(@RequestParam("customerLongitude") Float customerLongitude,
			@RequestParam("customerLatitude") Float customerLatitude );
	
	
	
	
}
