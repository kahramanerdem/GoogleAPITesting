package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils{
	RequestSpecification req;
	ResponseSpecification res;
	Response response;
	static String place_Id;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {	
		req = given().spec(requestSpecification()).body(data.AddPlacePayload(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		if(method.equalsIgnoreCase("POST")) {
		response = req.when().post(resourceAPI.getResources());
		}
		else if(method.equalsIgnoreCase("GET")) {
		response = req.when().get(resourceAPI.getResources());
		}
	}
	@Then("the API call got is success with status code {int}")
	public void the_api_call_got_is_success_with_status_code(int statusCode) {
	    assertEquals(response.getStatusCode(),statusCode);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {		
	    assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	@Then("verify place_Id created maps {string} using {string}")
	public void verify_place_id_created_maps_using(String name, String resource) throws IOException {
		place_Id = getJsonPath(response,"place_id");
		req = given().spec(requestSpecification())
				     .queryParam("place_id", place_Id);
		user_calls_with_post_http_request(resource,"GET");
		assertEquals(getJsonPath(response,"name").toString(),name);
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    req = given().spec(requestSpecification())
	    	   .body(data.DeletePlacePayload(place_Id));
	}
	@Then("verify deleted place with place_Id using {string} and status code {int}")
	public void verify_deleted_place_with_place_id_using_and_status_code(String resource, int statusCode) throws IOException {
		req = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_post_http_request(resource,"GET");
		the_api_call_got_is_success_with_status_code(statusCode);
	}
}
