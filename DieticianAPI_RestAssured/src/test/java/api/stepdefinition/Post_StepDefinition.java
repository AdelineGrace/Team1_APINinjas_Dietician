package api.stepdefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpStatus;

import Context.SharedContext;
import Utilities.DynamicValues;
import Utilities.ExcelReader;
import api.endpoints.PostPatient;
import api.request.PostPatient_request;
import api.response.PostPatient_response;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.stepdefinition.*;
import Utilities.LoggerLoad;

public class Post_StepDefinition extends BaseStep {
	PostPatient_request patientRequest;
	Map<String, String> excelData;
	String sheetName;
	String header;
	RequestSpecification request;
	Response response;
  static int patientIdValid = 0;
  static int patientIdMissingFile = 0;
	PostPatient_response patient;
	
	@Given("User is the registered Dietician with the valid {string} and {string}")
	public void user_is_the_registered_dietician_with_the_valid_and(String password, String UserLoginEmail) {
		 
		

	    }
	

	@When("User sends HTTP POST Request for User login with valid endpoint")
	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
	    
	}

	@Then("User receives Bearer Token")
	public void user_receives_bearer_token() {
	    
	}

	@Given("User creates POST Request for the Dietician  API endpoint")
	public void user_creates_post_request_for_the_dietician_api_endpoint() throws Exception {
		patientRequest = CreatePatientRequest(sheetName, header);
		LoggerLoad.logInfo("Patient POST request object created for- " + header);
	}

	@When("User sends HTTPS Request and  request Body with mandatory fields and morbidity files from {string} with {string}")
	public void user_sends_https_request_and_request_body_with_mandatory_fields_and_morbidity_files_from_with(String sheetName, String header) {
		
		response = PostPatient.CreatePatient(patientRequest,true);
		response = PostPatient.CreatePatient(patientRequest,false);

		LoggerLoad.logInfo("Patient POST request sent for- " + header);
	} 
	

	@Then("User receives response for POST {string} with {string}")
	public void user_receives_response_for_post_with(String sheetName, String header) {
		
			switch(header)
			{
				case "Post_Patient_Valid" :
				case "Post_Patient_Missing_File":
					
					response.then().assertThat()
						.statusCode(HttpStatus.SC_CREATED)
						.contentType(ContentType.JSON)
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("postpatientresponsebodyschema.json")));
					
					PostPatient_response patient = response.getBody().as(PostPatient_response.class);
					
					assertTrue(patient.patientId != 0);
					
					assertEquals(patientRequest.FirstName, patient.FirstName);
					assertEquals(patientRequest.LastName, patient.LastName);
					assertEquals(patientRequest.Email, patient.Email);
					assertEquals(patientRequest.ContactNumber, patient.ContactNumber);
					assertEquals(patientRequest.DateOfBirth, patient.DateOfBirth);
					assertEquals(patientRequest.Allergy, patient.Allergy);
					assertEquals(patientRequest.FoodCategory, patient.FoodCategory);
					break;
					
				case "Post_Patient_ExistingUniqueField" : 
				case "Post_Patient_NonAcceptedFoodCategory" : 
				case "Post_Patient_Missing_FirstName":
				case "Post_Patient_Missing_LastName":
				case "Post_Patient_Invalid_Email":
				case "Post_Patient_Missing_ContactNumber":
				case "Post_Patient_Missing_DateOfBirth":
	
					
					response.then().assertThat()
						.statusCode(HttpStatus.SC_BAD_REQUEST)
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("400badrequestjsonschema.json")));
					
					JsonPath jsonPathEvaluator = response.jsonPath();

					assertEquals(excelData.get("errorCode"), jsonPathEvaluator.get("errorCode"));
					assertEquals(excelData.get("errorMessage"), jsonPathEvaluator.get("errorMessage"));

					break;
					
									
			}			
	}
	

	private PostPatient_request CreatePatientRequest(String sheetName, String header) {
	    String firstName = excelData.get("FirstName");
	    String lastName = excelData.get("LastName");
	    String email = excelData.get("Email");
	    String contactNumber = excelData.get("ContactNumber");
	    String dateOfBirth = excelData.get("DateOfBirth");
	    String allergy = excelData.get("Allergy");
	    String foodCategory = excelData.get("FoodCategory");
	    

	    switch (header) {
	        case "Post_Patient_Valid":
	        case "Post_Patient_ExistingUniqueField":
	        case "Post_Patient_NonAcceptedFoodCategory":
	        case "Post_Patient_Missing_FirstName":
	        case "Post_Patient_Missing_LastName":
	        case "Post_Patient_Invalid_Email":
	        case "Post_Patient_Missing_ContactNumber":
	        case "Post_Patient_Missing_DateOfBirth":
	        case "Post_Patient_Missing_File":
	            break;
	        default:
	            response.then().log().all();
	            return null;
	    }

	    PostPatient_request patientRequest = new PostPatient_request(firstName, lastName, email, contactNumber,
	            dateOfBirth, allergy, foodCategory);

	    switch (header) {
	        case "Post_Patient_Valid":
	            Response responseValid = PostPatient.CreatePatient(patientRequest,true);
	            responseValid.then().log().all();

	            if (responseValid.statusCode() == 201) {
	                PostPatient_response patientValid = responseValid.getBody().as(PostPatient_response.class);
	                patientIdValid = patientValid.patientId;
	                SharedContext.setPatientIdValid(patientIdValid);
	                LoggerLoad.logInfo("PatientId for Post_Patient_Valid: " + patientIdValid);
	            }
	            break;

	        case "Post_Patient_Missing_File":
	            Response responseMissingFile = PostPatient.CreatePatient(patientRequest,false);
	            responseMissingFile.then().log().all();

	            if (responseMissingFile.statusCode() == 201) {
	                PostPatient_response patientMissingFile = responseMissingFile.getBody().as(PostPatient_response.class);
	                patientIdMissingFile = patientMissingFile.patientId;
                    SharedContext.setPatientIdMissingFile(patientIdMissingFile);

	                LoggerLoad.logInfo("PatientId for Post_Patient_Missing_File: " + patientIdMissingFile);
	            }
	            break;

	        default:
	            break;
	    }

	    return patientRequest;
	}
}