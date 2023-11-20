package api.stepdefinition;

import Utilities.ConfigReader;
import api.endpoints.PostPatient;
import api.endpoints.UserLogin;

public class BaseStep {
String baseUrl = ConfigReader.BaseURL();
	
PostPatient postpatient;
UserLogin login;
	

    public BaseStep() 
    {
    	postpatient = new PostPatient(baseUrl);
    	login = new UserLogin(baseUrl);
}
}