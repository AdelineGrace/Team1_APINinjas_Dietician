package api.stepdefinition;
//package stepdefinitions;
//
//import static io.restassured.RestAssured.given;
//
//import org.testng.Assert;
//
//import api.endpoints.*;
//import api.request.GetToken_Request;
//import api.request.PostPatient_request;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//public class GetDeletePatient_SD {
//	PatientEndpoints getPatient;
//	getPatient =new PatientEndpoints();
//	RequestSpecification request;
//	Response postPatientResponse;
//	String token, patientId;
//	GetToken_Request tokenReq;
//	PostPatient_request patientReq;
//	@Given("User is the registered Dietician with the valid {string} and {string}")
//	public void user_is_the_registered_dietician_with_the_valid_and(String email, String password) {
//		tokenReq = new GetToken_Request(email,password);
//		
//	}
//
//	@When("User sends HTTP POST Request for User login with valid endpoint")
//	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
//	    // Write code here that turns the phrase above into concrete actions
//		token=getPatient.getToken(tokenReq);
//	}
//
//	@Then("the bearer token is generated and is stored for future use")
//	public void the_bearer_token_is_generated_and_is_stored_for_future_use() {
//	    																															
//	}
//
//	@Given("User creates POST Request for the Dietician  API endpoint")
//	public void user_creates_post_request_for_the_dietician_api_endpoint() {
//		
//	}
//	
//	@When("the user calls a createPatientAPI with the details {string}, {string},{string}, {string}, {string},{string},{string}")
//	public void the_user_calls_a_create_patient_api_with_the_details(String FirstName, String LastName, String ContactNumber, String Email, String Allergy, String string6, String FoodCategory,String DateOfBirth) {
//		patientReq = new PostPatient_request(FirstName,LastName,ContactNumber,Email,Allergy,FoodCategory,DateOfBirth); 
//		postPatientResponse=getPatient.CreateAssignment(patientReq, token);
//		JsonPath jsonPath = new JsonPath(postPatientResponse.asString());
//        patientId = jsonPath.getString("patientId");
//	}
//
//	@Then("the response status code should be {int}")
//	public void the_response_status_code_should_be(Integer expectedStatusCode) {
//	    Integer actualStatusCode = postPatientResponse.getStatusCode();
//	    Assert.assertEquals(expectedStatusCode, actualStatusCode,"Unexpected status code");
//	}
//	
//
//	@Then("the response should contain a patient ID and is stored for future use")
//	public void the_response_should_contain_a_patient_id_and_is_stored_for_future_use() {
//		//Assert.assertEquals("Unexpected patient ID", expectedPatientId, actualPatientId);
//
//	}
//
//	@Given("User creates GET Request for the Dietician  API endpoint")
//	public void user_creates_get_request_for_the_dietician_api_endpoint() {
//	   
//	}
//
//	@When("the user calls  GETPatientAPI with GET http request")
//	public void the_user_calls_get_patient_api_with_get_http_request() {
//		postPatientResponse=getPatient.GetPatient(token);
//	}
//
//	@Then("the response should contain a list of patients")
//	public void the_response_should_contain_a_list_of_patients() {
//	    
//	}
//
//	@Then("the list should not be empty")
//	public void the_list_should_not_be_empty() {
//	   
//	}
//
//	@Given("User creates GET Request for the Dietician  invalid API endpoint")
//	public void user_creates_get_request_for_the_dietician_invalid_api_endpoint() {
//	    
//	}
//
//	@Given("User creates Delete Request for the Dietician  API endpoint")
//	public void user_creates_delete_request_for_the_dietician_api_endpoint() {
//	    
//	}
//
//	@When("the user calls  DELETEPatientAPI with DELETE http request")
//	public void the_user_calls_delete_patient_api_with_delete_http_request() {
//		postPatientResponse=getPatient.DeletePatientById(patienID, token);
//	}
//
//	@Then("the response should indicate successful deletion")
//	public void the_response_should_indicate_successful_deletion() {
//	    
//	}
//
//	@Given("User creates Delete Request for the Dietician API endpoint with invalid patientID")
//	public void user_creates_delete_request_for_the_dietician_api_endpoint_with_invalid_patient_id() {
//	   
//	}
//}
