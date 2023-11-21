
Feature:
GET Morbidities
    
   Background: User sets the Authorization header with the Bearer token
   Given User is the registered Dietician with the valid "<password>" and "<UserLoginEmail>"
   When User sends HTTP POST Request for User login with valid endpoint
   Then User receives Bearer Token 
    
    @Get_all_Morbidites
    Scenario Outline:
    Check if user able to retrieve all Morbidities with API End point "<DataKey>"
    Given User creates GET Request for the Dietician API endpoint (no parameters)
    When User sends HTTPS Request with "<DataKey>"
    Then User receives Status Code  with response body for endpoint "<DataKey>"
    Examples:
      | DataKey |
      | Valid   |
      | Invalid |
  
  
    @Get_Morbidity_condition_by_Test_name
    Scenario Outline:
    Check if user able to retrieve Morbidities by morbidity test name   
    Given User creates GET Request for the Dietician API endpoint with morbidity test name. 
    When User sends the HTTPS Request after setting of morbidity Test Name "<DataKey>"
    Then User receives Status Code  with response body with morbidity test name endpoint  "<DataKey>"
    Examples: 
      | DataKey |
      | Valid   |
      | Invalid |
      
    @Get_User_Logout
    Scenario Outline:
    Check if user able logout by endpoint "<DataKey>"  
    Given User creates GET Request for the Dietician API endpoint "<DataKey>" 
    When User sends the HTTPS Request after setting of User logout endpoint.
    Then User receives Status Code  with response body for logout endpoint "<DataKey>"
     Examples: 
      | DataKey |
      | Valid   |
      | Invalid |
    
    
    
   
    
    
    


