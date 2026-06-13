# Maven Plugin Configuration in IntelliJ IDEA

## Overview
Your project is configured with Maven plugins for compilation, testing, and report generation. This guide will help you use them effectively in IntelliJ.

---

## 1. Maven Plugins in Your Project

### Configured Plugins:

#### 1. **Maven Compiler Plugin** (v3.11.0)
- Compiles Java source code for Java 11
- Configuration:
  - Source: Java 11
  - Target: Java 11
  - Encoding: UTF-8

#### 2. **Maven Surefire Plugin** (v3.1.2)
- Runs unit tests during `test` phase
- Includes all `*Runner.java` and `*Test.java` classes
- Used for: Unit and integration tests

#### 3. **Maven Failsafe Plugin** (v3.1.2)
- Runs integration tests during `verify` phase
- Includes all `*IT.java` and `*Runner.java` classes
- Used for: Cucumber test runners

#### 4. **Maven Cucumber Reporting Plugin** (v7.1.0)
- Generates beautiful HTML reports after tests
- Reads JSON output from test runners
- Outputs HTML report to: `target/cucumber-reports/index.html`

---

## 2. How to Use Maven in IntelliJ

### Method 1: Maven Tool Window (Recommended)

1. **Open Maven Tool Window:**
   - Go to: **View → Tool Windows → Maven**
   - Or press: `Ctrl + Shift + M`

2. **View Project Lifecycle:**
   - In the Maven window, expand your project
   - You'll see "Lifecycle" folder with options:
     - `clean` - Deletes target directory
     - `compile` - Compiles source code
     - `test` - Runs unit tests
     - `package` - Creates JAR file
     - `install` - Installs to local repository
     - `deploy` - Deploys to remote repository

3. **Run a Maven Command:**
   - Double-click on the lifecycle phase you want to run
   - Or right-click and select "Run"

### Method 2: Terminal in IntelliJ

1. **Open Terminal:**
   - Go to: **View → Tool Windows → Terminal**
   - Or press: `Alt + F12`

2. **Run Maven Commands:**
   ```powershell
   mvn clean compile
   mvn clean test
   mvn clean verify
   mvn clean install
   ```

### Method 3: Run Configuration

1. **Create/Edit Run Configuration:**
   - Go to: **Run → Edit Configurations**
   - Click `+` → Search for "Maven"
   - Configure:
     - Name: `Run All Tests` (or any name)
     - Command line: `clean verify`
     - Working directory: `$ProjectFileDir$`

2. **Run the Configuration:**
   - Select from dropdown next to Run button
   - Press `Shift + F10`

---

## 3. Common Maven Commands

### Build Commands

```powershell
# Clean build (removes target directory)
mvn clean

# Compile source code
mvn compile

# Compile and run tests
mvn test

# Clean, compile, and run all tests
mvn clean test

# Create JAR file
mvn package

# Full build (compile, test, package)
mvn clean package

# Install to local Maven repository
mvn clean install
```

### Test Execution Commands

```powershell
# Run only UI tests (uses @ui tag)
mvn clean test -Dgroups="ui"

# Run only API tests (uses @api tag)
mvn clean test -Dgroups="api"

# Run all tests and generate reports
mvn clean verify

# Run specific test runner
mvn -Dtest=UITestRunner test
mvn -Dtest=APITestRunner test
mvn -Dtest=CombinedTestRunner test

# Run tests and skip report generation
mvn clean test -DskipReports=true
```

### Report Generation

```powershell
# Generate Cucumber HTML reports after tests
mvn clean verify
# Reports will be available at: target/cucumber-reports/index.html
```

---

## 4. Report Locations

After running tests, reports are generated in:

### Cucumber HTML Report:
- **Path:** `target/cucumber-reports/index.html`
- **Contains:** Comprehensive test execution report with:
  - Test scenarios and steps
  - Pass/Fail status
  - Execution time
  - Screenshots (if captured)

### Individual Test Reports:
- **UI Tests:** `target/cucumber-reports/ui-report.html`
- **API Tests:** `target/cucumber-reports/api-report.html`
- **Combined Tests:** `target/cucumber-reports/combined-report.html`

### JSON Output (used by Cucumber Reporting):
- **Location:** `target/cucumber-json-reports/`
- **Files:**
  - `ui-report.json`
  - `api-report.json`
  - `combined-report.json`

