package com.Resources;

import java.util.ArrayList;
import java.util.List;

import com.pojoClasses.AddPlaceRequest;
import com.pojoClasses.Location;

public class TestDataBuild {

	public AddPlaceRequest AddPlacePayload(String name,String language,String address) {
		AddPlaceRequest addPlace = new AddPlaceRequest();
		List<String> typeString = new ArrayList<String>();
		Location location = new Location();
		
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		typeString.add("shoe park");
		typeString.add("shop");
		
		addPlace.setLocation(location);
		addPlace.setTypes(typeString);
		addPlace.setAccuracy(90);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91)080-123456");
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(language);
		addPlace.setAddress(address);
		
		return addPlace;
	}
	
	public String deleteApiPayload(String placeId) {
		String payload ="{\n"
				+ "    \"place_id\":\""+placeId+"\"\n"
				+ "}";
		return payload;
		
	}
}
