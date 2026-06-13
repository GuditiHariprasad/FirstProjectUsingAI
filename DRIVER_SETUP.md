# Driver Setup Guide

## Prerequisites for Running Tests

### 1. Chrome Browser Setup

**Option A: Automatic Setup (Recommended)**
- Install Chrome browser from https://www.google.com/chrome/download/
- Update ChromeDriver path in your system PATH

**Option B: Manual Setup**
- Download ChromeDriver matching your Chrome version from https://chromedriver.chromium.org/
- Extract the driver and set path in DriverFactory.java:
  ```java
  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
  ```

### 2. Firefox Browser Setup

**Option A: Automatic Setup (Recommended)**
- Install Firefox browser from https://www.mozilla.org/firefox/new/
- Update GeckoDriver path in your system PATH

**Option B: Manual Setup**
- Download GeckoDriver from https://github.com/mozilla/geckodriver/releases
- Extract the driver and set path in DriverFactory.java:
  ```java
  System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
  ```

### 3. Add Drivers to System PATH (Windows)

1. Download ChromeDriver and GeckoDriver
2. Extract to a folder (e.g., `C:\WebDrivers`)
3. Add the folder to Windows PATH:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Add `C:\WebDrivers` to System PATH
4. Restart your IDE and terminal

### 4. Verify Setup

```bash
# Check ChromeDriver
chromedriver --version

# Check GeckoDriver
geckodriver --version
```

## Alternative: Docker Setup

For headless testing with Docker:

1. Create a Dockerfile with Chrome/Firefox
2. Update config.properties: `ui.headless=true`
3. Run tests in Docker container

## Troubleshooting

- **"chromedriver not found"**: Add driver to PATH or set System.setProperty()
- **"Chrome version mismatch"**: Download driver matching your Chrome version
- **"Permission denied"**: Make driver executable: `chmod +x chromedriver`

