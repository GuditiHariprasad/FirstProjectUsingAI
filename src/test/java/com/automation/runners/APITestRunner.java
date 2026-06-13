package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"com.automation.stepdefinitions.api"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/api-report.html",
                "json:target/cucumber-json-reports/api-report.json",
                "junit:target/cucumber-reports/api-report.xml"
        },
        tags = "@api",
        monochrome = false,
        dryRun = false
)
public class APITestRunner {
}

