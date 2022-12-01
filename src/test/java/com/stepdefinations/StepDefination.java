package com.stepdefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import com.Resources.GoogleAPIResources;
import com.Resources.SpecBuildersUtils;
import com.Resources.TestDataBuild;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends SpecBuildersUtils {
	RequestSpecification requestbody= null;
	JsonPath addPlaceResponnseJson;
	Response response;
	TestDataBuild payload=new TestDataBuild();
	String placeId = null;
	
	
	Properties prop= null;
	
	
	@Given("User has the payload for AddPlace {string} {string} {string}")
	public void user_has_the_payload_for_add_place(String name, String language, String address) throws IOException{
	    // Write code here that turns the phrase above into concrete actions 
		
		
		prop = loadPropertyFile();
		
		RequestSpecification request = RequestSpecBuild();
		System.out.println(name+language+address);
		
		
		//Build the request 
		 requestbody = given().spec(request).log().all().
		body(payload.AddPlacePayload(name,language,address));
		 
	   
	}
	@When("User call the {string} {string} http request")
	public void user_call_the_http_request(String resource, String reuqestType){
	    // Write code here that turns the phrase above into concrete actions 
		GoogleAPIResources resAPI =GoogleAPIResources.valueOf(resource);
		
		
		ResponseSpecification respSpec = ResponseSpecBuild();
		if(reuqestType.equalsIgnoreCase("POST")) {
			response =requestbody.when().post(prop.getProperty(resource)).then().spec(respSpec).
					log().all().
				extract().response();
		 
		}
		else if(reuqestType.equalsIgnoreCase("GET")) {
			//fetching the resource name using enum
			response =requestbody.when().get(resAPI.getResource()).then().spec(respSpec).
					log().all().
				extract().response();
		}
		
	 
	}
	@Then("API call is success with the status code {int}")
	public void api_call_is_success_with_the_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		int expectedStatusCode = response.getStatusCode();
	    assertEquals(expectedStatusCode,200);
	}
	@And("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		
		//JsonPath addPlaceResponnseJson = new JsonPath(addPlaceResponse.asString()); 
		//String key =addPlaceResponnseJson.get(string).toString();
		assertEquals(getValuesFromJson(response, key), value);
		
		
		 
	}
	

	@Then("Verify the {string} using {string}")
	public void verify_the_using(String name, String apiName) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		//JsonPath addPlaceResponnseJson = new JsonPath(addPlaceResponse.asString()); 
		placeId =getValuesFromJson(response,"place_id");
		requestbody = given().spec(RequestSpecBuild()).queryParam("place_id", placeId);
		user_call_the_http_request(apiName, "GET");
		String nameRes =getValuesFromJson(response, "name");
		System.out.println("name from response:  "+nameRes);
		assertEquals(name,nameRes);
		
		
	}


}
