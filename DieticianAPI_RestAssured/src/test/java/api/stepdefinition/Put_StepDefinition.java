package api.stepdefinition;

import java.io.File;
import java.util.Map;
import Utilities.DynamicValues;
import Utilities.ExcelReader;
import api.endpoints.PostPatient;
import api.endpoints.PutPatient;
import api.request.PostPatient_request;
import api.request.PutPatient_request;
import api.response.PostPatient_response;
import api.response.PutPatient_response;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utilities.LoggerLoad;


public class Put_StepDefinition {
	PutPatient_request req;
	Map<String, String> excelData;
	String sheetName;
	String header;
	RequestSpecification request;
	Response response;
  static int patientIdValid = 0;
   static int patientIdMissingFile = 0;
	PutPatient_response patient;
	
	@When("User sends HTTP PUT Request for User login with valid endpoint")
	public void user_sends_http_put_request_for_user_login_with_valid_endpoint() {
	    
	}

	@Given("User creates PUT Request for the Dietician  API endpoint")
	public void user_creates_put_request_for_the_dietician_api_endpoint() {
	    
	}

	@When("User sends HTTP Request and  request Body with  fields and files  from {string} with {string}")
	public void user_sends_http_request_and_request_body_with_fields_and_files_from_with(String string, String string2) {
	    
	}

	@Then("User receives response for PUT {string} with {string}")
	public void user_receives_response_for_put_with(String string, String string2) {
	    
	}
	
	private PutPatient_request UpdatePatientRequest(String sheetName, String header) {
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
	        case "Put_Patient_Valid":
	        case "Put_Patient_ExistingUniqueField":
	        case "Put_Patient_NonAcceptedFoodCategory":
	        case "Put_Patient_Missing_FirstName":
	        case "Put_Patient_Missing_LastName":
	        case "Put_Patient_Missing_Email":
	        case "Put_Patient_Missing_ContactNumber":
	        case "Put_Patient_Missing_DateOfBirth":
	        case "Put_Patient_Missing_File":
	            break;
	        default:
	            response.then().log().all();
	            return null;
	    }

	    PutPatient_request req = new PutPatient_request(firstName, lastName, email, contactNumber,
	            dateOfBirth, allergy, foodCategory, morbidityFile);

	    switch (header) {
	        case "Put_Patient_Valid":
	            Response responseValid = PutPatient.UpdatePatient(req);
	            responseValid.then().log().all();

	            if (responseValid.statusCode() == 201) {
	                PostPatient_response patientValid = responseValid.getBody().as(PostPatient_response.class);
	                patientIdValid = patientValid.patientId;
	                LoggerLoad.logInfo("PatientId for Put_Patient_Valid: " + patientIdValid);
	            }
	            break;

	        case "Post_Patient_Missing_File":
	            Response responseMissingFile = PutPatient.UpdatePatient(req);
	            responseMissingFile.then().log().all();

	            if (responseMissingFile.statusCode() == 201) {
	                PutPatient_response patientMissingFile = responseMissingFile.getBody().as(PutPatient_response.class);
	                patientIdMissingFile = patientMissingFile.patientId;
	                LoggerLoad.logInfo("PatientId for Put_Patient_Missing_File: " + patientIdMissingFile);
	            }
	            break;

	        default:
	            break;
	    }

	    return req;
	}

