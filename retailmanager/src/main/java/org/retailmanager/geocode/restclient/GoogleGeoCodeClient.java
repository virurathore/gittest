package org.retailmanager.geocode.restclient;

import org.retailmanager.rest.modal.ShopAddress;
import org.retailmanager.rest.modal.ShopGeoInfo;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Component 
public class GoogleGeoCodeClient implements IGoogleGeoCodeClient {

	@Override
	public ShopGeoInfo getShopGeoInfo(ShopAddress shopAddress) {
		// Replace the API key below with a valid API key.
//		GeoApiContext context = new GeoApiContext().setApiKey("YOUR_API_KEY");
		GeocodingResult[] results=null;
		try {
//			results = GeocodingApi.geocode(context,
//			    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//			System.out.println(results[0].formattedAddress);		
			ShopGeoInfo shopGeoInfo = new ShopGeoInfo();
			shopGeoInfo.setShopLatitude(40.714224);
			shopGeoInfo.setShopLongitude(-73.961452);
//			shopGeoInfo.setShopLatitude(results[0].geometry.location.lat);
//			shopGeoInfo.setShopLongitude(results[0].geometry.location.lng);
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
			return "94043"; 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}

}
