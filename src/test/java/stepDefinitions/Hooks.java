package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write a code that will give you place id
		//execute this code only when place id is null
		
		StepDefinitions steps = new StepDefinitions();
		if(StepDefinitions.place_Id==null) {
		steps.add_place_payload_with("Hotel", "Spanish", "Spain");
		steps.user_calls_with_post_http_request("addPlaceAPI", "POST");
		steps.verify_place_id_created_maps_using("Hotel", "getPlaceAPI");
		}
	}
}
