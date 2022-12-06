package com.stepdefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination step = new StepDefination();
		if(StepDefination.placeId == null)
		{
		step.user_has_the_payload_for_add_place("Bacchan", "Kannada", "Bengaluru");
		step.user_call_the_http_request("AddPlaceApi", "POST");
		step.verify_the_using("Bacchan","GetPlaceApi");
		}
	}
}
