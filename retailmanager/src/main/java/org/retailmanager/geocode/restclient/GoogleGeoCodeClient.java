package org.retailmanager.geocode.restclient;

import org.retailmanager.rest.bs.RetailManagerService;
import org.retailmanager.rest.modal.ShopAddress;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * @author Viru
 *
 * This can use WebTarget approach to access rest service, but as we have client library exposed, 
 * it will help to get response POJO, if we use WebTarget, we need to define and map POJO based on response json string.
 */
@Component 
public class GoogleGeoCodeClient implements IGoogleGeoCodeClient {
	
	private Logger logger = LoggerFactory.getLogger(GoogleGeoCodeClient.class);

	/* (non-Javadoc)
	 * @see org.retailmanager.geocode.restclient.IGoogleGeoCodeClient#getShopGeoInfo(org.retailmanager.rest.modal.ShopAddress)
	 * Unable to get google API Key because of some issues. 
	 * Now dividing postal code in 2 parts and building the latitude and longitude 
	 * Sorry it's alphanumeric so converting require special care(algorithm).
	 * for time crunch assuming that user always pass numeric postal code and size > 4 digits.
	 */
	@Override
	public ShopGeoInfo getShopGeoInfo(ShopAddress shopAddress) {
		// Replace the API key below with a valid API key.
//		GeoApiContext context = new GeoApiContext().setApiKey("YOUR_API_KEY");
		GeocodingResult[] results=null;
		try {
//			results = GeocodingApi.geocode(context,
//			    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//			System.out.println(results[0].formattedAddress);		
			String postCode = shopAddress.getPostCode();
			String lat = postCode.substring(0,postCode.length()/2);
			String lng = postCode.substring((postCode.length()/2));
			
			ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
			shopGeoInfo.setShopLatitude(Double.parseDouble(lat));
			shopGeoInfo.setShopLongitude(Double.parseDouble(lng));
//			shopGeoInfo.setShopLatitude(results[0].geometry.location.lat);
//			shopGeoInfo.setShopLongitude(results[0].geometry.location.lng);
			logger.info(shopAddress.toString());
			logger.info(shopGeoInfo.toString());
			return shopGeoInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPostalCode(ShopGeoInfo shopGeoInfo) {
		// Replace the API key below with a valid API key.
//		GeoApiContext context = new GeoApiContext().setApiKey("YOUR_API_KEY");
		GeocodingResult[] geocodingResults=null;
		try {
//			LatLng location = new LatLng(shopGeoInfo.getShopLatitude(), shopGeoInfo.getShopLongitude());
//			geocodingResults = GeocodingApi.reverseGeocode(context, location).await();
//			System.out.println(geocodingResults[0].formattedAddress);
//			for(AddressComponent addressComponet :geocodingResults[0].addressComponents){
//				for(AddressComponentType type:addressComponet.types){
//					if(type ==AddressComponentType.POSTAL_CODE){
//						return addressComponet.longName;
//					}
//				}
//			}
//			logger.info(shopAddress.toString());
			logger.info(shopGeoInfo.toString());
			String postCode  = Integer.toString((int)shopGeoInfo.getShopLatitude()) + Integer.toString((int)shopGeoInfo.getShopLongitude());
			logger.info("postCode : "+postCode);
			return postCode; 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}

}
