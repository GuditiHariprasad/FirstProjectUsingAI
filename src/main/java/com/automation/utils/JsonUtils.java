package com.automation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final Logger logger = LogManager.getLogger(JsonUtils.class);
    private static final Gson gson = new Gson();

    public static JsonObject readJsonFile(String filePath) {
        try {
            JsonElement element = JsonParser.parseReader(new FileReader(filePath));
            logger.info("JSON file loaded successfully: " + filePath);
            return element.getAsJsonObject();
        } catch (IOException e) {
            logger.error("Failed to read JSON file: " + filePath, e);
            throw new RuntimeException("Could not read JSON file: " + filePath, e);
        }
    }

    public static JsonObject getUITestData() {
        return readJsonFile("src/main/resources/ui_test_data.json");
    }

    public static JsonObject getAPITestData() {
        return readJsonFile("src/main/resources/api_test_data.json");
    }

    public static String getValueAsString(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    public static int getValueAsInt(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsInt();
    }

    public static boolean getValueAsBoolean(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsBoolean();
    }

    public static JsonObject getValueAsJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsJsonObject();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}

