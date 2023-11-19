package api.stepdefinition;

import api.endpoints.PostPatient;

import api.endpoints.UserDietician;

public class BaseStep {
	
		
		String baseUrl = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		
		PostPatient postPatientEndpoints;
		UserDietician userDieticianEndpoints;
		
	    public BaseStep() 
	    {
	    	postPatientEndpoints = new PostPatient(baseUrl);
	    	userDieticianEndpoints = new UserDietician(baseUrl);
			
	    }

}
