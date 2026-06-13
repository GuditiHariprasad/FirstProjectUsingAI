Feature: RESTful API Testing
  As an API tester
  I want to test the RESTful API endpoints
  So that I can verify the API functionality

  @api @smoke
  Scenario: Get all objects from API
    When User sends a GET request to "/objects"
    Then Response status code should be 200
    And Response should contain list of objects

  @api @smoke
  Scenario: Create a new object via API
    When User sends a POST request to "/objects" with valid payload
    Then Response status code should be 200 or 201
    And Response should contain the created object details
    And Extract and store the object ID from POST response

  @api @regression
  Scenario: Update an existing object
    When User sends a PUT request to "/objects/1" with update payload
    Then Response status code should be 200
    And Response should contain updated object details

  @api @regression
  Scenario: Delete an object
    When User sends a DELETE request to "/objects/1"
    Then Response status code should be 204 or 200
    And Object should be deleted successfully

  @api @negative
  Scenario: Get non-existent object
    When User sends a GET request to "/objects/invalid-id"
    Then Response status code should be 400 or 404

