Feature: OrangeHRM Login Functionality
  As a user
  I want to be able to login to OrangeHRM
  So that I can access the dashboard

  @smoke @ui
  Scenario: User logs in with valid credentials
    Given User navigates to the login page
    When User enters valid username and password
    And User clicks the login button
    Then User should see the dashboard

  @ui @negative
  Scenario: User logs in with invalid credentials
    Given User navigates to the login page
    When User enters invalid username and password
    And User clicks the login button
    Then User should see an error message

  @ui @regression
  Scenario: User logout from the application
    Given User is logged in to the application
    When User clicks on user dropdown
    And User clicks logout button
    Then User should be redirected to login page

