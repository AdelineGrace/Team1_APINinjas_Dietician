package api.stepdefinition;

import Utilities.ConfigReader;
import Utilities.LoggerLoad;
import api.endpoints.Morbidity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbiditySteps extends BaseSteps{
	

	static final String baseUrl = ConfigReader.getProperty("base.url");
	static final String morbidityTestNameUrl = ConfigReader.getProperty("all_morbidity.url");
	RequestSpecification request;
	Response response;
	static String morbidityTestName;
	static String DataKey;
	static int morbidityId;
	
	
	
	@Given("User is the registered Dietician with the valid {string} and {string}")
	public void user_is_the_registered_dietician_with_the_valid_and(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTP POST Request for User login with valid endpoint")
	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
	   
	}

	@Then("User receives Bearer Token")
	public void user_receives_bearer_token() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates GET Request for the Dietician API endpoint \\(no parameters)")
	public void user_creates_get_request_for_the_dietician_api_endpoint_no_parameters() {
		 try
			{
			  
			  RestAssured.baseURI = baseUrl;
			  request = RestAssured.given().log().all();
				LoggerLoad.logInfo("GET all Morbidity");
			}
		  catch (Exception ex) 
			{
				LoggerLoad.logInfo(ex.getMessage());
				ex.printStackTrace();
			}
	}

	@When("User sends HTTPS Request with {string}")
	public void user_sends_https_request_with() {
		try
		{
		response = Morbidity.GetAllMorbidity(DataKey);
		
		LoggerLoad.logInfo("GET all assignments request sent");
		} 
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives Status Code  with response body for endpoint {string}")
	public void user_receives_status_code_with_response_body_for_endpoint(String string) {
		response.then().log().all();
		if(DataKey.equals("Valid") ) {
			response.then().statusCode(200);
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("200 status code json schema for GetAllMorbidity.json")));
		
		} else if(DataKey.equals("Invalid")) {
			response.then().statusCode(404);
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("404 status code json schema for GetAllMorbidity.json")));

		}
	}
	
	@Given("User creates GET Request for the Dietician API endpoint with morbidity test name.")
	public void user_creates_get_request_for_the_dietician_api_endpoint_with_morbidity_test_name() {
		try
		{
		  
		  RestAssured.baseURI = baseUrl;
		  request = RestAssured.given().log().all();
			LoggerLoad.logInfo("GET all Morbidity");
		}
	  catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@When("User sends the HTTPS Request after setting of morbidity Test Name {string}")
	public void user_sends_the_https_request_after_setting_of_morbidity_test_name(String string) {
		//response = Morbidity.GetMorbidityByTestName(morbidityTestName,DataKey);
		try
		{
			switch(DataKey)
			{
				case "ValidMorbidityTestName" :
					response = Morbidity.GetMorbidityByTestName(morbidityTestName,DataKey);
					break;
					
				case "InValidMorbidityTestName" :
					response = Morbidity.GetMorbidityByTestName(morbidityTestName,DataKey);
					break;
				
			}
	
			LoggerLoad.logInfo("Get patient morbidity details request sent for- " + DataKey);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives Status Code  with response body with morbidity test name endpoint  {string}")
	public void user_receives_status_code_with_response_body_with_morbidity_test_name_endpoint(String string) {
		response.then().log().all();
		if(DataKey.equals("Valid") ) {
			response.then().statusCode(200);
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("200 status code json schema for GetAllMorbidity.json")));
		
		} else if(DataKey.equals("Invalid")) {
			response.then().statusCode(404);
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("404 status code json schema for GetAllMorbidity.json")));

		}
	}
	
	
	
	@Given("User creates GET Request for the Dietician API endpoint {string}")
	public void user_creates_get_request_for_the_dietician_api_endpoint(String string) {
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();
	}

	@When("User sends the HTTPS Request after setting of User logout endpoint.")
	public void user_sends_the_https_request_after_setting_of_user_logout_endpoint() {
		response = Morbidity.User_Logout(DataKey);
	}

	@Then("User receives Status Code  with response body for logout endpoint {string}")
	public void user_receives_status_code_with_response_body_for_logout_endpoint(String string) {
		response.then().log().all();
		if(DataKey.equals("Valid") ) {
			response.then().statusCode(200);
			
		} else if(DataKey.equals("Invalid")) {
			response.then().statusCode(404);
			
		}
	}





}
