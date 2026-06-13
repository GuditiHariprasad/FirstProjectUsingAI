# Quick Start Guide

## Project Overview

This is a comprehensive automation testing framework that supports both **UI Testing** (using Selenium) and **API Testing** (using REST Assured) with the **Cucumber BDD framework**.

### Key Features

✅ **Selenium WebDriver** - Cross-browser UI automation  
✅ **REST Assured** - RESTful API testing  
✅ **Cucumber BDD** - Human-readable test scenarios  
✅ **Page Object Model** - Maintainable UI test structure  
✅ **Centralized Configuration** - Properties and JSON files  
✅ **Comprehensive Logging** - Log4j2 integration  
✅ **HTML Reports** - Beautiful test reports with history  

---

## 1. Initial Setup

### Prerequisites

- **Java 11+**: Required for running the framework
- **Maven 3.6+**: Required for building the project
- **Chrome or Firefox**: For UI testing
- **Git**: Optional, for version control

### Installation Steps

1. **Clone or Download the Project**
   ```bash
   # If using Git
   git clone <repository-url>
   cd FirstProjectUsingAI
   ```

2. **Install Required Browsers**
   - Download Chrome: https://www.google.com/chrome/
   - Download Firefox: https://www.mozilla.org/firefox/

3. **Download WebDrivers** (See DRIVER_SETUP.md for detailed steps)
   - ChromeDriver: https://chromedriver.chromium.org/
   - GeckoDriver: https://github.com/mozilla/geckodriver/releases

4. **Add WebDrivers to System PATH**
   - Windows: Add driver location to Environment Variables → PATH
   - Or place drivers in project root directory

5. **Verify Setup**
   ```bash
   # Open terminal in project directory
   mvn --version
   java -version
   chromedriver --version
   ```

---

## 2. Project Configuration

### Update Configuration Files

1. **src/main/resources/config.properties**
   ```properties
   # Update browser type
   ui.browser=chrome  # or firefox
   
   # Update if testing on different URL
   ui.base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
   
   # API base URL
   api.base.url=https://api.restful-api.dev
   ```

2. **src/main/resources/ui_test_data.json**
   ```json
   {
     "validLogin": {
       "username": "Admin",
       "password": "admin123"
     }
   }
   ```

3. **src/main/resources/api_test_data.json**
   - Update test data for API scenarios

---

## 3. Build the Project

```bash
# Navigate to project directory
cd FirstProjectUsingAI

# Build project (skip tests)
mvn clean install -DskipTests

# Build and run tests
mvn clean install
```

---

## 4. Run Tests

### Using Maven Commands

```bash
# Run all tests
mvn test

# Run only UI tests
mvn test -Dtest=UITestRunner

# Run only API tests
mvn test -Dtest=APITestRunner

# Run combined tests
mvn test -Dtest=CombinedTestRunner

# Run with specific tags
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke"
```

### Using Test Runner Scripts

**Windows:**
```bash
# Run UI tests
run_tests.bat ui

# Run API tests
run_tests.bat api

# Run smoke tests
run_tests.bat smoke

# Run all tests
run_tests.bat all
```

**Mac/Linux:**
```bash
# Make script executable
chmod +x run_tests.sh

# Run UI tests
./run_tests.sh ui

# Run API tests
./run_tests.sh api

# Run all tests
./run_tests.sh all
```

---

## 5. View Test Reports

After running tests, view the reports:

### HTML Reports
- **UI Report**: `target/cucumber-reports/ui-report.html`
- **API Report**: `target/cucumber-reports/api-report.html`
- **Combined Report**: `target/cucumber-reports/combined-report.html`

### JSON Reports (for CI/CD)
- `target/cucumber-reports/ui-report.json`
- `target/cucumber-reports/api-report.json`
- `target/cucumber-reports/combined-report.json`

### Logs
- Test logs: `logs/test-automation.log`

---

## 6. Writing New Tests

### Add UI Test Scenario

1. Create feature file: `src/test/resources/features/ui/your_feature.feature`
   ```gherkin
   Feature: Your Feature Name
     
     Scenario: Your test scenario
       Given User navigates to the login page
       When User enters valid credentials
       Then User should see dashboard
   ```

2. Implement step definitions in `src/test/java/com/automation/stepdefinitions/ui/`

