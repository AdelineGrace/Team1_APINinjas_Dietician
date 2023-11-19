package api.stepdefinition;

import java.io.File;
import java.util.Map;
import Utilities.DynamicValues;
import Utilities.ExcelReader;
import api.endpoints.PostPatient;
import api.request.PostPatient_request;
import api.response.PostPatient_response;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utilities.LoggerLoad;

public class Post_StepDefinition {
	PostPatient_request req;
	Map<String, String> excelData;
	String sheetName;
	String header;
	RequestSpecification request;
	Response response;
  static int patientIdValid = 0;
   static int patientIdMissingFile = 0;
	PostPatient_response patient;
	
	@Given("User is the registered Dietician with the valid {string} and {string}")
	public void user_is_the_registered_dietician_with_the_valid_and(String sheetName, String header) {
		 
		req = CreatePatientRequest(sheetName, header);
		
	    }
	

	@When("User sends HTTP POST Request for User login with valid endpoint")
	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives Bearer Token")
	public void user_receives_bearer_token() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates POST Request for the Dietician  API endpoint")
	public void user_creates_post_request_for_the_dietician_api_endpoint() throws Exception {
		
	}

	@When("User sends HTTPS Request and  request Body with mandatory fields and morbidity files from {string} with {string}")
	public void user_sends_https_request_and_request_body_with_mandatory_fields_and_morbidity_files_from_with(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives response for POST {string} with {string}")
	public void user_receives_response_for_post_with(String string, String string2) {
	  
	}
	

	private PostPatient_request CreatePatientRequest(String sheetName, String header) {
	    String firstName = excelData.get("FirstName");
	    String lastName = excelData.get("LastName");
	    String email = excelData.get("Email");
	    String contactNumber = excelData.get("ContactNumber");
	    String dateOfBirth = excelData.get("DateOfBirth");
	    String allergy = excelData.get("Allergy");
	    String foodCategory = excelData.get("FoodCategory");

	    File morbidityFile = null;

	    String morbidityFilePath = excelData.get("Morbidity");
	    if (morbidityFilePath != null) {
	        morbidityFile = new File(morbidityFilePath);
	    }

	    switch (header) {
	        case "Post_Patient_Valid":
	        case "Post_Patient_ExistingUniqueField":
	        case "Post_Patient_NonAcceptedFoodCategory":
	        case "Post_Patient_Missing_FirstName":
	        case "Post_Patient_Missing_LastName":
	        case "Post_Patient_Missing_Email":
	        case "Post_Patient_Missing_ContactNumber":
	        case "Post_Patient_Missing_DateOfBirth":
	        case "Post_Patient_Missing_File":
	            break;
	        default:
	            response.then().log().all();
	            return null;
	    }

	    PostPatient_request req = new PostPatient_request(firstName, lastName, email, contactNumber,
	            dateOfBirth, allergy, foodCategory, morbidityFile);

	    switch (header) {
	        case "Post_Patient_Valid":
	            Response responseValid = PostPatient.CreatePatient(req);
	            responseValid.then().log().all();

	            if (responseValid.statusCode() == 201) {
	                PostPatient_response patientValid = responseValid.getBody().as(PostPatient_response.class);
	                patientIdValid = patientValid.patientId;
	                LoggerLoad.logInfo("PatientId for Post_Patient_Valid: " + patientIdValid);
	            }
	            break;

	        case "Post_Patient_Missing_File":
	            Response responseMissingFile = PostPatient.CreatePatient(req);
	            responseMissingFile.then().log().all();

	            if (responseMissingFile.statusCode() == 201) {
	                PostPatient_response patientMissingFile = responseMissingFile.getBody().as(PostPatient_response.class);
	                patientIdMissingFile = patientMissingFile.patientId;
	                LoggerLoad.logInfo("PatientId for Post_Patient_Missing_File: " + patientIdMissingFile);
	            }
	            break;

	        default:
	            break;
	    }

	    return req;
	}
}