package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class APIClient {

    private static final Logger logger = LogManager.getLogger(APIClient.class);
    private String baseURL;
    private Response response;

    public APIClient() {
        this.baseURL = ConfigReader.getAPIBaseURL();
        RestAssured.baseURI = this.baseURL;
        logger.info("APIClient initialized with base URL: " + this.baseURL);
    }

    public APIClient(String baseURL) {
        this.baseURL = baseURL;
        RestAssured.baseURI = this.baseURL;
        logger.info("APIClient initialized with base URL: " + this.baseURL);
    }

    public Response get(String endpoint) {
        logger.info("Sending GET request to: " + endpoint);
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response get(String endpoint, Map<String, String> headers) {
        logger.info("Sending GET request to: " + endpoint + " with headers");
        this.response = RestAssured.given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response post(String endpoint, Object payload) {
        logger.info("Sending POST request to: " + endpoint);
        logger.debug("Payload: " + JsonUtils.toJson(payload));
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response post(String endpoint, String payload) {
        logger.info("Sending POST request to: " + endpoint);
        logger.debug("Payload: " + payload);
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response put(String endpoint, Object payload) {
        logger.info("Sending PUT request to: " + endpoint);
        logger.debug("Payload: " + JsonUtils.toJson(payload));
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response put(String endpoint, String payload) {
        logger.info("Sending PUT request to: " + endpoint);
        logger.debug("Payload: " + payload);
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response delete(String endpoint) {
        logger.info("Sending DELETE request to: " + endpoint);
        this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        return this.response;
    }

    public Response getLastResponse() {
        return this.response;
    }

    public int getResponseStatusCode() {
        return this.response.getStatusCode();
    }

    public String getResponseBody() {
        return this.response.getBody().asString();
    }

    public <T> T getResponseAsObject(Class<T> classOfT) {
        return this.response.as(classOfT);
    }
}

