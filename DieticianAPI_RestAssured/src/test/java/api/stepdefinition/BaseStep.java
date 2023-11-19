package api.stepdefinition;

import Utilities.ConfigReader;
import api.endpoints.PostPatient;

public class BaseStep {
String baseUrl = ConfigReader.BaseURL();
	
PostPatient postpatient;
	

    public BaseStep() 
    {
    	postpatient = new PostPatient(baseUrl);
		
}
}