3. Create/Update page objects in `src/main/java/com/automation/pages/`

### Add API Test Scenario

1. Create feature file: `src/test/resources/features/api/your_api_feature.feature`
   ```gherkin
   Feature: API Feature Name
     
     Scenario: Test API endpoint
       When User sends a GET request to "/objects"
       Then Response status code should be 200
   ```

2. Implement step definitions in `src/test/java/com/automation/stepdefinitions/api/`

3. Update test data in `src/main/resources/api_test_data.json`

---

## 7. Project Structure

```
FirstProjectUsingAI/
├── pom.xml                      # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── pages/          # Page Object Model
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   └── EmployeePage.java
│   │   │   └── utils/          # Utilities
│   │   │       ├── ConfigReader.java
│   │   │       ├── JsonUtils.java
│   │   │       ├── DriverFactory.java
│   │   │       ├── APIClient.java
│   │   │       └── WebElementWait.java
│   │   └── resources/
│   │       ├── config.properties
│   │       ├── ui_test_data.json
│   │       ├── api_test_data.json
│   │       └── log4j2.xml
│   └── test/
│       ├── java/com/automation/
│       │   ├── stepdefinitions/
│       │   │   ├── ui/
│       │   │   └── api/
│       │   └── runners/
│       │       ├── UITestRunner.java
│       │       ├── APITestRunner.java
│       │       └── CombinedTestRunner.java
│       └── resources/
│           └── features/
│               ├── ui/
│               └── api/
├── logs/                        # Test logs
├── target/                      # Build outputs
│   └── cucumber-reports/        # Test reports
├── README.md                    # Full documentation
├── DRIVER_SETUP.md             # Driver setup guide
└── QUICK_START.md              # This file
```

---

## 8. Troubleshooting

### Test Execution Issues

**Issue**: "WebDriver not found"
- **Solution**: Add WebDriver to system PATH or update DRIVER_SETUP.md

**Issue**: "Failed to connect to API"
- **Solution**: Check internet connection and API URL in config.properties

**Issue**: "Element not found"
- **Solution**: Update locators in page objects or use explicit waits

### Build Issues

**Issue**: "Maven not found"
- **Solution**: Install Maven and add to system PATH

**Issue**: "Java version incompatible"
- **Solution**: Install Java 11 or higher

**Issue**: "Dependencies not resolved"
- **Solution**: Run `mvn clean install` to download dependencies

---

## 9. Best Practices

✅ **Always use Page Object Model** for UI tests  
✅ **Keep test data in JSON/Properties files**  
✅ **Use explicit waits instead of Thread.sleep()**  
✅ **Write descriptive step names**  
✅ **Maintain test independence**  
✅ **Use logging for debugging**  
✅ **Clean up resources in @After hooks**  

---

## 10. CI/CD Integration

### GitHub Actions Example

Create `.github/workflows/test.yml`:
```yaml
name: Run Automation Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
      - uses: actions/upload-artifact@v2
        with:
          name: Test Reports
          path: target/cucumber-reports/
```

---

## 11. Common Commands Reference

```bash
# Clean project
mvn clean

# Build project
mvn install

# Run tests
mvn test

# Skip tests during build
mvn install -DskipTests

# Run specific test class
mvn test -Dtest=UITestRunner

# View project info
mvn project-info-reports:index

# Download dependencies
mvn dependency:resolve

# Generate reports
mvn site
```

---

## 12. Support and Documentation

- **Full README**: See `README.md` for comprehensive documentation
- **Driver Setup**: See `DRIVER_SETUP.md` for driver configuration
- **Cucumber**: Visit https://cucumber.io/docs
- **Selenium**: Visit https://www.selenium.dev/documentation/
- **REST Assured**: Visit https://rest-assured.io/

---

## Next Steps

1. ✅ Clone/download the project
2. ✅ Set up WebDrivers (see DRIVER_SETUP.md)
3. ✅ Run `mvn clean install` to build
4. ✅ Configure `config.properties` if needed
5. ✅ Run tests: `mvn test`
6. ✅ View reports in `target/cucumber-reports/`

---

**Ready to test?** Start with:
```bash
mvn test -Dtest=UITestRunner
```

Enjoy automated testing! 🚀

