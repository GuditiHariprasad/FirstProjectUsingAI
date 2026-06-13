package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {"com.automation.stepdefinitions.ui"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ui-report.html",
                "json:target/cucumber-json-reports/ui-report.json",
                "junit:target/cucumber-reports/ui-report.xml"
        },
        tags = "@ui",
        monochrome = false,
        dryRun = false
)
public class UITestRunner {
}

