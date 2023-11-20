package api.endpoints;

import java.io.File;

import Utilities.ConfigReader;
import api.request.PutPatient_request;
import api.routes.PutPatient_routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutPatient {
	static String baseUrl;
	static Response response;
	public PutPatient(String baseUrl)
	{
		PostPatient.baseUrl = baseUrl;
	}
	public static Response UpdatePatient(PutPatient_request putreq,boolean hasFile)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
				
		Response response = request.body(putreq).post(PutPatient_routes.createPut());
		if (hasFile) {
	        String filePath = ConfigReader.MorbidityFile();
	        File file = new File(filePath);
	         response = request.body(putreq)
	                .multiPart("file", file)
	                .post(PutPatient_routes.createPut());
	    } else {
	    	 response = request.body(putreq)
	                .post(PutPatient_routes.createPut());
	    }
		response.then().log().all();
		
		return response;
	}

}