package com.automation.stepdefinitions.api;

import com.automation.utils.APIClient;
import com.automation.utils.JsonUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class APIStepDefinitions {

    private static final Logger logger = LogManager.getLogger(APIStepDefinitions.class);
    private APIClient apiClient;
    private Response response;
    private JsonObject testData;
    private String storedObjectId;  // Store the object ID from POST response

    @Before
    public void setUp() {
        logger.info("Setting up API test environment");
        apiClient = new APIClient();
        testData = JsonUtils.getAPITestData();
    }

    @When("User sends a GET request to {string}")
    public void userSendsGetRequest(String endpoint) {
        logger.info("Sending GET request to: " + endpoint);
        response = apiClient.get(endpoint);
    }

    @Then("Response status code should be {int}")
    public void responseStatusCodeShouldBe(int expectedCode) {
        logger.info("Verifying response status code: " + expectedCode);
        assertThat(response.getStatusCode()).isEqualTo(expectedCode);
    }

    @Then("Response should contain list of objects")
    public void responseShouldContainObjectsList() {
        logger.info("Verifying response contains objects list");
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isNotNull().isNotEmpty();
        logger.info("Response body: " + responseBody);
    }

    @When("User sends a POST request to {string} with valid payload")
    public void userSendsPostRequestWithPayload(String endpoint) {
        logger.info("Sending POST request to: " + endpoint);
        JsonObject createObject = JsonUtils.getValueAsJsonObject(testData, "createObject");
        String payload = JsonUtils.toJson(createObject);
        response = apiClient.post(endpoint, payload);
    }

    @Then("Response should contain the created object details")
    public void responseShouldContainCreatedObject() {
        logger.info("Verifying response contains created object");
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isNotNull().isNotEmpty();
        assertThat(responseBody).containsAnyOf("name", "id");
    }

    @When("User sends a PUT request to {string} with update payload")
    public void userSendsPutRequestWithPayload(String endpoint) {
        logger.info("Sending PUT request to: " + endpoint);
        JsonObject updateObject = JsonUtils.getValueAsJsonObject(testData, "updateObject");
        String payload = JsonUtils.toJson(updateObject);
        response = apiClient.put(endpoint, payload);
    }

    @Then("Response should contain updated object details")
    public void responseShouldContainUpdatedObject() {
        logger.info("Verifying response contains updated object");
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isNotNull().isNotEmpty();
    }

    @When("User sends a DELETE request to {string}")
    public void userSendsDeleteRequest(String endpoint) {
        logger.info("Sending DELETE request to: " + endpoint);
        response = apiClient.delete(endpoint);
    }

    @Then("Response status code should be {int} or {int}")
    public void responseStatusCodeShouldBeOneOf(int code1, int code2) {
        logger.info("Verifying response status code is either: " + code1 + " or " + code2);
        int actualCode = response.getStatusCode();
        assertThat(actualCode).isIn(code1, code2);
    }

    @Then("Object should be deleted successfully")
    public void objectShouldBeDeletedSuccessfully() {
        logger.info("Verifying object is deleted successfully");
        int statusCode = response.getStatusCode();
        assertThat(statusCode).isIn(200, 204);
    }

    /**
     * Extract and store the object ID from POST response
     * Supports multiple common ID field names: id, _id, ID
     */
    @Then("Extract and store the object ID from POST response")
    public void extractAndStoreObjectId() {
        logger.info("Extracting and storing object ID from POST response");
        String responseBody = response.getBody().asString();
        logger.info("Response body: " + responseBody);

        try {
            JsonObject responseJson = JsonParser.parseString(responseBody).getAsJsonObject();

            // Try to extract ID from common field names
            if (responseJson.has("id")) {
                storedObjectId = responseJson.get("id").getAsString();
            } else if (responseJson.has("_id")) {
                storedObjectId = responseJson.get("_id").getAsString();
            } else if (responseJson.has("ID")) {
                storedObjectId = responseJson.get("ID").getAsString();
            } else {
                throw new AssertionError("No ID field found in response. Common fields: id, _id, ID");
            }

            logger.info("Successfully extracted and stored object ID: " + storedObjectId);
            assertThat(storedObjectId).isNotNull().isNotEmpty();
        } catch (Exception e) {
            logger.error("Error extracting ID from response: " + e.getMessage());
            throw new AssertionError("Failed to extract object ID from response: " + e.getMessage());
        }
    }

    /**
     * Send GET request using the stored object ID
     */
    @When("User sends a GET request to the stored object endpoint")
    public void userSendsGetRequestToStoredObjectEndpoint() {
        logger.info("Sending GET request to stored object endpoint with ID: " + storedObjectId);
        assertThat(storedObjectId).isNotNull().isNotEmpty()
                .withFailMessage("Object ID not stored. Please extract ID first.");

        String endpoint = "/objects/" + storedObjectId;
        response = apiClient.get(endpoint);
    }

    /**
     * Send PUT request using the stored object ID
     */
    @When("User sends a PUT request to the stored object endpoint with update payload")
    public void userSendsPutRequestToStoredObjectEndpoint() {
        logger.info("Sending PUT request to stored object endpoint with ID: " + storedObjectId);
        assertThat(storedObjectId).isNotNull().isNotEmpty()
                .withFailMessage("Object ID not stored. Please extract ID first.");

        String endpoint = "/objects/" + storedObjectId;
        JsonObject updateObject = JsonUtils.getValueAsJsonObject(testData, "updateObject");
        String payload = JsonUtils.toJson(updateObject);
        logger.info("Update payload: " + payload);
        response = apiClient.put(endpoint, payload);
    }

    /**
     * Send DELETE request using the stored object ID
     */
    @When("User sends a DELETE request to the stored object endpoint")
    public void userSendsDeleteRequestToStoredObjectEndpoint() {
        logger.info("Sending DELETE request to stored object endpoint with ID: " + storedObjectId);
        assertThat(storedObjectId).isNotNull().isNotEmpty()
                .withFailMessage("Object ID not stored. Please extract ID first.");

        String endpoint = "/objects/" + storedObjectId;
        response = apiClient.delete(endpoint);
    }

    /**
     * Verify response contains object details
     */
    @Then("Response should contain the object details")
    public void responseShouldContainObjectDetails() {
        logger.info("Verifying response contains object details");
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isNotNull().isNotEmpty();
        logger.info("Response body: " + responseBody);

        // Verify the response contains the stored ID
        assertThat(responseBody).contains(storedObjectId)
                .withFailMessage("Response does not contain the stored object ID: " + storedObjectId);
    }
}

