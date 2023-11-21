package api.endpoints;

import api.request.UserLogin_request;
import api.routes.UserLogin_routes;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserLogin {
	static Response response;
	static String baseUrl;
	public UserLogin(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}
	
	public static Response UserLoginCredentials(UserLogin_request data)
	{
		//RestAssured.baseURI = ConfigReader.BaseURL();
		//RestAssured.baseURI = "dietician-dev-41d9a344a720.herokuapp.com/dietician";

		RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
    	 response = request.body(data)
                .post(UserLogin_routes.userlogin());
    
		
		return response;
	}

}
