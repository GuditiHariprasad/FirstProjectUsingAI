package com.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            String propertiesPath = "src/main/resources/config.properties";
            FileInputStream fis = new FileInputStream(propertiesPath);
            properties.load(fis);
            fis.close();
            logger.info("Properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            throw new RuntimeException("Could not load properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static String getUIBaseURL() {
        return getProperty("ui.base.url");
    }

    public static String getAPIBaseURL() {
        return getProperty("api.base.url");
    }

    public static int getImplicitWait() {
        return getIntProperty("ui.implicit.wait");
    }

    public static int getExplicitWait() {
        return getIntProperty("ui.explicit.wait");
    }

    public static String getBrowser() {
        return getProperty("ui.browser");
    }

    public static boolean isHeadless() {
        return getBooleanProperty("ui.headless");
    }
}

