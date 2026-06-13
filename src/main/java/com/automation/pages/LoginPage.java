package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Locators for Orange HRM Login Page
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//div[contains(@class, 'error')]");
    private By pageHeading = By.xpath("//h1");

    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        WebElement usernameElement = wait.waitForElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        WebElement passwordElement = wait.waitForElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        logger.info("Clicking login button");
        WebElement loginBtn = wait.waitForElementToBeClickable(loginButton);
        loginBtn.click();
    }

    public void login(String username, String password) {
        logger.info("Performing login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        logger.info("Getting error message");
        try {
            WebElement errorMsg = wait.waitForElementToBeVisible(errorMessage);
            return errorMsg.getText();
        } catch (Exception e) {
            logger.warn("Error message not found");
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.waitForElementToBeVisible(errorMessage) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageHeading() {
        logger.info("Getting page heading");
        return driver.findElement(pageHeading).getText();
    }
}

