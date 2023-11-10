Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is eing succesfully added using AddPlaceAPI
Given Add place Payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "POST" http request
Then the API call got is success with status code 200
And  "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps "<name>" using "getPlaceAPI"

Examples: 
		|name 		 |language| address						|
		|PeraPalace|Turkish | World Cross Center|
#		|SeraPalace|English | Sea Cross Center  |

@DeletePlace
Scenario: Verify if delete Place functionality is working

Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got is success with status code 200
And  "status" in response body is "OK"
And verify deleted place with place_Id using "getPlaceAPI" and status code 404