package com.Resources;

public enum GoogleAPIResources {
	
		AddPlaceApi("maps/api/place/add"),
		GetPlaceApi("maps/api/place/get/json"),
		DeletePLaceApi("maps/api/place/delete");
		private String resource;
		GoogleAPIResources(String string) {
			// TODO Auto-generated constructor stub
					this.resource=string;
		}
	
	public String getResource() {
		return resource;
	}

}
