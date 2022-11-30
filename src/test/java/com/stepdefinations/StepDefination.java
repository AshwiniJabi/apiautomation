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
	RequestSpecification addPlaceRequest= null;
	Response addPlaceResponse=null;
	TestDataBuild payload=new TestDataBuild();
	
	
	Properties prop= null;
	
	@Given("User has the payload for AddPlace {string} {string} {string}")
	public void user_has_the_payload_for_add_place(String name, String language, String address) throws IOException{
	    // Write code here that turns the phrase above into concrete actions 
		
		
		prop = loadPropertyFile();
		
		RequestSpecification request = RequestSpecBuild();
		System.out.println(name+language+address);
		
		
		//Build the request 
		 addPlaceRequest = given().spec(request).log().all().
		body(payload.AddPlacePayload(name,language,address));
		 
	   
	}
	@When("User call the {string} {string} http request")
	public void user_call_the_http_request(String resource, String string2){
	    // Write code here that turns the phrase above into concrete actions 
		GoogleAPIResources resAPI =GoogleAPIResources.valueOf(resource);
		resAPI.getResource();
		
		ResponseSpecification response = ResponseSpecBuild();
		addPlaceResponse =addPlaceRequest.when().post(prop.getProperty(resource)).then().spec(response).
					log().all().
				extract().response();
		
	 
	}
	@Then("API call is success with the status code {int}")
	public void api_call_is_success_with_the_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		int expectedStatusCode = addPlaceResponse.getStatusCode();
	    assertEquals(expectedStatusCode,200);
	}
	@And("{string} in the response body is {string}")
	public void in_the_response_body_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		
		JsonPath addPlaceResponnseJson = new JsonPath(addPlaceResponse.asString()); 
		String key =addPlaceResponnseJson.get(string).toString();
		assertEquals(string2, key);
	}

}
