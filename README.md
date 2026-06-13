# API and UI Testing Framework

A comprehensive automation testing framework built with Selenium, REST Assured, and Cucumber for testing both web applications and RESTful APIs.

## Project Structure

```
FirstProjectUsingAI/
├── pom.xml                              # Maven configuration
├── README.md                            # This file
├── logs/                                # Test execution logs
├── target/                              # Build and test reports
│   └── cucumber-reports/               # Cucumber HTML reports
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── pages/                  # Page Object Model classes
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── HomePage.java
│   │   │   └── utils/                  # Utility classes
│   │   │       ├── ConfigReader.java   # Read config properties
│   │   │       ├── JsonUtils.java      # JSON file handling
│   │   │       ├── DriverFactory.java  # WebDriver management
│   │   │       ├── APIClient.java      # REST API client
│   │   │       └── WebElementWait.java # Explicit waits
│   │   └── resources/
│   │       ├── config.properties       # Configuration file
│   │       ├── ui_test_data.json      # UI test data
│   │       ├── api_test_data.json     # API test data
│   │       └── log4j2.xml             # Logging configuration
│   └── test/
│       ├── java/com/automation/
│       │   ├── stepdefinitions/
│       │   │   ├── ui/                # UI step definitions
│       │   │   │   └── LoginStepDefinitions.java
│       │   │   └── api/               # API step definitions
│       │   │       └── APIStepDefinitions.java
│       │   └── runners/               # Test runners
│       │       ├── UITestRunner.java
│       │       ├── APITestRunner.java
│       │       └── CombinedTestRunner.java
│       └── resources/
│           └── features/
│               ├── ui/                 # UI feature files
│               │   └── login.feature
│               └── api/                # API feature files
│                   └── api_testing.feature
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome or Firefox browser (for UI tests)
- Internet connection for API tests

## Project Dependencies

- Selenium WebDriver 4.15.0
- REST Assured 5.4.0
- Cucumber 7.14.0
- JUnit 4.13.2
- Log4j 2.21.1
- Gson 2.10.1
- TestNG 7.8.1
- AssertJ 3.24.1

## Configuration

### Update Configuration Files

1. **config.properties** - Update the following properties:
   ```properties
   # UI Configuration
   ui.base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
   ui.browser=chrome              # chrome or firefox
   ui.implicit.wait=10            # in seconds
   ui.explicit.wait=20            # in seconds
   ui.headless=false              # true for headless mode
   
   # API Configuration
   api.base.url=https://api.restful-api.dev
   api.request.timeout=5000       # in milliseconds
   ```

2. **ui_test_data.json** - Update test data for login:
   ```json
   {
     "validLogin": {
       "username": "Admin",
       "password": "admin123",
       "expectedTitle": "OrangeHRM"
     },
     "invalidLogin": {
       "username": "InvalidUser",
       "password": "InvalidPass"
     }
   }
   ```

3. **api_test_data.json** - Update API test data as needed

## Build the Project

```bash
# Clean and build
mvn clean install

# Build without running tests
mvn clean install -DskipTests

# Build with specific profile (if applicable)
mvn clean install -P test-profile
```

## Run Tests

### Run All Tests
```bash
mvn test
```

### Run UI Tests Only
```bash
mvn test -Dtest=UITestRunner
```

### Run API Tests Only
```bash
mvn test -Dtest=APITestRunner
```

### Run Tests with Specific Tags
```bash
# Run only smoke tests
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke"

# Run only regression tests
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@regression"

# Run both smoke and regression
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke or @regression"
```

### Run Specific Feature File
```bash
# UI tests
mvn test -Dtest=UITestRunner

# API tests
mvn test -Dtest=APITestRunner
```

## Test Reports

After test execution, reports are generated in `target/cucumber-reports/`:

- `ui-report.html` - UI test report
- `api-report.html` - API test report
- `combined-report.html` - Combined test report
- `ui-report.json` - JSON format for CI/CD integration
- `api-report.json` - JSON format for CI/CD integration

To view the HTML report, open it in a browser:
```bash
# Windows
start target/cucumber-reports/combined-report.html

