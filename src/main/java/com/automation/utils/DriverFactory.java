package com.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static WebDriver driver;

    public static WebDriver initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        logger.info("Initializing WebDriver for browser: " + browser);

        switch (browser) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                driver = createFirefoxDriver();
                break;
            default:
                logger.error("Unsupported browser: " + browser);
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        // Set timeouts
        driver.manage().timeouts().implicitlyWait(ConfigReader.getImplicitWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("WebDriver initialized successfully");
        return driver;
    }

    private static WebDriver createChromeDriver() {
        logger.info("Creating Chrome WebDriver");
        // Note: Ensure chromedriver is in system PATH or update the path below
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            logger.info("Running Chrome in headless mode");
        }

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        logger.info("Creating Firefox WebDriver");
        // Note: Ensure geckodriver is in system PATH or update the path below
        // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Running Firefox in headless mode");
        }

        return new FirefoxDriver(options);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.warn("WebDriver not initialized. Initializing now.");
            initializeDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver closed successfully");
        }
    }

    public static void navigateTo(String url) {
        getDriver().navigate().to(url);
        logger.info("Navigated to URL: " + url);
    }
}



