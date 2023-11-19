package api.endpoints;

import Utilities.ConfigReader;
import api.request.PutPatient_request;
import api.routes.PutPatient_routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutPatient {
	public static Response UpdatePatient(PutPatient_request putreq)
	{
		RestAssured.baseURI = ConfigReader.BaseURL();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
				
		Response response = request.body(putreq).post(PutPatient_routes.createPut());
		
		response.then().log().all();
		
		return response;
	}

}