@echo off
REM Test Execution Script for Automation Framework
REM Usage: run_tests [option]
REM Options: ui, api, all, smoke, regression, negative

cls
echo ===============================================
echo   Automation Testing Framework - Test Runner
echo ===============================================
echo.

if "%1%"=="" (
    echo No option provided. Available options:
    echo   ui          - Run UI tests only
    echo   api         - Run API tests only
    echo   all         - Run all tests
    echo   smoke       - Run smoke tests
    echo   regression  - Run regression tests
    echo   negative    - Run negative tests
    echo   combined    - Run combined tests
    echo.
    echo Example: run_tests.bat ui
    exit /b
)

setlocal enabledelayedexpansion

cd /d "%~dp0"

if /i "%1%"=="ui" (
    echo Running UI Tests...
    call mvn test -Dtest=UITestRunner
) else if /i "%1%"=="api" (
    echo Running API Tests...
    call mvn test -Dtest=APITestRunner
) else if /i "%1%"=="all" (
    echo Running All Tests...
    call mvn test
) else if /i "%1%"=="smoke" (
    echo Running Smoke Tests...
    call mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke"
) else if /i "%1%"=="regression" (
    echo Running Regression Tests...
    call mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@regression"
) else if /i "%1%"=="negative" (
    echo Running Negative Tests...
    call mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@negative"
) else if /i "%1%"=="combined" (
    echo Running Combined Tests...
    call mvn test -Dtest=CombinedTestRunner
) else (
    echo Invalid option: %1%
    echo Available options: ui, api, all, smoke, regression, negative, combined
    exit /b
)

if %ERRORLEVEL% equ 0 (
    echo.
    echo ===============================================
    echo   Tests completed successfully!
    echo ===============================================
    start /d "%~dp0" "target\cucumber-reports\combined-report.html"
) else (
    echo.
    echo ===============================================
    echo   Tests failed. Check logs for details.
    echo ===============================================
    exit /b %ERRORLEVEL%
)

endlocal