# Mac
open target/cucumber-reports/combined-report.html

# Linux
xdg-open target/cucumber-reports/combined-report.html
```

## Logging

Logs are generated in `logs/` directory. Configure logging in `log4j2.xml`:
- Console logs in real-time
- Rolling file logs with daily rotation
- Separate logs for automation module

## Project Features

### 1. **Page Object Model (POM)**
   - Centralized locator management
   - Reusable page methods
   - Maintains separation of concerns
   - Easy to maintain and scale

### 2. **Cucumber BDD Framework**
   - Feature files written in Gherkin language
   - Human-readable test scenarios
   - Better test documentation
   - Easy stakeholder communication

### 3. **Selenium WebDriver**
   - Cross-browser testing support
   - Explicit and implicit waits
   - WebDriverManager for automatic driver setup
   - Headless mode support

### 4. **REST Assured API Testing**
   - Easy REST API testing
   - Request/Response validation
   - JSON/XML parsing
   - Headers and authentication support

### 5. **Configuration Management**
   - Properties file for easy configuration
   - JSON files for test data
   - Centralized configuration
   - Environment-specific configs

### 6. **Logging and Reporting**
   - Log4j2 for comprehensive logging
   - Cucumber HTML reports
   - JSON reports for CI/CD
   - JUnit XML reports

## Writing New Tests

### Adding UI Tests

1. Create a new feature file in `src/test/resources/features/ui/`
2. Write scenarios in Gherkin
3. Implement step definitions in `src/test/java/com/automation/stepdefinitions/ui/`
4. Create or update page objects in `src/main/java/com/automation/pages/`

### Adding API Tests

1. Create a new feature file in `src/test/resources/features/api/`
2. Write scenarios in Gherkin
3. Implement step definitions in `src/test/java/com/automation/stepdefinitions/api/`
4. Update test data in `api_test_data.json`

### Adding Page Objects

1. Create a new Java class in `src/main/java/com/automation/pages/`
2. Extend `BasePage`
3. Define locators using `By` class
4. Implement methods for page interactions

Example:
```java
public class NewsPage extends BasePage {
    private By newsHeading = By.id("news-heading");
    
    public String getNewsHeading() {
        return driver.findElement(newsHeading).getText();
    }
}
```

## Best Practices

1. **Use Explicit Waits** - Avoid Thread.sleep() when possible
2. **Maintain DRY** - Don't repeat code, use utilities
3. **Descriptive Names** - Use clear variable and method names
4. **Error Handling** - Use try-catch for exception handling
5. **Logging** - Log important steps and failures
6. **Data Management** - Keep test data in JSON/properties files
7. **Test Independence** - Tests should not depend on each other
8. **Cleanup** - Always cleanup resources in @After hooks

## Troubleshooting

### WebDriver Issues
- Ensure Chrome/Firefox is installed
- WebDriverManager will download compatible drivers automatically

### Feature File Not Found
- Ensure feature files are in `src/test/resources/features/`
- Check the glue path in runner classes

### Properties File Issues
- Verify the path in ConfigReader matches actual location
- Check file encoding is UTF-8

### API Connection Issues
- Verify internet connection
- Check API base URL is correct
- Ensure API is accessible from your network

## CI/CD Integration

The project can be integrated with CI/CD tools like Jenkins, GitHub Actions, etc.

Example GitHub Actions workflow:
```yaml
name: Run Tests
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
          name: cucumber-reports
          path: target/cucumber-reports/
```

## Contributing

When contributing to this project:
1. Create feature files for new functionality
2. Implement step definitions
3. Add corresponding page objects if needed
4. Update test data files
5. Run all tests locally before pushing
6. Generate and review reports

## Contact

For issues, questions, or suggestions, contact the automation team.

---

**Last Updated:** June 12, 2026
**Version:** 1.0.0

