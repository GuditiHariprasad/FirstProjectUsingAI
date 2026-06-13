# Cucumber Reporting Guide

## Overview
This project is configured to generate comprehensive Cucumber reports in multiple formats:
- **HTML Reports** - Visual, user-friendly reports
- **JSON Reports** - Machine-readable reports for integration with CI/CD
- **JUnit XML Reports** - For integration with test management tools

---

## Report Generation Methods

### Method 1: Using Maven Command (Recommended)

#### Generate All Reports (API + UI + Combined)
```bash
mvn clean verify
```

This command will:
1. Clean previous builds
2. Run all tests via the test runners
3. Generate JSON reports in `target/cucumber-json-reports/`
4. Generate HTML reports in `target/cucumber-reports/`
5. Generate JUnit XML reports in `target/cucumber-reports/`

#### Generate Only API Tests
```bash
mvn clean verify -Dgroups="@api"
```

#### Generate Only UI Tests
```bash
mvn clean verify -Dgroups="@ui"
```

#### Generate Combined Tests
```bash
mvn clean verify -Dgroups="@smoke or @regression"
```

---

### Method 2: Using IntelliJ IDE

#### Option A: Run via Maven Plugin
1. Open the **Maven** panel on the right sidebar
2. Navigate to: `FirstProjectUsingAI` → `Lifecycle`
3. Double-click on `clean` first
4. Then double-click on `verify`
5. Reports will be generated automatically

#### Option B: Run via Test Runners
1. Right-click on any test runner class:
   - `UITestRunner.java`
   - `APITestRunner.java`
   - `CombinedTestRunner.java`
2. Select **"Run 'UITestRunner'"** (or respective runner)
3. Reports generate automatically

---

## Report Locations

After running tests, find your reports at:

### HTML Reports (Visual)
```
target/cucumber-reports/
├── ui-report.html              (UI Tests)
├── api-report.html             (API Tests)
└── combined-report.html        (All Tests)
```

### JSON Reports (Data)
```
target/cucumber-json-reports/
├── ui-report.json              (UI Tests)
├── api-report.json             (API Tests)
└── combined-report.json        (All Tests)
```

### JUnit XML Reports
```
target/cucumber-reports/
├── ui-report.xml               (UI Tests)
├── api-report.xml              (API Tests)
└── combined-report.xml         (All Tests)
```

---

## Opening and Viewing Reports

### HTML Reports (Recommended)
1. Navigate to `target/cucumber-reports/`
2. Open the desired HTML file in your web browser:
   - `ui-report.html` - for UI test results
   - `api-report.html` - for API test results
   - `combined-report.html` - for all tests combined

**Features of HTML Reports:**
- Visual pass/fail indicators (green/red)
- Test execution time
- Feature and Scenario details
- Step-by-step execution logs
- Screenshot attachments (if implemented)
- Summary statistics

### JSON Reports (For Integration)
JSON reports are useful for:
- CI/CD pipeline integration
- Custom reporting tools
- Test analytics platforms
- Automated report distribution

---

## Test Runners and Their Reports

### 1. **UITestRunner** - Selenium UI Tests
- **Features Location**: `src/test/resources/features/ui/`
- **Tags**: `@ui`
- **Report**: `ui-report.html`
- **Tests Orangehrm Application**

### 2. **APITestRunner** - REST Assured API Tests
- **Features Location**: `src/test/resources/features/api/`
- **Tags**: `@api`
- **Report**: `api-report.html`
- **Tests Restful-API.dev endpoints**

### 3. **CombinedTestRunner** - All Tests
- **Features Location**: `src/test/resources/features/`
- **Tags**: `@smoke or @regression or @negative`
- **Report**: `combined-report.html`
- **Includes both UI and API tests**

---

## Configuring Report Options

The Cucumber reporting plugin is configured in `pom.xml`:

