package api.stepdefinition;

import api.endpoints.Morbidity;

public class BaseSteps {
String baseUrl = " dietician-dev-41d9a344a720.herokuapp.com/dietician";
	
	Morbidity morbidityEndpoints;
	

    public BaseSteps() 
    {
    	morbidityEndpoints = new Morbidity(baseUrl);
		
		
    }

}
