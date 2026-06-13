package com.automation.stepdefinitions.ui;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import com.automation.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefinitions {

    private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
    private LoginPage loginPage;
    private HomePage homePage;
    private JsonObject testData;

    @Before
    public void setUp() {
        logger.info("Setting up test environment");
        DriverFactory.initializeDriver();
        testData = JsonUtils.getUITestData();
    }

    @After
    public void tearDown() {
        logger.info("Tearing down test environment");
        DriverFactory.closeDriver();
    }

    @Given("User navigates to the login page")
    public void userNavigatesToLoginPage() {
        logger.info("Navigating to login page");
        DriverFactory.navigateTo(ConfigReader.getUIBaseURL());
        loginPage = new LoginPage();
        assertThat(loginPage.getCurrentURL()).contains("login");
    }

    @When("User enters valid username and password")
    public void userEntersValidCredentials() {
        logger.info("Entering valid credentials");
        loginPage = new LoginPage();
        JsonObject validLogin = JsonUtils.getValueAsJsonObject(testData, "validLogin");
        String username = JsonUtils.getValueAsString(validLogin, "username");
        String password = JsonUtils.getValueAsString(validLogin, "password");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User enters invalid username and password")
    public void userEntersInvalidCredentials() {
        logger.info("Entering invalid credentials");
        loginPage = new LoginPage();
        JsonObject invalidLogin = JsonUtils.getValueAsJsonObject(testData, "invalidLogin");
        String username = JsonUtils.getValueAsString(invalidLogin, "username");
        String password = JsonUtils.getValueAsString(invalidLogin, "password");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User clicks the login button")
    public void userClicksLoginButton() {
        logger.info("Clicking login button");
        loginPage.clickLoginButton();
    }

    @Then("User should see the dashboard")
    public void userShouldSeeDashboard() {
        logger.info("Verifying user sees dashboard");
        homePage = new HomePage();
        try {
            Thread.sleep(2000); // Wait for page load
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        assertThat(homePage.isDashboardDisplayed()).isTrue();
    }

    @Then("User should see an error message")
    public void userShouldSeeErrorMessage() {
        logger.info("Verifying error message is displayed");
        loginPage = new LoginPage();
        try {
            Thread.sleep(1000); // Wait for error to display
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
    }

    @Given("User is logged in to the application")
    public void userIsLoggedIn() {
        logger.info("User logging in to the application");
        DriverFactory.navigateTo(ConfigReader.getUIBaseURL());
        loginPage = new LoginPage();
        JsonObject validLogin = JsonUtils.getValueAsJsonObject(testData, "validLogin");
        String username = JsonUtils.getValueAsString(validLogin, "username");
        String password = JsonUtils.getValueAsString(validLogin, "password");

        loginPage.login(username, password);
        try {
            Thread.sleep(3000); // Wait for login to complete
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        homePage = new HomePage();
        assertThat(homePage.isUserLoggedIn()).isTrue();
    }

    @When("User clicks on user dropdown")
    public void userClicksUserDropdown() {
        logger.info("Clicking user dropdown");
        homePage = new HomePage();
        homePage.clickUserDropdown();
    }

    @When("User clicks logout button")
    public void userClicksLogoutButton() {
        logger.info("Clicking logout button");
        homePage.logout();
        try {
            Thread.sleep(2000); // Wait for logout to complete
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    @Then("User should be redirected to login page")
    public void userShouldBeRedirectedToLoginPage() {
        logger.info("Verifying user is redirected to login page");
        assertThat(DriverFactory.getDriver().getCurrentUrl()).contains("login");
    }
}



