package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // Locators for Home Page after successful login
    private By userGreeting = By.xpath("//p[@class='oxd-userdropdown-name']");
    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");
    private By logoutButton = By.xpath("//a[contains(@href, 'logout')]");
    private By userDropdown = By.xpath("//img[@alt='profile picture']");
    private By userMenuOptions = By.xpath("//a[@class='oxd-topbar-body-nav-tab']");

    public boolean isDashboardDisplayed() {
        logger.info("Checking if dashboard is displayed");
        try {
            return wait.waitForElementToBeVisible(dashboardHeading) != null;
        } catch (Exception e) {
            logger.warn("Dashboard not found");
            return false;
        }
    }

    public String getUserGreeting() {
        logger.info("Getting user greeting");
        WebElement greeting = wait.waitForElementToBeVisible(userGreeting);
        return greeting.getText();
    }

    public void clickUserDropdown() {
        logger.info("Clicking user dropdown");
        WebElement dropdown = wait.waitForElementToBeClickable(userDropdown);
        dropdown.click();
    }

    public void logout() {
        logger.info("Performing logout");
        clickUserDropdown();
        WebElement logout = wait.waitForElementToBeClickable(logoutButton);
        logout.click();
    }

    public boolean isUserLoggedIn() {
        logger.info("Checking if user is logged in");
        try {
            return wait.waitForElementToBeVisible(userGreeting) != null;
        } catch (Exception e) {
            logger.warn("User is not logged in");
            return false;
        }
    }
}