### JUnit XML Reports:
- **Location:** `target/cucumber-reports/`
- **Files:**
  - `ui-report.xml`
  - `api-report.xml`
  - `combined-report.xml`

---

## 5. IntelliJ Maven Settings

### Enable Maven Auto-Import

1. Go to: **File → Settings → Build, Execution, Deployment → Maven**
2. Check: **"Always update snapshots"**
3. Check: **"Automatically download sources and documentation"**
4. Click: **OK**

### Set Maven Home (if needed)

1. Go to: **File → Settings → Build, Execution, Deployment → Maven**
2. **Maven home path:** (Usually auto-detected)
   - If not set, select the Maven installation path
3. Click: **OK**

### Refresh Maven Project

- Right-click on `pom.xml` in project explorer
- Select: **Maven → Reload Projects**
- Or: `Ctrl + Shift + Alt + U`

---

## 6. Maven Plugin Execution Flow

```
┌─────────────┐
│  mvn verify │
└──────┬──────┘
       │
       ├─→ [1] Maven Compiler Plugin
       │     Compiles source code
       │
       ├─→ [2] Maven Surefire Plugin
       │     Runs unit tests (*Test.java)
       │     Outputs: JSON, HTML, XML
       │
       ├─→ [3] Maven Failsafe Plugin
       │     Runs integration tests (*IT.java, *Runner.java)
       │     Outputs: JSON, HTML, XML
       │
       └─→ [4] Cucumber Reporting Plugin
             Processes JSON reports
             Generates: target/cucumber-reports/index.html
```

---

## 7. Viewing Test Reports

### In IntelliJ:

1. After tests complete, go to: **View → Tool Windows → Maven**
2. View the build output in the console

### In Web Browser:

1. Open file explorer: Press `Win + R` → `explorer` -> Enter
2. Navigate to: `C:\Users\Hariprasad\IdeaProjects\FirstProjectUsingAI\target\cucumber-reports\`
3. Open: `index.html` in your web browser
4. View detailed test reports with:
   - Pass/Fail scenarios
   - Execution time
   - Detailed step information
   - Charts and graphs

### Direct Path:
```
C:\Users\Hariprasad\IdeaProjects\FirstProjectUsingAI\target\cucumber-reports\index.html
```

---

## 8. Troubleshooting

### Issue: Maven Not Found
**Solution:** 
- Set MAVEN_HOME environment variable
- Or configure Maven home in IntelliJ settings

### Issue: Tests Not Running
**Solution:**
- Ensure test runners have @RunWith(Cucumber.class)
- Check feature files exist in correct paths
- Verify step definitions are correctly mapped

### Issue: Reports Not Generated
**Solution:**
- Ensure Cucumber plugins output JSON files
- Run `mvn clean verify` (not just `test`)
- Check target/cucumber-json-reports/ directory for JSON files

### Issue: IntelliJ Not Recognizing Maven Changes
**Solution:**
- Right-click pom.xml → Maven → Reload Projects
- Or: File → Invalidate Caches and Restart

---

## 9. Best Practices

1. **Always Clean Before Build:**
   ```powershell
   mvn clean package  # Instead of just mvn package
   ```

2. **Use Profiles (Optional Future Enhancement):**
   ```powershell
   mvn -P ui test    # Run only UI tests
   mvn -P api test   # Run only API tests
   ```

3. **Skip Tests When Not Needed:**
   ```powershell
   mvn clean package -DskipTests
   ```

4. **Check Dependencies:**
   ```powershell
   mvn dependency:tree
   ```

5. **Validate pom.xml:**
   ```powershell
   mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin
   ```

---

## 10. Quick Reference

| Task | Command |
|------|---------|
| Build Project | `mvn clean package` |
| Run All Tests | `mvn clean verify` |
| Run UI Tests | `mvn -Dtest=UITestRunner test` |
| Run API Tests | `mvn -Dtest=APITestRunner test` |
| View Reports | Open `target/cucumber-reports/index.html` |
| Clean Build | `mvn clean` |
| Update Dependencies | `mvn dependency:resolve` |
| Skip Tests | `mvn clean package -DskipTests` |

---

## 11. Summary

Your Maven setup includes:
- ✅ Automatic test compilation and execution
- ✅ JSON output for Cucumber reporting
- ✅ HTML report generation
- ✅ JUnit XML reports for CI/CD integration
- ✅ Proper test phase management

All plugins are integrated into the build lifecycle. Simply run `mvn clean verify` to compile, test, and generate reports!

