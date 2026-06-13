# Complete Project Setup and Usage Guide

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Running Tests](#running-tests)
6. [Project Structure](#project-structure)
7. [Creating New Tests](#creating-new-tests)
8. [Troubleshooting](#troubleshooting)
9. [CI/CD Integration](#cicd-integration)

---

## 🎯 Project Overview

**API and UI Testing Framework** is a comprehensive automation solution providing:

- ✅ **UI Testing**: Selenium WebDriver with Page Object Model
- ✅ **API Testing**: REST Assured for RESTful API automation
- ✅ **BDD Framework**: Cucumber with Gherkin language
- ✅ **Configuration Management**: Centralized properties and JSON files
- ✅ **Test Reporting**: HTML and JSON reports with Cucumber
- ✅ **Logging**: Comprehensive logging with Log4j2
- ✅ **Cross-Platform**: Works on Windows, Mac, and Linux

### Key Technologies
- **Java 11+** - Programming language
- **Maven** - Build and dependency management
- **Selenium 4.13** - Web browser automation
- **REST Assured 5.3.2** - RESTful API testing
- **Cucumber 7.14** - BDD test framework
- **log4j2** - Logging framework

---

## 🔧 Prerequisites

### System Requirements
- **OS**: Windows 10+, macOS 10.15+, or Linux
- **Java**: OpenJDK 11 or higher
- **Maven**: 3.6 or higher
- **Memory**: 4GB minimum RAM
- **Disk**: 2GB free space

### Required Software

1. **Java JDK 11+**
   ```bash
   # Verify Java installation
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   # Verify Maven installation
   mvn -version
   ```

3. **Git (Optional)**
   ```bash
   # For version control
   git --version
   ```

4. **Browsers**
   - Chrome: https://www.google.com/chrome/
   - Firefox: https://www.mozilla.org/firefox/

---

## 📦 Installation

### Step 1: Download WebDrivers

#### For Chrome Users:
```
1. Visit: https://chromedriver.chromium.org/
2. Download matching your Chrome version
3. Extract to C:\WebDrivers (Windows) or ~/WebDrivers (Mac/Linux)
```

#### For Firefox Users:
```
1. Visit: https://github.com/mozilla/geckodriver/releases
2. Download latest release for your OS
3. Extract to C:\WebDrivers (Windows) or ~/WebDrivers (Mac/Linux)
```

### Step 2: Add WebDrivers to PATH

**Windows:**
1. Press `Win + X` → System
2. Advanced system settings
3. Environment Variables
4. Add `C:\WebDrivers` to System PATH
5. Restart terminal/IDE

**Mac/Linux:**
```bash
# Option 1: Move to /usr/local/bin
cp ~/Downloads/chromedriver /usr/local/bin/
chmod +x /usr/local/bin/chromedriver

# Option 2: Add to PATH in ~/.zshrc or ~/.bash_profile
export PATH=$PATH:~/WebDrivers
```

### Step 3: Clone/Extract Project

```bash
# Clone from repository (if available)
git clone <repository-url>

# Or extract downloaded ZIP file
# Navigate to project directory
cd FirstProjectUsingAI
```

### Step 4: Build Project

```bash
# Download dependencies and compile
mvn clean install -DskipTests

# Wait for download to complete (~5-10 minutes for first build)
```

---

## ⚙️ Configuration

### 1. Update config.properties

**File**: `src/main/resources/config.properties`

```properties
# Update UI settings
ui.browser=chrome                          # chrome or firefox
ui.headless=false                         # true for CI/CD

# Update UI target URL if different
ui.base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

# Update API target URL if different
api.base.url=https://api.restful-api.dev
```

### 2. Update Test Data Files

**UI Test Data**: `src/main/resources/ui_test_data.json`
```json
{
  "validLogin": {
    "username": "Admin",
    "password": "admin123"
  }
}
```

**API Test Data**: `src/main/resources/api_test_data.json`
```json
{
  "createObject": {
    "name": "Test Object",
    "data": {
      "year": 2021
    }
  }
}
```

---

## 🚀 Running Tests

### Command Line Execution

```bash
# Run all tests
mvn test

# Run UI tests only
mvn test -Dtest=UITestRunner

# Run API tests only
mvn test -Dtest=APITestRunner

# Run combined tests
mvn test -Dtest=CombinedTestRunner

# Run with specific tags
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke"

# Run with multiple tags
mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke or @api"
```

### Using Test Runner Scripts

**Windows:**
```bash
# Run tests
run_tests.bat ui          # UI tests
run_tests.bat api         # API tests
run_tests.bat all         # All tests
run_tests.bat smoke       # Smoke tests
run_tests.bat regression  # Regression tests
```

**Mac/Linux:**
```bash
# Make executable
chmod +x run_tests.sh

# Run tests
./run_tests.sh ui          # UI tests
./run_tests.sh api         # API tests
./run_tests.sh all         # All tests
./run_tests.sh smoke       # Smoke tests
```

### IDE Integration

**IntelliJ IDEA:**
1. Right-click on test runner class
2. Select "Run 'ClassName'"
3. Select "Run with Coverage" for coverage report

**Eclipse:**
1. Right-click on test runner class
2. Run As → JUnit Test

**Visual Studio Code:**
1. Install Extension Pack for Java
2. Right-click on runner class
3. Run Tests

---

## 📁 Project Structure

```
FirstProjectUsingAI/
│
├── pom.xml                                 # Maven configuration
├── README.md                               # Full documentation
├── QUICK_START.md                         # Quick setup guide
├── DRIVER_SETUP.md                        # Driver configuration
├── SETUP_AND_USAGE.md                     # This file
├── run_tests.bat                          # Windows test runner
├── run_tests.sh                           # Mac/Linux test runner
│
├── src/main/
│   ├── java/com/automation/
│   │   ├── pages/                        # Page Object Model
│   │   │   ├── BasePage.java             # Base class for all pages
│   │   │   ├── LoginPage.java            # Login form interactions
│   │   │   ├── HomePage.java             # Dashboard interactions
│   │   │   └── EmployeePage.java         # Employee management
│   │   │
│   │   └── utils/                        # Utility classes
│   │       ├── ConfigReader.java         # Read configuration
│   │       ├── JsonUtils.java            # JSON file handling
│   │       ├── DriverFactory.java        # WebDriver management
│   │       ├── APIClient.java            # REST API client
│   │       └── WebElementWait.java       # Element waits
│   │
│   └── resources/
│       ├── config.properties             # Main configuration
│       ├── config.example.properties     # Configuration template
│       ├── ui_test_data.json            # UI test data
│       ├── api_test_data.json           # API test data
│       └── log4j2.xml                   # Logging configuration
│
├── src/test/
│   ├── java/com/automation/
│   │   ├── stepdefinitions/
│   │   │   ├── ui/
│   │   │   │   └── LoginStepDefinitions.java
│   │   │   └── api/
│   │   │       └── APIStepDefinitions.java
│   │   │
│   │   └── runners/
│   │       ├── UITestRunner.java
│   │       ├── APITestRunner.java
│   │       └── CombinedTestRunner.java
│   │
│   └── resources/
│       └── features/
│           ├── ui/
│           │   └── login.feature
│           └── api/
│               └── api_testing.feature
│
├── logs/                                  # Test execution logs
│   └── test-automation.log
│
└── target/                               # Build outputs
    ├── classes/                          # Compiled classes
    ├── test-classes/                     # Compiled test classes
    └── cucumber-reports/                # Test reports
        ├── combined-report.html
        ├── ui-report.html
        ├── api-report.html
        ├── combined-report.json
        ├── ui-report.json
        └── api-report.json
```

---

## 📝 Creating New Tests

### Adding UI Test

1. **Create Feature File**: `src/test/resources/features/ui/new_feature.feature`
```gherkin
Feature: Feature Description
  
  @smoke @ui
  Scenario: Scenario Description
    Given User navigates to the login page
    When User enters valid credentials
    Then User should see dashboard
```

2. **Implement Step Definitions**: `src/test/java/com/automation/stepdefinitions/ui/NewStepDefinitions.java`
```java
public class NewStepDefinitions {
    private CustomPage customPage;
    
    @Given("User navigates to {string}")
    public void navigateTo(String url) {
        customPage = new CustomPage();
        customPage.navigateTo(url);
    }
}
```

3. **Create Page Object**: `src/main/java/com/automation/pages/CustomPage.java`
```java
public class CustomPage extends BasePage {
    private By element = By.id("element-id");
    
    public void interactWithElement() {
        WebElement el = wait.waitForElement(element);
        el.click();
    }
}
```

### Adding API Test

1. **Create Feature File**: `src/test/resources/features/api/new_api.feature`
```gherkin
Feature: API Endpoints

  @api @smoke
  Scenario: Call GET endpoint
    When User sends a GET request to "/endpoint"
    Then Response status code should be 200
```

2. **Implement Step Definition**: Add to `APIStepDefinitions.java`
```java
@When("User sends a GET request to {string}")
public void sendGetRequest(String endpoint) {
    response = apiClient.get(endpoint);
}
```

---

## 🔍 Viewing Test Results

### HTML Reports
Open in browser:
- `target/cucumber-reports/combined-report.html`
- `target/cucumber-reports/ui-report.html`
- `target/cucumber-reports/api-report.html`

### Logs
View in text editor:
- `logs/test-automation.log`

### Console Output
Maven prints test results and execution time

---

## ❌ Troubleshooting

### Build Issues

| Issue | Solution |
|-------|----------|
| "mvn: command not found" | Install Maven and add to PATH |
| Java version error | Install Java 11+ and update JAVA_HOME |
| Dependencies not found | Run `mvn dependency:resolve` |
| Port already in use | Change port or kill process |

### Test Execution Issues

| Issue | Solution |
|-------|----------|
| "WebDriver not found" | Add drivers to PATH or update DriverFactory.java |
| Chrome version error | Download matching ChromeDriver version |
| "Element not found" | Update locators in page objects |
| "Connection refused" | Check API URL and networking |
| Timeout errors | Increase explicit wait time in config.properties |

### Common Fixes

```bash
# Clear Maven cache
mvn dependency:purge-local-repository

# Rebuild project
mvn clean install

# Run tests in debug mode
mvn test -X

# Skip test compilation errors
mvn compile -DskipTests
```

---

## 🔄 CI/CD Integration

### GitHub Actions

Create `.github/workflows/tests.yml`:
```yaml
name: Automated Tests
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Setup Java
        uses: actions/setup-java@v3
        with:
          java-version: '11'
      - name: Build and Test
        run: mvn clean test
      - name: Upload Reports
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: Test Reports
          path: target/cucumber-reports/
```

### Jenkins Pipeline

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'combined-report.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
}
```

---

## 📚 Additional Resources

- **Selenium Documentation**: https://www.selenium.dev/documentation/
- **Cucumber Documentation**: https://cucumber.io/docs
- **REST Assured Guide**: https://rest-assured.io/
- **Maven Documentation**: https://maven.apache.org/

---

## 💡 Tips and Best Practices

✅ **Always use POM** for page objects  
✅ **Keep data external** in JSON/Properties files  
✅ **Write descriptive step names** in Gherkin  
✅ **Use explicit waits** instead of Thread.sleep()  
✅ **Maintain independence** between tests  
✅ **Log important steps** for debugging  
✅ **Tag tests appropriately** (@smoke, @regression, etc.)  
✅ **Review reports** after each test run  

---

## 🎓 Getting Help

1. Check `README.md` for comprehensive documentation
2. Review `QUICK_START.md` for rapid setup
3. Check `DRIVER_SETUP.md` for driver configuration
4. Review test logs in `logs/` directory
5. Check Cucumber/Selenium documentation

---

## 📝 Version History

| Version | Date | Updates |
|---------|------|---------|
| 1.0.0 | 2026-06-12 | Initial release |

---

**Happy Testing!** 🚀

For questions or issues, refer to the documentation or check the logs for detailed error messages.

