Feature: Create Patient with Post

Background: User sets the Authorization header with the Bearer token.
Given User is the registered Dietician with the valid "<password>" and "<UserLoginEmail>"
When User sends HTTP POST Request for User login with valid endpoint
Then User receives Bearer Token 

 @CreateAssignment
  Scenario Outline: Check if user able to create a Patient with valid endpoint and request body (non existing values)
    Given User creates POST Request for the Dietician  API endpoint
    When  User sends HTTPS Request and  request Body with mandatory fields and morbidity files from "<sheetName>" with "<header>"  
    Then User receives response for POST "<sheetName>" with "<header>"

    Examples: 
      | sheetName  | header                                      |
      | reqres     | Post_User_Valid                          |
      | reqres     | Post_User_Existing                           |
      | reqres     | Post_user_MissingName                         |
      | reqres      | Post_user_MissingJob                          |