package api.stepdefinition;


import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.http.HttpStatus;

import Context.SharedContext;

import Utilities.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utilities.LoggerLoad;
import api.request.UserDietician_request;
import api.response.UserDietician_response;


public class UserDieticianSteps extends BaseStep{
	Response response;
	UserDietician_request userDieticianreq;
	static String fileId;
	static UserDietician_response userDieticianFileAdded;
	
	UserDietician_response userDieticianres;
	@Given("User is the registered Dietician with the valid {string} and {string}")
	public void user_is_the_registered_dietician_with_the_valid_and(String string, String string2) {
	    
	}


	@When("User sends HTTP POST Request for User login with valid endpoint")
	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
	  
	}

	@Then("User receives Bearer Token")
	public void user_receives_bearer_token() {
	    
	}

	@Given("User creates GET Request for the Patient endpoint with {string} scenario patientid")
	public void user_creates_get_request_for_the_patient_endpoint_with_scenario(String patientId) {
		try
		{
			RestAssured.baseURI = ConfigReader.BaseURL();
			RequestSpecification request = RestAssured.given();
			
			LoggerLoad.logInfo("Get patient morbidity details request created for " + patientId);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@When("User sends {string} request with patientid")
	public void user_sends_request_with_patientid(String scenario) {
		try
		{
			switch(scenario)
			{
				case "ValidPatientIdMorbidityFileAttached" :
					response = userDieticianEndpoints.GetPatientMorbidityDetails(SharedContext.patientIdValid());
					break;
					
				case "ValidPatientIdMorbidityFileMissing" :
					response = userDieticianEndpoints.GetPatientMorbidityDetails(SharedContext.getPatientIdMissingFile());
					break;
				case "InvalidPatientIdMorbidityFileAttached" :
					response = userDieticianEndpoints.GetPatientMorbidityDetails(000);
					break;
			}
	
			LoggerLoad.logInfo("Get patient morbidity details request sent for- " + scenario);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives the status code and message based on {string}")
	public void user_receives_the_status_code_and_message_based_on(String scenario) {
		try
		{	
			UserDietician_response userDieticianResponse = null;
			
			switch(scenario)
			{
				case "ValidPatientIdMorbidityFileAttached" :
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_OK)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("getpatientmorbiditydetailsjsonschema.json")));
					
					userDieticianResponse = response.getBody().as(UserDietician_response.class);
					break;
				
				case "ValidPatientIdMorbidityFileMissing" :
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_OK)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema("[]"));
					userDieticianResponse = response.getBody().as(UserDietician_response.class);
					break;
					
					
				case "InvalidPatientIdMorbidityFileAttached" :
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_NOT_FOUND)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("400badrequestjsonschema.json")));
					
					
					JsonPath jsonPathEvaluator = response.jsonPath();
					List<UserDietician_response> userDieticianList = jsonPathEvaluator.getList("", UserDietician_response.class);
					userDieticianResponse = userDieticianList.get(0);
					break;
			}
	
			// Validate json response values
			
			assertEquals(userDieticianFileAdded.fileId, userDieticianResponse.fileId);
			assertEquals(userDieticianFileAdded.fileName, userDieticianResponse.fileName);
			assertEquals(userDieticianFileAdded.uploadDate, userDieticianResponse.uploadDate);
			assertEquals(userDieticianFileAdded.morbidConditions, userDieticianResponse.morbidConditions);
			assertEquals(userDieticianFileAdded.morbidConditionStr, userDieticianResponse.morbidConditionStr);
			
			LoggerLoad.logInfo("Get patient morbidity details response validated for- " + scenario);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	@Given("User creates GET Request for the Patient endpoint with {string} scenario fileid")
	public void user_creates_get_request_for_the_patient_endpoint_with_scenario_fileid(String fileId) {
		try
		{
			RestAssured.baseURI = ConfigReader.BaseURL();
			RequestSpecification request = RestAssured.given();
			
			LoggerLoad.logInfo("Get patient morbidity details request created for " + fileId);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@When("User sends {string} request with fileid")
	public void user_sends_request_with_fileid(String scenario) {
		try
		{
			switch(scenario)
			{
				case "ValidPatientIdMorbidityFileAttached" :
					response = userDieticianEndpoints.GetPatientFileReport(fileId);
					break;
					
				case "ValidPatientIdMorbidityFileMissing" :
					response = userDieticianEndpoints.GetPatientFileReport(fileId);
					break;
				case "InvalidPatientIdMorbidityFileAttached" :
					response = userDieticianEndpoints.GetPatientFileReport(fileId);
					break;
			}
	
			LoggerLoad.logInfo("Get patient morbidity details request sent for- " + scenario);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}
	@Then("User receives the status code and message based on {string} with file report")
	public void user_receives_the_status_code_and_message_based_on_with_file_report(String scenario) {
		try
		{	
			UserDietician_response userDieticianResponse = null;
			File responsebodyfile=new File("src/test/resources/Hypo Thyroid-Report.pdf.pdf");
			
			switch(scenario)
			{
				case "ValidFileId" :
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_OK)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(responsebodyfile));
					
					userDieticianResponse = response.getBody().as(UserDietician_response.class);
					break;
					
					
				case "InvalidFileId" :
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_NOT_FOUND)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("400badrequestjsonschema.json")));
					
					JsonPath jsonPathEvaluator = response.jsonPath();
					List<UserDietician_response> userDieticianList = jsonPathEvaluator.getList("", UserDietician_response.class);
					userDieticianResponse = userDieticianList.get(0);
					break;
			}
	
			// Validate json response values
			
			assertEquals(userDieticianFileAdded.MorbidityFile, userDieticianResponse.MorbidityFile);
			
			LoggerLoad.logInfo("Get patient file report validated for- " + scenario);
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
		
	    
	}
	
	
	
	 

}
