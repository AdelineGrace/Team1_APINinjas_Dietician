Feature: Validating GET and DELETE Dietician API methods
   
   Scenario Outline: Bearer Key
    Given User is the registered Dietician with the valid "<password>" and "<UserLoginEmail>"
    When User sends HTTP POST Request for User login with valid endpoint
    Then the bearer token is generated and is stored for future use
     Examples:
     | password   | UserLoginEmail |
     | Spark33   | ssasangee@gmail.com |
  Scenario Outline: Create a Patient
    Given User creates POST Request for the Dietician  API endpoint
    When the user calls a createPatientAPI with the details "<FirstName>", "<LastName>","<ContactNumber>", "<Email>", "<Allergy>","<FoodCategory>","<DateOfBirth>"  
    Then the response status code should be 200
    And the response should contain a patient ID and is stored for future use
    Examples:
      |FirstName|LastName|ContactNumber|Email|Allergy|FoodCategory|DateOfBirth|
      | Sangee  | Shroff | 9993451023| sangee@gmail.com|None|Vegan| 2000-01-01|
   
  Scenario: Positive Get All Patients 
    Given User creates GET Request for the Dietician  API endpoint 
    When the user calls  GETPatientAPI with GET http request
    Then the response status code should be 200
    And the response should contain a list of patients
    And the list should not be empty
    
    Scenario: Attempt to Get All Patients with Iinvalid API endpoint 
    Given User creates GET Request for the Dietician  invalid API endpoint 
    When the user calls  GETPatientAPI with GET http request
   Then the response status code should be 200
    And the response should contain a list of patients
    And the list should not be empty
    
    Scenario: Delete the Last Created Patient
    Given User creates Delete Request for the Dietician  API endpoint 
    When the user calls  DELETEPatientAPI with DELETE http request
   Then the response status code should be 200
    And the response should indicate successful deletion
          
    Scenario: Delete with invalid patientID
    Given User creates Delete Request for the Dietician API endpoint with invalid patientID
    When the user calls  DELETEPatientAPI with DELETE http request
  Then the response status code should be 200
    
    
   
    
    