```xml
<plugin>
    <groupId>net.masterthought</groupId>
    <artifactId>maven-cucumber-reporting</artifactId>
    <version>5.7.0</version>
    <executions>
        <execution>
            <id>execution</id>
            <phase>verify</phase>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <projectName>API &amp; UI Testing Framework</projectName>
                <outputDirectory>target/cucumber-reports</outputDirectory>
                <inputDirectory>target/cucumber-json-reports</inputDirectory>
                <skippedFails>true</skippedFails>
                <parallelTesting>false</parallelTesting>
                <disableLog>false</disableLog>
            </configuration>
        </execution>
    </executions>
</plugin>
```

---

## Running Tests with Different Options

### Record Screenshots (Optional)
If you want to add screenshots to your UI reports, add to UITestRunner:
```java
plugin = {
    "pretty",
    "html:target/cucumber-reports/ui-report.html",
    "json:target/cucumber-json-reports/ui-report.json",
    "junit:target/cucumber-reports/ui-report.xml"
    // Screenshots can be embedded in hooks
}
```

### Dry Run (Show what tests will run without executing)
```bash
mvn clean test -Ddryrun=true
```

### Specific Scenarios
Run specific scenarios by tag:
```bash
mvn clean verify -Dcucumber.filter.tags="@ui and @smoke"
```

---

## Troubleshooting

### Issue: Reports not generating
**Solution**: Ensure tests are not skipped and complete successfully
```bash
mvn clean verify -X  # -X for verbose output
```

### Issue: No JSON files in target folder
**Solution**: Ensure JSON plugin is specified in test runners
```java
plugin = {
    "json:target/cucumber-json-reports/report.json"
}
```

### Issue: HTML report is blank
**Solution**: Clear target folder and rebuild
```bash
mvn clean verify
```

### Issue: Cannot open HTML report in Firefox
**Solution**: Some browsers require CORS. Use Chrome or open directly from file system.

---

## Publishing Reports Online (Optional)

### Option 1: Cucumber Reports (Free)
1. Generate JSON report
2. Visit [https://reports.cucumber.io/](https://reports.cucumber.io/)
3. Upload the JSON file
4. Share the link

### Option 2: Local Server
Serve reports from a local HTTP server:
```bash
# Using Python
python -m http.server 8000 --directory target/cucumber-reports

# Then open http://localhost:8000 in your browser
```

---

## Jenkins/CI-CD Integration

Add to your Jenkins pipeline or GitHub Actions:

```bash
# Run tests and generate reports
mvn clean verify

# Archive reports
# This path: target/cucumber-reports/**
```

---

## Best Practices

1. **Always run `mvn clean`** before running tests to ensure fresh reports
2. **Use meaningful tags** (@ui, @api, @smoke, @regression) for better report organization
3. **Add descriptive scenario names** in feature files for clarity in reports
4. **Review reports regularly** to catch flaky tests
5. **Commit feature files** to version control for better traceability
6. **Don't commit target folder** - it gets regenerated on each build

---

## Quick Reference Commands

```bash
# Generate all reports
mvn clean verify

# Generate UI reports only
mvn clean verify -Dcucumber.filter.tags="@ui"

# Generate API reports only
mvn clean verify -Dcucumber.filter.tags="@api"

# Generate smoke tests only
mvn clean verify -Dcucumber.filter.tags="@smoke"

# Run without stopping on failure
mvn clean verify -DfailIfNoTests=false

# Generate with detailed logging
mvn clean verify -X

# Generate only tests (skip API/UI execution)
mvn clean test

# Show test execution summary
mvn clean verify | grep -E "(BUILD|Tests|Scenarios)"
```

---

## Report Contents

Each HTML report contains:

### Summary Section
- Total Features
- Total Scenarios
- Total Steps
- Pass/Fail/Skip/Undefined counts
- Execution time
- Pass percentage

### Feature Details
- Feature name and description
- Background steps (if any)
- All scenarios with:
  - Scenario name
  - Status (Passed/Failed/Skipped)
  - Execution time
  - Step-by-step breakdown

### Step Details
Each step shows:
- Step keyword (Given/When/Then)
- Step text
- Result status
- Execution time
- Error message (if failed)
- Log output (if available)

---

For more information, visit:
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Maven Cucumber Reporting](https://github.com/damianszczepanik/maven-cucumber-reporting)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [REST Assured Documentation](https://rest-assured.io/)

