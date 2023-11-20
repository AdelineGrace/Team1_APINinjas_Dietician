Feature: Update Patient with Put

Background: User sets the Authorization header with the Bearer token.
Given User is the registered Dietician with the valid "<password>" and "<UserLoginEmail>"
When User sends HTTP POST Request for User login with valid endpoint
Then User receives Bearer Token 

     @CreateAssignment
  Scenario Outline: Check if user able to update a Patient with valid endpoint and request body (non existing values)
    Given A valid existing Patient Id with request values from "<sheetName>" row "<header>"
    When Submit PUT request
    Then User receives response for PUT "<sheetName>" with "<header>"

    Examples: 
      | sheetName  |            header                    |
      |  patient   | Post_Patient_Valid                   |
      |  patient   | Post_Patient_ExistingUniqueField     |
      |  patient   | Post_Patient_NonAcceptedFoodCategory |
      |  patient   | Post_Patient_Missing_FirstName       |
      |  patient   | Post_Patient_Missing_LastName        |
      |  patient   | Post_Patient_Missing_Email           |
      |  patient   | Post_Patient_Missing_ContactNumber   |
      |  patient   | Post_Patient_Missing_DateOfBirth     |

#      | sheetName  |            header                   |
#      |  patient   | Put_Patient_Valid                   |
#      |  patient   | Put_Patient_ExistingUniqueField     |
#      |  patient   | Put_Patient_NonAcceptedFoodCategory |
#      |  patient   | Put_Patient_Missing_FirstName       |
#      |  patient   | Put_Patient_Missing_LastName        |
#      |  patient   | Put_Patient_Missing_Email           |
#      |  patient   | Put_Patient_Missing_ContactNumber   |
#      |  patient   | Put_Patient_Missing_DateOfBirth     | 