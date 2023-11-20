package api.stepdefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import Context.SharedContext;
import Utilities.ConfigReader;
import Utilities.DynamicValues;
import Utilities.LoggerLoad;
import api.endpoints.PostPatient;
import api.endpoints.UserLogin;
import api.request.PostPatient_request;
import api.request.UserLogin_request;
import api.response.PostPatient_response;
import Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class Post_StepDefinition extends BaseStep  {
	PostPatient_request patientRequest;
	Map<String, String> excelData;
	String sheetName;
	String header;
	RequestSpecification request;
	Response response;
  static int patientIdValid = 0;
  static int patientIdMissingFile = 0;
  static String existingPhoneNumber =null;
  static int fileId = 0;

  static String existingEmail = null;
	PostPatient_response patient;
	UserLogin_request Alogin; 
    public static String bearerToken;
    String dateOfBirth;
       
	@Given("User is the registered Dietician with the valid {string} and {string}")
	public void user_is_the_registered_dietician_with_the_valid_and(String password, String UserLoginEmail) throws IOException {
		
		 String filePath = ConfigReader.AloginCredentials();
        
         BufferedReader reader = new BufferedReader(new FileReader(filePath));

         String jsonContent = reader.lines().reduce("", (line1, line2) -> line1 + line2);

         JSONObject jsonObject = new JSONObject(jsonContent);

         String passwor = jsonObject.optString("password");
         String userLoginEmail = jsonObject.optString("userLoginEmail");

         Alogin = new UserLogin_request(passwor,userLoginEmail);
       
         reader.close();
     } 
     	
		
	
	@When("User sends HTTP POST Request for User login with valid endpoint")
	public void user_sends_http_post_request_for_user_login_with_valid_endpoint() {
		response =  UserLogin.UserLoginCredentials(Alogin);
	    }
	

	@Then("User receives Bearer Token")
	public void user_receives_bearer_token() {
	    response.then().statusCode(200); 
	    LoggerLoad.logInfo("BearerToken was created");
	    bearerToken = response.jsonPath().getString("token");

	}

	@Given("User creates POST Request for the Dietician  API endpoint with {string} and {string}")
	public void user_creates_post_request_for_the_dietician_api_endpoint_with(String sheetName, String header) throws Exception {
		patientRequest = CreatePatientRequest(sheetName, header);
		LoggerLoad.logInfo("Patient POST request object created for- " + header);
		
	}

	@When("User sends HTTPS Request and  request Body with mandatory fields and morbidity files from {string} with {string}")
	public void user_sends_https_request_and_request_body_with_mandatory_fields_and_morbidity_files_from_with(String sheetName, String header) {
		
		response = PostPatient.CreatePatient(patientRequest,true);
		//response = PostPatient.CreatePatient(patientRequest,false);

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
							getClass().getClassLoader().getResourceAsStream(ConfigReader.PostPatientSchema())));
					
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
							getClass().getClassLoader().getResourceAsStream(ConfigReader.BadRequestSchema())));
					
					JsonPath jsonPathEvaluator = response.jsonPath();

					assertEquals(excelData.get("errorCode"), jsonPathEvaluator.get("errorCode"));
					assertEquals(excelData.get("errorMessage"), jsonPathEvaluator.get("errorMessage"));

					break;
					
									
			}			
	}
	

	private PostPatient_request CreatePatientRequest(String sheetName, String header) throws Exception {
		excelData = ExcelReader.getData(header, sheetName);
		String firstName = excelData.get("FirstName");
	    String lastName = excelData.get("LastName");
	    String email = excelData.get("Email");
//	   String contactNumberAsString = excelData.get("ContactNumber");
//	    Long contactNumber = Long.parseLong(contactNumberAsString);	    //Long contactNumber = Long.parseLong(contactNumberAsString);
           // int contactNumber = DynamicValues.PhoneNumber();
        //int contactNumber = ;
		   String contactNumber = excelData.get("ContactNumber");

            		//contactNumberLong.intValue();
	    String dateOfBirthExcel = excelData.get("DateOfBirth");
        String dateOfBirth = convertExcelDate(Integer.parseInt(dateOfBirthExcel));
	    String allergy = excelData.get("Allergy");
	    String foodCategory = excelData.get("FoodCategory");
	    

	    switch (header) {
	        case "Post_Patient_Valid":
	        case "Post_Patient_ExistingUniqueField":
	        	if(header.equals("Post_Patient_ExistingUniqueField"))
				{
	        		 contactNumber  = SharedContext.getExistingPhoneNumber();
	        		email = SharedContext.getExistingEmail();
				}
	        	break;
	        case "Post_Patient_NonAcceptedFoodCategory":
	        case "Post_Patient_Missing_FirstName":
	        case "Post_Patient_Missing_LastName":
	        case "Post_Patient_Invalid_Email":
	        case "Post_Patient_Missing_ContactNumber":
	        	if(header.equals("Post_Patient_Missing_ContactNumber"))
				{
	        		 contactNumber  = null;
				}
	        	break;
	        case "Post_Patient_Missing_DateOfBirth":
	        	if(header.equals("Post_Patient_Missing_DateOfBirth"))
				{
					dateOfBirth  = null;
				}
	        	break;
	        case "Post_Patient_Missing_File":
	            break;
	        default:
	            response.then().log().all();
	            return null;
	    }
	    
	    PostPatient_request patientRequest = new PostPatient_request(firstName, lastName, contactNumber,email,allergy,
	            foodCategory,dateOfBirth);

	    switch (header) {
	        case "Post_Patient_Valid":
	            Response responseValid = PostPatient.CreatePatient(patientRequest,true);
	            responseValid.then().log().all();

	            if (responseValid.statusCode() == 201) {
	                PostPatient_response patientValid = responseValid.getBody().as(PostPatient_response.class);
	                patientIdValid = patientValid.patientId;
	                existingPhoneNumber = patientValid.ContactNumber;
	                existingEmail = patientValid.Email;
	                fileId = patientValid.fileId;
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

private static String convertExcelDate(int excelDate) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date referenceDate;
  		referenceDate = dateFormat.parse("1900-01-01");
		Date resultDate = new Date(referenceDate.getTime() + ((long) excelDate - 2) * 24 * 60 * 60 * 1000);
   
    
    return dateFormat.format(resultDate);
}


}