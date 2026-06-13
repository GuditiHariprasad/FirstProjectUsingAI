package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.automation.stepdefinitions.ui",
                "com.automation.stepdefinitions.api"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/combined-report.html",
                "json:target/cucumber-json-reports/combined-report.json",
                "junit:target/cucumber-reports/combined-report.xml"
        },
        tags = "@smoke or @regression or @negative",
        monochrome = false,
        dryRun = false
)
public class CombinedTestRunner {
}

