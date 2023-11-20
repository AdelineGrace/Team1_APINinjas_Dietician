package api.stepdefinition;

import java.io.File;
import java.util.Map;

import Utilities.ExcelReader;
import api.endpoints.PutPatient;
import api.request.PutPatient_request;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import Utilities.LoggerLoad;


public class Put_StepDefinition {
	Map<String, String> excelData;
	int patientId = 0;
	String sheetName;
	String header;
	PutPatient_request patientRequest;
	Response response;
	
	@Given("A valid existing Patient Id with request values from {string} row {string}")
	public void user_creates_put_request_for_the_dietician_api_endpoint(String sheetName, String header) throws Exception
	{
		patientId = Post_StepDefinition.patientIdValid;
		this.sheetName = sheetName;
		this.header = header;
		try {
			excelData = ExcelReader.getData(header, sheetName);
			patientRequest = GetUpdatePatientRequest();
		} catch (Exception e) {
			LoggerLoad.logInfo(e.getMessage());
			throw e;
		}
		LoggerLoad.logInfo("Patient PUT request object created for- " + header);
	}

	@When("Submit PUT request")
	public void user_sends_http_request_and_request_body_with_fields_and_files_from_with() {
		response = PutPatient.UpdatePatient(patientRequest, false);
		
		LoggerLoad.logInfo("Patient PUT request sent for- " + header);
	}
	
	@Then("User receives response for PUT {string} with {string}")
	public void user_receives_response_for_put_request(String sheetName, String header ) {
		VerifyResponse(sheetName, header);
		LoggerLoad.logInfo("Patient PUT request sent for- " + header);
	}

	private void VerifyResponse(String sheetName, String header) {
		switch (header) {
        case "Put_Patient_Valid":
        	response.then().statusCode(200);
            break;
        default:
        	response.then().statusCode(200);
            break;
		}
	}
	
	private PutPatient_request GetUpdatePatientRequest() {
	    String firstName = excelData.get("FirstName");
	    String lastName = excelData.get("LastName");
	    String email = excelData.get("Email");
	    String contactNumber = excelData.get("ContactNumber");
	    String dateOfBirth = excelData.get("DateOfBirth");
	    String allergy = excelData.get("Allergy");
	    String foodCategory = excelData.get("FoodCategory");

	    File morbidityFile = null;
	    
	    PutPatient_request req = new PutPatient_request(firstName, lastName, email, contactNumber,
	            dateOfBirth, allergy, foodCategory, morbidityFile);

	    return req;
	}
}

