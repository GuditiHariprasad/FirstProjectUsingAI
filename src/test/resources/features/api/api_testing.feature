Feature: RESTful API Testing
  As an API tester
  I want to test the RESTful API endpoints
  So that I can verify the API functionality

  @api @smoke
  Scenario: Get all objects from API
    When User sends a GET request to "/objects"
    Then Response status code should be 200
    And Response should contain list of objects

  @api @smoke @critical
  Scenario: Complete CRUD operation with dynamically stored object ID
    # Step 1: CREATE - Post a new object and store the ID
    When User sends a POST request to "/objects" with valid payload
    Then Response status code should be 200 or 201
    And Response should contain the created object details
    And Extract and store the object ID from POST response

    # Step 2: RETRIEVE - Get the object using stored ID
    When User sends a GET request to the stored object endpoint
    Then Response status code should be 200
    And Response should contain the object details

    # Step 3: UPDATE - Update the object using stored ID
    When User sends a PUT request to the stored object endpoint with update payload
    Then Response status code should be 200
    And Response should contain updated object details

    # Step 4: DELETE - Delete the object using stored ID
    When User sends a DELETE request to the stored object endpoint
    Then Response status code should be 204 or 200
    And Object should be deleted successfully

  @api @regression
  Scenario: Create and Retrieve object using stored ID
    # Create a new object and store its ID
    When User sends a POST request to "/objects" with valid payload
    Then Response status code should be 200 or 201
    And Response should contain the created object details
    And Extract and store the object ID from POST response

    # Retrieve using the stored ID
    When User sends a GET request to the stored object endpoint
    Then Response status code should be 200
    And Response should contain the object details

  @api @regression
  Scenario: Update object using stored ID from creation
    # Create a new object and store its ID
    When User sends a POST request to "/objects" with valid payload
    Then Response status code should be 200 or 201
    And Response should contain the created object details
    And Extract and store the object ID from POST response

    # Update the created object using stored ID
    When User sends a PUT request to the stored object endpoint with update payload
    Then Response status code should be 200
    And Response should contain updated object details

  @api @regression
  Scenario: Delete object using stored ID from creation
    # Create a new object and store its ID
    When User sends a POST request to "/objects" with valid payload
    Then Response status code should be 200 or 201
    And Response should contain the created object details
    And Extract and store the object ID from POST response

    # Delete the created object using stored ID
    When User sends a DELETE request to the stored object endpoint
    Then Response status code should be 204 or 200
    And Object should be deleted successfully

  @api @negative
  Scenario: Get non-existent object
    When User sends a GET request to "/objects/invalid-id"
    Then Response status code should be 400 or 404

