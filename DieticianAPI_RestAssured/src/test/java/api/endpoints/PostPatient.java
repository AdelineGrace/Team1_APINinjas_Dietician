package api.endpoints;

import java.io.File;

import Utilities.ConfigReader;
import api.request.PostPatient_request;
import api.routes.PostPatient_routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostPatient {
	static String baseUrl;
	static Response response;
	public PostPatient(String baseUrl)
	{
		PostPatient.baseUrl = baseUrl;
	}
	
	public static Response CreatePatient(PostPatient_request postreq,boolean hasFile)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		if (hasFile) {
        String filePath = ConfigReader.MorbidityFile();
        File file = new File(filePath);
         response = request.body(postreq)
                .multiPart("file", file)
                .post(PostPatient_routes.createPost());
    } else {
    	 response = request.body(postreq)
                .post(PostPatient_routes.createPost());
    }
	   
		response.then().log().all();
		
		return response;
	}

}
