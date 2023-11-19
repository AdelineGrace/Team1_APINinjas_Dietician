package api.endpoints;

import Utilities.ConfigReader;
import api.request.PostPatient_request;
import api.routes.PostPatient_routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostPatient {
	public static Response CreatePatient(PostPatient_request postreq)
	{
		RestAssured.baseURI = ConfigReader.BaseURL();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
				
		Response response = request.body(postreq).post(PostPatient_routes.createPost());
		
		response.then().log().all();
		
		return response;
	}

}
