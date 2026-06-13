#!/bin/bash

# Test Execution Script for Automation Framework
# Usage: ./run_tests.sh [option]
# Options: ui, api, all, smoke, regression, negative, combined

clear

echo "========================================"
echo "  Automation Testing Framework - Test Runner"
echo "========================================"
echo ""

if [ -z "$1" ]; then
    echo "No option provided. Available options:"
    echo "  ui          - Run UI tests only"
    echo "  api         - Run API tests only"
    echo "  all         - Run all tests"
    echo "  smoke       - Run smoke tests"
    echo "  regression  - Run regression tests"
    echo "  negative    - Run negative tests"
    echo "  combined    - Run combined tests"
    echo ""
    echo "Example: ./run_tests.sh ui"
    exit 0
fi

# Get the directory where the script is located
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

case "$1" in
    "ui")
        echo "Running UI Tests..."
        mvn test -Dtest=UITestRunner
        ;;
    "api")
        echo "Running API Tests..."
        mvn test -Dtest=APITestRunner
        ;;
    "all")
        echo "Running All Tests..."
        mvn test
        ;;
    "smoke")
        echo "Running Smoke Tests..."
        mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@smoke"
        ;;
    "regression")
        echo "Running Regression Tests..."
        mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@regression"
        ;;
    "negative")
        echo "Running Negative Tests..."
        mvn test -Dtest=CombinedTestRunner -Dcucumber.filter.tags="@negative"
        ;;
    "combined")
        echo "Running Combined Tests..."
        mvn test -Dtest=CombinedTestRunner
        ;;
    *)
        echo "Invalid option: $1"
        echo "Available options: ui, api, all, smoke, regression, negative, combined"
        exit 1
        ;;
esac

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================"
    echo "  Tests completed successfully!"
    echo "========================================"
    # Open the report in default browser
    if command -v xdg-open &> /dev/null; then
        xdg-open target/cucumber-reports/combined-report.html
    elif command -v open &> /dev/null; then
        open target/cucumber-reports/combined-report.html
    fi
else
    echo ""
    echo "========================================"
    echo "  Tests failed. Check logs for details."
    echo "========================================"
    exit 1
fi

