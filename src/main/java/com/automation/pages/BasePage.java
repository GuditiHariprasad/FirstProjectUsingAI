package com.automation.pages;

import com.automation.utils.DriverFactory;
import com.automation.utils.WebElementWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebElementWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebElementWait(driver);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void takeScreenshot(String screenshotName) {
        logger.info("Taking screenshot: " + screenshotName);
        // Screenshot functionality can be added here
    }
}

