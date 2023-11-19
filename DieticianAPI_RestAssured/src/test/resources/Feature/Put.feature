Feature: Update Patient with Put

Background: User sets the Authorization header with the Bearer token.
Given User is the registered Dietician with the valid "<password>" and "<UserLoginEmail>"
When User sends HTTP POST Request for User login with valid endpoint
Then User receives Bearer Token 

 @CreateAssignment
  Scenario Outline: Check if user able to Update a Patient with valid endpoint and request body (non existing values)
    Given User creates PUT Request for the Dietician  API endpoint
    When  User sends HTTPS Request and  request Body with mandatory fields and morbidity files from "<sheetName>" with "<header>"  
    Then User receives response for PUT "<sheetName>" with "<header>"

    Examples: 
      | sheetName  |            header                    |
      |  patient   | Put_Patient_Valid                   |
      |  patient   | Put_Patient_InvalidPatientId       |
      |  patient   | Put_Patient_Missing_FirstName       |
      |  patient   | Put_Patient_Missing_LastName        |
      |  patient   | Put_Patient_Missing_Email           |
      |  patient   | Put_Patient_Missing_ContactNumber   |
      |  patient   | Put_Patient_Missing_DateOfBirth     |
      
     